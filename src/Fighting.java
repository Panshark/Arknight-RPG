import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

// Fighting
public class Fighting extends JFrame {

	int hucount = 0, anicount = 0, current = 0, gain, lost, area, process, experience;
	Enemy[] humans;
	JPanel fighting = new JPanel();
	JTextArea EnHP = new JTextArea();
	JTextArea MyHP = new JTextArea();
	JLabel EnPic = new JLabel();
	JLabel MyPic = new JLabel(run.data.wargroup[0].image);
	String des = "";
	JTextArea Description = new JTextArea(des);
	JScrollPane jp = new JScrollPane(Description);
	JButton[] buts = new JButton[3];

	public Fighting(String name, int area, int process, Enemy[] humans) {
		super(name);
		this.area = area;
		this.process = process;
		this.humans = humans;
		this.gain = (area + 1) * 100 + process * 20;
		this.lost = (area + 1) * 50 + process * 10;
		this.experience = (area + 1) * 50 + process * 10;
		EnPic.setIcon(humans[0].image);

		CheckAlive();
		fighting.setLayout(new GridLayout(3, 2));

		EnHP.setEditable(false);
		EnHP.setOpaque(false);
		MyHP.setEditable(false);
		MyHP.setOpaque(false);
		EnHP.setText(printHP(humans));
		MyHP.setText(printHP(run.data.wargroup));
		fighting.add(EnHP);
		fighting.add(MyHP);

		fighting.add(EnPic);
		fighting.add(MyPic);

		Description.setEditable(false);
		Description.setOpaque(false);
		fighting.add(jp);

		fighting.add(oPanel());
		add(fighting);

	}

	// HP
	public String printHP(Agents[] animal) {
		String HP = "";
		for (int index = 0; index < animal.length; index++) {
			HP += animal[index].name + "   HP:" + animal[index].HP + "/" + animal[index].TotalHP + "   level:"
					+ animal[index].level + "\n\n";
		}
		return HP;
	}

	public String printHP(Enemy[] humans) {
		String HP = "";
		for (int index = 0; index < humans.length; index++) {
			HP += humans[index].name + " HP:" + humans[index].HP + "/" + humans[index].TotalHP + "\n\n";
		}
		return HP;
	}

	// Check alive or not in the beginning
	public void CheckAlive() {
		for (int index = 0; index < run.data.wargroup.length; index++)
			if (run.data.wargroup[index].HP == 0)
				anicount++;
	}

	// Attack
	public void MyAttack(Enemy[] humans) {
		// Makes a valid object attack
		if (run.data.wargroup[current].HP == 0) {
			for (int index = 0; index < run.data.wargroup.length; index++) {
				if (run.data.wargroup[index].HP != 0) {
					des += run.data.wargroup[index].attack(humans, current);
					break;
				}
			}
		} else
			des += run.data.wargroup[current].attack(humans, current);

		// Battle Information Description
		EnHP.setText(printHP(humans));
		Description.setText(des);
		// Equipment durability reduced
		run.data.wargroup[current].enduranceDown();
		// Enemy down
		hucount = 0;
		for (int index = 0; index < humans.length; index++)
			if (humans[index].HP <= 0) {
				hucount++;
			}
		// Victor if enemies all down
		if (hucount >= humans.length) {

			ImageIcon victory = new ImageIcon("Images/Victory.png");
			Equipment drop = drop();
			// Gain experience/money/equipment
			String win = "Victor!\nGain " + experience + " EXP£¡\nGain " + gain + " Gold!\n";
			if (drop != null) {
				run.bag.bag.equipAdd(drop);
				win += "Achieve the equipment£º" + drop.name + "\n";
			}
			run.data.money += gain;
			// Level up
			for (int index = 0; index < run.data.wargroup.length; index++)
				if (run.data.wargroup[index].levelUp(experience))
					win += "\n" + run.data.wargroup[index].name + " level up! To level " + run.data.wargroup[index].level + " !";
			// victory information
			JOptionPane.showMessageDialog(null, win, "Victor", JOptionPane.INFORMATION_MESSAGE, victory);
			
			// Get new agents
			run.base.AddMember(area,process);
			
			this.setVisible(false);
			// already conquered
			if (process < run.map.buttons[area].length) {
				run.map.buttons[area][process].setForeground(Color.blue);
			}
			run.map.pack();
			run.map.setVisible(true);
			
		}

	}

