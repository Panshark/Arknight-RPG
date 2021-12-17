import java.sql.*;

public class create {
    public static void main(String[] args){
        String url="jdbc:sqlite:database.db";    //URL of JDBC
        Connection conn;
        try {
            conn = DriverManager.getConnection(url,"root","");
            //creat a Statement object
            Statement stmt = conn.createStatement();
            System.out.print("Succeed to connect to database!");


            String sql;
//            sql = "create table Arknights (\n" +
//                    "                    num integer,\n" +
//                    "                    data Ole Object,\n" +
//                    "                    money integer,\n" +
//                    "                    primary key (num)\n" +
//                    "            );";
            //sql = "drop table Arknights";
            sql = "Select * from Arknights where num = " + 2 + ";";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                int id  = rs.getInt("money");
                System.out.print("ID: " + id);
                System.out.print("\n");  //just to check the table Arknights
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}