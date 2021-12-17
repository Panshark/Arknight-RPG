import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;


public class S_L {

	public static void Save(int number) {
		try {
			Socket socket;
			socket = new Socket("localhost", 6999);

			DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
			//DataInputStream fromServer = new DataInputStream(socket.getInputStream());//
			toServer.writeInt(0);
			toServer.flush();

			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

			ArrayList<Object> x = new ArrayList<>();
			x.add(run.data.wargroup);
			x.add(run.base.members);
			x.add(run.shop.st.equip);
			x.add(run.bag.bag.item);
			x.add(run.map.buttons);

			out.writeObject(x);
			out.flush();

			toServer.writeInt(number);
			toServer.flush();
			toServer.writeInt(run.data.money);
			toServer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Load(int number) {
		try {
			Socket socket;
			socket = new Socket("localhost", 6999);

			DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
			DataInputStream fromServer = new DataInputStream(socket.getInputStream());

			toServer.writeInt(1);
			toServer.flush();
			toServer.writeInt(number);
			toServer.flush();

			if(fromServer.readBoolean()) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

				try {

					ArrayList<Object> x = (ArrayList<Object>) in.readObject();

					run.data.wargroup = (Agents[]) x.get(0);
					run.base.members = (Agents[])x.get(1);

					run.shop.st.equip = (Equipment[])x.get(2);
					for (int index = 0; index < run.shop.st.des.length; index++)
						run.shop.st.des[index].setText(run.shop.st.equip[index].print());

					run.bag.bag.item = (Equipment[])x.get(3);
					for (int index = 0; index < run.bag.bag.display.length; index++) {
						if (run.bag.bag.item[index] instanceof Blank)
							run.bag.bag.display[index].setText("");
						else
							run.bag.bag.display[index].setText(run.bag.bag.item[index].print());
					}

					JButton[][] buttons = (JButton[][])x.get(4);
					for (int count = 0; count < buttons.length; count++)
						for (int index = 0; index < buttons[count].length; index++)
							run.map.buttons[count][index].setForeground(buttons[count][index].getForeground());
					run.data.money = fromServer.readInt();

					in.close();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}  catch (IOException e) {
			e.printStackTrace();
		}
	}
}