	public void EnAttack(Agents[] animal) {
		// Makes a valid object attack
		if (humans[current].HP == 0) {
			for (int index = 0; index < humans.length; index++) {
				if (humans[index].HP != 0) {
					des += humans[index].attack(animal, current);
					break;
				}
			}
		} else
			des += humans[current].attack(animal, current);

		MyHP.setText(printHP(run.data.wargroup));
		Description.setText(des);
		current++;
		if (current % 3 == 0)
			current = 0;
		// Count fall
		anicount = 0;
		for (int index = 0; index < animal.length; index++)
			if (animal[index].HP <= 0) {
				anicount++;
			}
		// failed
		if (anicount >= animal.length) {
			// loss money
			if(run.data.money<=lost)
				lost=run.data.money;
			run.data.money-=lost;
			
			ImageIcon lose = new ImageIcon("Images/Failed.png");
			JOptionPane.showMessageDialog(null, "Battle failed\nLoss " + lost + " Gold!", "Failed", JOptionPane.INFORMATION_MESSAGE,
					lose);
			this.setVisible(false);

			run.map.pack();
			run.map.setVisible(true);
		}

	}

	// Random loot equipment
	public Equipment drop() {
		Equipment drop = null;
		Random gene = new Random();
		int rand = gene.nextInt(25) + 1;
		if (rand == 5)
			drop = new Earrings().changeQuality();
		else if (rand == 10)
			drop = new Bracelet().changeQuality();
		else if (rand == 15)
			drop = new Helmet().changeQuality();
		else if (rand == 20)
			drop = new Breastplate().changeQuality();
		else if (rand == 25)
			drop = new Kneecap().changeQuality();

		return drop;
	}

	// Combat operations
	public JPanel oPanel() {
		JPanel operation = new JPanel();
		JPanel[] op = new JPanel[3];
		buts[0] = new JButton("Attack");
		buts[1] = new JButton("Healing (HoneyBerry:" + run.base.aidnum + "/10)");
		buts[2] = new JButton("Escape");
		operation.setLayout(new GridLayout(3, 1));
		for (int index = 0; index < buts.length; index++) {
			buts[index].addActionListener(new attackLis());
			op[index] = new JPanel();
			op[index].add(buts[index]);
			operation.add(op[index]);
		}
		return operation;
	}

	private class attackLis implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Attack listener
			if (e.getSource() == buts[0]) {
				MyAttack(humans);
				EnAttack(run.data.wargroup);

				MyPic.setIcon(run.data.wargroup[current].image);

			}
			// Healing
			else if (e.getSource() == buts[1]) {
				for (int index = 0; index < run.base.Kits.length; index++) {
					if (run.base.Kits[index] != null) {
						run.base.Kits[index].cure(run.data.wargroup[current]);
						run.base.aidnum--;
						des += run.data.wargroup[current].name + " refreshes " + run.base.Kits[index].recover + " HP\n";
						Description.setText(des);
						run.base.Kits[index] = null;
						run.base.disaid.setText("HoneyBerry (Treat a agent 50):" + run.base.aidnum + "/10");
						buts[1].setText("Healing (HoneyBerry:" + run.base.aidnum + "/10)");
						break;
					}
				}
				EnAttack(run.data.wargroup);
				current++;
				if (current % 3 == 0)
					current = 0;
				MyPic.setIcon(run.data.wargroup[current].image);
				EnHP.setText(printHP(humans));
				MyHP.setText(printHP(run.data.wargroup));
			}
			// Escape
			else if (e.getSource() == buts[2]) {
				EnAttack(run.data.wargroup);
				dispose();

				run.map.pack();
				run.map.setVisible(true);
			}
		}
	}
}
