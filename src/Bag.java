import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Bag interface
public class Bag extends JFrame {
	JPanel center = new JPanel();
	dataDes data = new dataDes();
	dataBag bag = new dataBag(data);

	public Bag(String name) {
		super(name);
		setLayout(new BorderLayout());
		add(new ActMenu(), BorderLayout.NORTH);

		center.setLayout(new GridLayout(1, 2));
		center.add(bag);
		center.add(data);
		add(center, BorderLayout.CENTER);

		add(run.data.Gold[1], BorderLayout.SOUTH);

	}
}

// Current equipment
class dataBag extends JTabbedPane {
	int contain = 0;
	int capacity = 20;
	JLabel des = new JLabel("Capacity      " + contain + "/" + capacity);
	JPanel big = new JPanel();
	JPanel bag = new JPanel();
	Equipment[] item = new Equipment[capacity];
	JTextArea[] display = new JTextArea[capacity];
	dataDes data;
	GridLayout layout = new GridLayout(5, 4, 20, 20);

	public dataBag(dataDes data) {
		this.data = data;

		bag.setLayout(layout);
		for (int index = 0; index < capacity; index++) {
			item[index] = new Blank();
			display[index] = new JTextArea();
			display[index].setEditable(false);
			display[index].setComponentPopupMenu(popup(index));
			bag.add(display[index]);
		}

		big.setLayout(new BorderLayout());
		big.add(bag, BorderLayout.CENTER);
		big.add(des, BorderLayout.SOUTH);

		add("Bag", big);
	}

	// Add equipment to Bag
	public void equipAdd(Equipment equip) {
		for (int index = 0; index < capacity; index++) {
			// Add an item when there is no item in the grid
			if (item[index] instanceof Blank) {
				if (!(equip instanceof Blank)) {
					item[index] = equip;
					display[index].setText(item[index].print());
					des.setText("Capacity      " + (++contain) + "/" + capacity);
				}
				return;
			}
		}
		// No return the Bag is full
		JOptionPane.showMessageDialog(null, "The Bag is full, unable to add equipment!", "The Bag is full", JOptionPane.INFORMATION_MESSAGE);

	}

	// Right click pop-up menu
	public JPopupMenu popup(int index) {
		JPopupMenu menu = new JPopupMenu();
		menu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				menu.show();
			}
		});

		// Right-click equip function
		JMenuItem equipV = new JMenuItem("Equip");
		equipV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agents ani = run.base.members[data.getSelectedIndex()];
				item[index] = ani.changeEquip(item[index]);
				if (item[index] instanceof Blank) {
					display[index].setText("");
					des.setText("Capacity      " + (--contain) + "/" + capacity);
				} else
					display[index].setText(item[index].print());
			}
		});

		// Right click sell function
		JMenuItem sellV = new JMenuItem("Sell");
		sellV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				run.data.money += item[index].cost;
				item[index] = new Blank();
				display[index].setText("");
				des.setText("Capacity      " + (--contain) + "/" + capacity);
			}
		});

		// Right click discard function
		JMenuItem throwV = new JMenuItem("Discard");
		throwV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item[index] = new Blank();
				display[index].setText("");
				des.setText("Capacity      " + (--contain) + "/" + capacity);
			}
		});

		for (int count = 0; count < capacity; count++) {
			menu.add(equipV);
			menu.add(sellV);
			menu.add(throwV);
		}
		return menu;
	}

}

// Current property
class dataDes extends JTabbedPane {
	createDes[] des = new createDes[run.base.members.length];

	public dataDes() {

		for (int index = 0; index < run.base.members.length; index++) {
			if (run.base.members[index] != null) {
				createTab(index, run.base.members[index]);
			}
		}

		Thread desc = new Thread(new Description());
		desc.setDaemon(true);
		desc.start();
	}

	// Create a new role
	public void createTab(int index, Agents animal) {
		synchronized (this) { 
		des[index] = new createDes(animal);
		add(animal.name, des[index]);
		}
	}

	// Real-time update
	private class Description implements Runnable {
		public void run() {
			while (true) {

				try {
					Thread.sleep(107);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int index = 0; index < run.base.members.length; index++) {
					if (run.base.members[index] != null)
						des[index].words.setText((run.base.members[index].print()));
				}
			}
		}
	}
}