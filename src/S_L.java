import java.io.*;

import javax.swing.*;


public class S_L {

	public static void Save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Saves/save"));

			out.writeInt(run.data.money);
			out.writeObject(run.data.wargroup);
			out.writeObject(run.base.members);

			out.writeObject(run.shop.st.equip);

			out.writeObject(run.bag.bag.item);

			out.writeObject(run.map.buttons);

			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Load() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("Saves/save"));
			try {

				run.data.money = (int)in.readInt();
				run.data.wargroup = (Agents[]) in.readObject();
				run.base.members = (Agents[])in.readObject();

				run.shop.st.equip = (Equipment[]) in.readObject();
				for (int index = 0; index < run.shop.st.des.length; index++)
					run.shop.st.des[index].setText(run.shop.st.equip[index].print());

				run.bag.bag.item = (Equipment[]) in.readObject();
				for (int index = 0; index < run.bag.bag.display.length; index++) {
					if (run.bag.bag.item[index] instanceof Blank)
						run.bag.bag.display[index].setText("");
					else
						run.bag.bag.display[index].setText(run.bag.bag.item[index].print());
				}

				JButton[][] buttons = (JButton[][]) in.readObject();
				for (int count = 0; count < buttons.length; count++)
					for (int index = 0; index < buttons[count].length; index++)
						run.map.buttons[count][index].setForeground(buttons[count][index].getForeground());
						
				in.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			run.frames[1].setVisible(false);
        	run.frames[0].setVisible(true);
			JOptionPane.showMessageDialog(null, "无法检测到已知存档", "载入失败", JOptionPane.INFORMATION_MESSAGE);
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
