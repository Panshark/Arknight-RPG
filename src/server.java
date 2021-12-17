import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;

public class server extends JFrame implements Runnable{

    private Connection con;
    public server() {
        Thread t = new Thread(this);
        t.start();
    }
    @SuppressWarnings("resource")
    @Override
    public void run() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(6999);
            con = DriverManager.getConnection("jdbc:sqlite:database.db");
            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();
                try {
                    // Create data input and output streams
                    DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());

                    switch(inputFromClient.readInt()){
                        case 0:
                            new Thread(new SaveUpdate(socket)).start();
                            break;
                        case 1:
                            new Thread(new LoadData(socket)).start();
                            break;
                        default:
                            break;
                    }
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
                // Create and start a new thread for the connection
            }
        }
        catch(IOException ex) {
            System.err.println(ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    class SaveUpdate implements Runnable{
        private Socket socket; // A connected socket

        DataOutputStream outputToClient;
        DataInputStream inputFromClient;

        /** Construct a thread */
        public SaveUpdate(Socket socket) {
            this.socket = socket;
            try {
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try {
                storedata();
            } catch (ClassNotFoundException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        private void storedata() throws IOException, ClassNotFoundException, SQLException {

            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());

            Object deSerializedObject = objectIn.readObject();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream tmp = null;
            tmp = new ObjectOutputStream(bos);
            tmp.writeObject(deSerializedObject);
            tmp.flush();
            byte[] data = bos.toByteArray();

            int num = inputFromClient.readInt();
            int money = inputFromClient.readInt();

            String sq = "Select money from Arknights where num = " + num + ";";
            PreparedStatement search = con.prepareStatement(sq);
            ResultSet rs = search.executeQuery();
            if(rs.next()) { //if we have the load, update it. else insert it
                String sql = "update Arknights set "
                        + "data = ?,money = " + money
                        +" where num = " + num + ";";

                PreparedStatement statement = con.prepareStatement(sql);
                statement.setBytes(1, data);
                statement.execute();
            }else {
                String sql = "insert into Arknights values("
                        + num + ",?," + money +");";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setBytes(1, data);
                statement.execute();
            }

        }
    }

    class LoadData implements Runnable{
        private Socket socket; // A connected socket

        DataOutputStream outputToClient;
        DataInputStream inputFromClient;

        /** Construct a thread */
        public LoadData(Socket socket) {
            this.socket = socket;
            try {
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            try {
                loadMap();
            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        private void loadMap() throws SQLException, IOException, ClassNotFoundException {
            DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
            DataInputStream fromClient = new DataInputStream(socket.getInputStream());

            int num = fromClient.readInt(); //load data according to the load position we select

            String sql = "select * from Arknights where num = " + num + ";";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if(rs.next() && !rs.isAfterLast())
            {
                byte[] list = rs.getBytes(2);
                int money = rs.getInt(3);

                toClient.writeBoolean(true);

                ObjectInputStream objectIn = null;

                if (list != null)
                    objectIn = new ObjectInputStream(new ByteArrayInputStream(list));

                Object dataState = objectIn.readObject();
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(dataState);
                out.flush();

                toClient.writeInt(money);
                toClient.flush();

            }else {
                toClient.writeBoolean(false);
            }
        }
    }

    public static void main (String[] args){
        new server();
    }
}