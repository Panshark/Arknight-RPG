import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Base extends JFrame {
	JPanel base = new JPanel();
	JScrollPane home = new JScrollPane(base);
	Agents[] members = new Agents[999];
	createDes[] des = new createDes[members.length];
	JButton[] gotowar = new JButton[members.length];
	JButton[] gotorest = new JButton[members.length];
	int[] Case = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	JButton cure = new JButton("     Healing all            ");
	JPanel aid = new JPanel();
	JPanel c = new JPanel();
	JPanel b = new JPanel();
	int aidnum = 0;
	JLabel disaid = new JLabel(" HoneyBerry (Treat a agent 50)£º0/10", JLabel.CENTER);
	cureKit[] Kits = new cureKit[10];
	JButton buyaid = new JButton("       Buy HoneyBerry (100G)          ");

	public Base(String name) {
		super(name);
		setLayout(new BorderLayout());
		add(new ActMenu(), BorderLayout.NORTH);

		base.setLayout(new GridLayout(0, 2, 15, 15));
		for (int index = 0; index < run.data.wargroup.length; index++) {
			members[index] = run.data.wargroup[index];
			des[index] = createCharacter(index, members[index]);
			gotowar[index].setEnabled(false);
			gotowar[index].setLabel("In team");
			gotorest[index].setEnabled(true);
			gotorest[index].setLabel("Rest");
			base.add(des[index]);
		}

		// mouse scroll event
		home.getVerticalScrollBar().setUnitIncrement(10);
		home.setWheelScrollingEnabled(true);

		cure.addActionListener(new cure());
		aid.setLayout(new GridLayout(4, 1));
		aid.add(new JLabel(new ImageIcon("Images/cure.jpg")));
		c.setLayout(new GridLayout(2, 1));
		c.add(new JLabel());
		JPanel s = new JPanel();
		s.add(cure);
		c.add(s);
		aid.add(c);
		aid.add(disaid);
		buyaid.addActionListener(new buyKit());
		b.add(buyaid);
		aid.add(b);

		Thread desc = new Thread(new Description());
		desc.setDaemon(true);
		desc.start();

		add(home, BorderLayout.CENTER);
		add(aid, BorderLayout.EAST);
		add(run.data.Gold[3], BorderLayout.SOUTH);
	}

	// Create role panel
	public createDes createCharacter(int index, Agents animal) {
		createDes basepanel = new createDes(animal);
		JPanel big = new JPanel();
		JPanel c = new JPanel();
		JPanel ss = new JPanel();

		JPanel sgo = new JPanel();

		sgo.setLayout(new GridLayout(1, 2));
		gotowar[index] = new JButton("Pick");
		gotowar[index].addActionListener(new go());
		sgo.add(gotowar[index]);

		gotorest[index] = new JButton("Resting");
		gotorest[index].setEnabled(false);
		gotorest[index].addActionListener(new go());
		sgo.add(gotorest[index]);

		c.setLayout(new GridLayout(2, 1));
		c.add(basepanel.pic);
		c.add(basepanel.jp);

		big.setLayout(new BorderLayout());
		big.add(c, BorderLayout.CENTER);
		ss.add(basepanel.unload);
		big.add(ss, BorderLayout.SOUTH);

		basepanel.setLayout(new BorderLayout());
		basepanel.add(big, BorderLayout.CENTER);
		basepanel.add(sgo, BorderLayout.SOUTH);

		return basepanel;
	}

	// Role activating listener
	private class go implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int index = 0; index < members.length; index++)
				if (e.getSource() == gotowar[index]) {
					for (int count = 0; count < run.data.wargroup.length; count++)
						if (run.data.wargroup[count] == null) {
							run.data.wargroup[count] = members[index];
							gotowar[index].setEnabled(false);
							gotowar[index].setLabel("In team");
							gotorest[index].setEnabled(true);
							gotorest[index].setLabel("Rest");
							break;
						}
				} else if (e.getSource() == gotorest[index]) {
					for (int count = 0; count < run.data.wargroup.length; count++)
						if (run.data.wargroup[count] == (members[index])) {
							run.data.wargroup[count] = null;
							gotorest[index].setEnabled(false);
							gotorest[index].setLabel("Resting");
							gotowar[index].setEnabled(true);
							gotowar[index].setLabel("Pick");
							break;
						}
				}

		}
	}

	// new member
	public void AddMember(int area, int process) {
		Agents newmember = null;
		// All scenes get new members in the first level
		if (process == 0) {
			switch (area) {
			case 1:
				if (Case[0]!=0){break;}
				Case[0]++;
				newmember = new Siege();
				break;
			case 2:
				if (Case[1]!=0){break;}
				Case[1]++;
				newmember = new Skadi();
				break;
			case 3:
				if (Case[2]!=0){break;}
				Case[2]++;
				newmember = new Schwarz();
				break;
			}
		}
		// others get new members in random
		else {
			Random generator = new Random();
			int rand = generator.nextInt(6);
			if (rand == 3) {
				int rand_a = generator.nextInt(3);
				switch (area) {
				case 1:
					switch (rand_a){
					case 0:
						if (Case[3]!=0){break;}
						Case[3]++;
						newmember = new SilverAsh();
						break;
					case 1:
						if (Case[4]!=0){break;}
						Case[4]++;
						newmember = new Mountain();
						break;
					case 2:
						if (Case[5]!=0){break;}
						Case[5]++;
						newmember = new Nian();
						break;
					}
				    break;
				case 2:
					switch (rand_a){
						case 0:
							if (Case[6]!=0){break;}
							Case[6]++;
							newmember = new Specter();
							break;
						case 1:
							if (Case[7]!=0){break;}
							Case[7]++;
							newmember = new Andreana();
							break;
						case 2:
							if (Case[8]!=0){break;}
							Case[8]++;
							newmember = new Gladiia();
							break;
					}
					break;
				case 3:
					switch (rand_a){
						case 0:
							if (Case[9]!=0){break;}
							Case[9]++;
							newmember = new Passenger();
							break;
						case 1:
							if (Case[10]!=0){break;}
							Case[10]++;
							newmember = new Platinum();
							break;
						case 2:
							if (Case[11]!=0){break;}
							Case[11]++;
							newmember = new Exusiai();
							break;
					}
					break;
				}
			}
		}
		// yes: 0, no: 1
		if (newmember != null) {
			int n = JOptionPane.showConfirmDialog(null, newmember.name + " wants to join your team£¡\n" + newmember.print() + "\nYes or No?",
					"New agent", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, newmember.image);
			if (n == 0)
				for (int index = 0; index < members.length; index++)
					if (members[index] == null) {
						run.shop.cha.createTab(index, newmember);
						run.bag.data.createTab(index, newmember);
						des[index] = createCharacter(index, newmember);
						base.add(des[index]);
						// Add a member last to prevent null pointing in the thread
						members[index] = newmember;
						break;
					}
		}
	}

	// real-time update
	private class Description implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(108);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int index = 0; index < run.base.members.length; index++) {
					if (run.base.members[index] != null) {
						des[index].words.setText((run.base.members[index].print()));
					}
				}
			}
		}
	}

	// healing
	private class cure implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!isFullHealth(members)) {
				int cost10 = 0, cost;
				for (int index = 0; index < members.length; index++) {
					if (members[index] != null) {
						cost10 += members[index].TotalHP - members[index].HP;
						members[index].HP = members[index].TotalHP;
					}
				}
				cost = cost10 / 10;
				run.data.money -= cost;

				JOptionPane.showMessageDialog(null, "Healing succeed!\nloss " + cost + " Gold!", "Healing ends",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "HP is full, no more healing!\n", "Healing ends", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	// Check for full HP
	public boolean isFullHealth(Agents[] animal) {
		boolean is = true;
		for (int index = 0; index < animal.length; index++) {
			if (animal[index] != null && animal[index].HP != animal[index].TotalHP)
				is = false;
		}
		return is;
	}

	// Buy HoneyBerry
	private class buyKit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (run.data.money >= 100) {
				for (int index = 0; index < Kits.length; index++) {
					if (Kits[index] == null) {
						Kits[index] = new cureKit(50);
						aidnum++;
						disaid.setText("HoneyBerry (Treat a agent 50): " + aidnum + "/10");
						run.data.money -= 100;
						break;
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "No gold, cannot buy!", "Cannot buy", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}

class cureKit {
	int recover;

	public cureKit(int recover) {
		this.recover = recover;
	}

	public void cure(Agents agents) {
		if (agents.TotalHP - agents.HP >= recover)
			agents.HP += recover;
		else
			agents.HP += agents.TotalHP - agents.HP;
	}
}
