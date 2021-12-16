import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

// shop
public class Shop extends JFrame {
	JPanel test = new JPanel();
	store st = new store();
	character cha = new character(st);

	public Shop(String name) {
		super(name);
		setLayout(new BorderLayout());
		add(new ActMenu(), BorderLayout.NORTH);

		test.setLayout(new GridLayout(1, 2));
		test.add(st);
		test.add(cha);

		add(test);
		add(run.data.Gold[2], BorderLayout.SOUTH);
	}
}

// Store
class store extends JTabbedPane {

	JPanel buy = new JPanel();
	JButton refresh = new JButton("Refresh the current goods ( 1500 G )");
	JButton buybag = new JButton("Buy (add to bag)");

	Equipment[] equip = { new Earrings().changeQuality(), new Bracelet().changeQuality(), new Helmet().changeQuality(),
			new Breastplate().changeQuality(), new Kneecap().changeQuality() };
	JCheckBox[] des = { new JCheckBox(equip[0].print()), new JCheckBox(equip[1].print()),
			new JCheckBox(equip[2].print()), new JCheckBox(equip[3].print()), new JCheckBox(equip[4].print()) };

	public store() {
		buy();
		add("Buy", buy);

	}

	// Buy interface
	public void buy() {
		buy.setLayout(new GridLayout(7, 1));
		for (int index = 0; index < des.length; index++)
			buy.add(des[index]);

		// Buy button
		buybag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipment[] purchase = getEquip();
				for (int index = 0; index < des.length; index++)
					des[index].setSelected(false);
				int sumcost = 0;

				// Total prices
				for (int count = 0; count < purchase.length; count++)
					if (purchase[count] != null) {
						sumcost += purchase[count].cost;
					}
				// Add to bag
				if (run.data.money >= sumcost) {
					for (int count = 0; count < purchase.length; count++) {
						if (purchase[count] != null) {
							run.data.money -= purchase[count].cost;
							run.bag.bag.equipAdd(purchase[count]);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No gold, cannot buy!", "Cannot buy", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// refresh goods
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (run.data.money >= 1500) {
						rollE();
						for (int index = 0; index < des.length; index++)
							des[index].setSelected(false);
						run.data.money -= 1500;
					} else
						JOptionPane.showMessageDialog(null, "No gold, cannot refresh!", "Cannot refresh", JOptionPane.INFORMATION_MESSAGE);
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JPanel b = new JPanel();
		JPanel r = new JPanel();
		b.add(buybag);
		r.add(refresh);
		buy.add(b);
		buy.add(r);
	}

	// Randomly changing equipment quality
	public void rollE() throws InstantiationException, IllegalAccessException {
		for (int index = 0; index < equip.length; index++) {
			equip[index] = equip[index].getClass().newInstance().changeQuality();
			des[index].setText(equip[index].print());
		}
	}

	// Get all checked goods
	public Equipment[] getEquip() {
		Equipment[] purchased = new Equipment[5];
		for (int index = 0; index < equip.length; index++) {
			if (des[index].isSelected())
				purchased[index] = equip[index];
		}
		return purchased;
	}
}

// Role panel
class character extends JTabbedPane {
	final int num = run.base.members.length;
	JPanel[] ani = new JPanel[num];
	JButton[] buyFor = new JButton[num];
	JPanel[] buy = new JPanel[num];
	JButton[] fixFor = new JButton[num];
	JPanel[] fix = new JPanel[num];
	createDes[] des = new createDes[num];
	store st;

	public character(store st) {
		this.st = st;
		for (int index = 0; index < run.base.members.length; index++) {
			if (run.base.members[index] != null) {
				createTab(index, run.base.members[index]);
			}
		}
		Thread desc = new Thread(new Description());
		desc.setDaemon(true);
		desc.start();
	}

	// Create role label
	public void createTab(int index, Agents animal) {
		buyFor[index] = new JButton("Buy for this agent");
		buyFor[index].addActionListener(new buyListener());
		buy[index] = new JPanel();
		buy[index].add(buyFor[index]);

		des[index] = new createDes(animal);

		fixFor[index] = new JButton("Repair equipments");
		fixFor[index].addActionListener(new fixListener());
		fix[index] = new JPanel();
		fix[index].add(fixFor[index]);

		ani[index] = new JPanel();
		ani[index].setLayout(new BorderLayout());
		ani[index].add(buy[index], BorderLayout.NORTH);
		ani[index].add(des[index], BorderLayout.CENTER);
		ani[index].add(fix[index], BorderLayout.SOUTH);

		add(animal.name, ani[index]);
	}

	// Real-time update
	private class Description implements Runnable {
		public void run() {
			while (true) {

				try {
					Thread.sleep(109);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int index = 0; index < run.base.members.length; index++) {
					if (run.base.members[index] != null) {
						des[index].words.setText((run.base.members[index].print()));
						setTitleAt(index, run.base.members[index].name);
					}
				}
			}

		}
	}

	// Buy button
	private class buyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Equipment[] purchase = st.getEquip();
			for (int index = 0; index < st.des.length; index++)
				st.des[index].setSelected(false);
			int aninum = 0, sumcost = 0;
			// buy for who
			for (int index = 0; index < run.base.members.length; index++) {
				if (e.getSource() == buyFor[index]) {
					aninum = index;
				}
			}
			// total cost
			for (int count = 0; count < purchase.length; count++)
				if (purchase[count] != null) {
					sumcost += purchase[count].cost;
				}
			// add
			if (run.data.money >= sumcost) {
				for (int count = 0; count < purchase.length; count++) {
					if (purchase[count] != null) {
						run.data.money -= purchase[count].cost;
						// Replace the purchased equipment and put the replacement in the bag
						run.bag.bag.equipAdd(run.base.members[aninum].changeEquip(purchase[count]));
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "No gold, cannot buy!", "Cannot buy", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	// Repair button
	private class fixListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int aninum = 0, sumcost = 0;

			// Repair for whom
			for (int index = 0; index < run.base.members.length; index++) {
				if (e.getSource() == fixFor[index]) {
					aninum = index;
				}
			}

			Equipment[] equip = run.base.members[aninum].equips;
			// Repair
			for (int index = 0; index < equip.length; index++) {
				if (equip[index] != null) {
					equip[index].endurance++;
					run.data.money--;
				}
			}
		}
	}
}
