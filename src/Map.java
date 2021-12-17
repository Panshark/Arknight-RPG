import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Map extends JFrame {
	JPanel map = new JPanel();
	int area = 0;

	JPanel up = new JPanel();
	JPanel Zoo = new JPanel();
	JLabel ZooLa = new JLabel("                  Area 0 Battlefield                             ");
	JButton[] ZooBt = { new JButton("          0-1 Collapse            "),
			new JButton("           0-2 Dilemma             "),
			new JButton("            0-3 Assault             ")};
	
	JPanel Land = new JPanel();
	JLabel LandLa = new JLabel("              Area 1 Siege                    ");
	JButton[] LandBt = { new JButton("           1-1 Ruins Siege            "),
			new JButton("     1-2 Stronghold Siege        ") ,
			new JButton("        1-3 Outpost Siege           "),
			new JButton("              1-4 Base Siege           "),
			new JButton("          1-5 Fortress Siege         ")};

	JPanel Sea = new JPanel();
	JLabel SeaLa = new JLabel("                 Area 2 Siberia                     ");
	JButton[] SeaBt = { new JButton("               2-1 Biting Cold            "),
			new JButton("         2-2 Drifting, Falling           ") ,
			new JButton("2-3 Furniture Shipping Escort"),
			new JButton("           2-4 Condensation          "),
			new JButton("              2-5 Hoarfrost               ")};

	JPanel Sky = new JPanel();
	JLabel SkyLa = new JLabel("              Area 3 SKY FALL                ");
	JButton[] SkyBt = { new JButton("             3-1 Guerilla-1             "),
			new JButton("            3-2 Guerilla-2              "),
			new JButton("   3-3 It Fell From the Sky!    "),
			new JButton(" 3-4 Deep Into Enemy Lines"),
			new JButton("              3-5 Intruder               ")};

	JPanel down = new JPanel();
	JPanel End = new JPanel();
	JLabel EndLa = new JLabel("         Area EX End Game           ");
	JButton[] EndBt = { new JButton("       Giant      ") ,
			new JButton("       Assassin     	"),
			new JButton("       Dandy        ") ,
			new JButton("       Godfather         ") ,
			new JButton("    Winter's Scar    ") };

	JPanel cen = new JPanel();
	JPanel[] panels = { Zoo, Land, Sea, Sky, End };
	JLabel[] labels = { ZooLa, LandLa, SeaLa, SkyLa, EndLa };
	JButton[][] buttons = { ZooBt, LandBt, SeaBt, SkyBt, EndBt };

	public Map(String name) {
		super(name);
		setLayout(new BorderLayout());

		for (int count = 0; count < panels.length; count++) {
			panels[count].add(labels[count]);
			for (int index = 0; index < buttons[count].length; index++) {
				buttons[count][index].addActionListener(new MapLis());
				panels[count].add(buttons[count][index]);
			}
		}
		map.setLayout(new GridLayout(3, 1));
		
		up.setLayout(new GridLayout(2,1));
		up.add(new JLabel());
		up.add(panels[0]);
		map.add(up);
		
		cen.setLayout(new GridLayout(1, 3));
		cen.add(panels[1]);
		cen.add(panels[2]);
		cen.add(panels[3]);
		map.add(cen);
		
		down.setLayout(new GridLayout(2,1));
		down.add(panels[4]);
		map.add(down);

		add(new ActMenu(), BorderLayout.NORTH);
		add(map, BorderLayout.CENTER);
		
	}

	// Create a enemy group
	public Enemy[] CreArray(int area, int process) {
		Enemy[] enermy = new Enemy[3];
		for (int index = 0; index < 3; index++) {
            if(area==0)
			    enermy[index] = new EnemyBegin(process);
            else if(area==1)
            	enermy[index] = new EnemyLand(process);
            else if(area==2)
            	enermy[index] = new EnemySea(process);
            else if(area==3)
            	enermy[index] = new EnemySky(process);
            else if(area==4)
				if (index == 0) {
					enermy[index] = new EnemyEnd(process);
				}else{
					enermy[index] = new EnemySky(process);
				}
			enermy[index].setName(index);
		}

		return enermy;
	}

	private class MapLis implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Checks the number of active characters
			for (int count = 0; count < run.data.wargroup.length; count++)
				if(run.data.wargroup[count]==null){
					JOptionPane.showMessageDialog(null, "You need at least three agents��", "Inadequate fighting capacity ", JOptionPane.INFORMATION_MESSAGE);
					
					return;
				}
			setVisible(false);
			//Determine the enemy type based on the parameters
			loop: for (int area = 0; area < buttons.length; area++)
				for (int process = 0; process < buttons[area].length; process++) {
					if (e.getSource() == buttons[area][process]) {
						Fighting fight = new Fighting(run.title, area, process, CreArray(area, process));
						fight.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						fight.setPreferredSize(new Dimension(1000, 900));
						run.screencentre(fight);
						fight.pack();
						fight.setVisible(true);
						break loop;
					}
				}
		}
	}
}
