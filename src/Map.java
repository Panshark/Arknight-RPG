import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.*;

public class Map extends JFrame {
	JPanel map = new JPanel();
	int area = 0;

	JPanel up = new JPanel();
	JPanel Zoo = new JPanel();
	JLabel ZooLa = new JLabel("                  Area 1 ����԰                             ");
	JButton[] ZooBt = { new JButton("          �����ѳ�            "), 
			new JButton("           �ֵ�ͻΧ             "), 
			new JButton("            ϣ��֮��             ")};
	
	JPanel Land = new JPanel();
	JLabel LandLa = new JLabel("            Area 2-1 ½��                      ");
	JButton[] LandBt = { new JButton("           ʹ��͵����              ") ,
			new JButton("           ��ս���Գ�              "),
			new JButton("          ���ط������䳵       "),
			new JButton("           ����Ұζ�͹�          "),
			new JButton("          �ݻ�ëƤ����           ")};

	JPanel Sea = new JPanel();
	JLabel SeaLa = new JLabel("                Area 2-2 ����                    ");
	JButton[] SeaBt = { new JButton("              ��ֹ�Ĳ�����           "),
			new JButton("              ӭս������                ") ,
			new JButton("              ����������ҵ            "),
			new JButton("              ���ֺ��ʲ͹�             "),
			new JButton("             ����Σ������              ")};

	JPanel Sky = new JPanel();
	JLabel SkyLa = new JLabel("           Area 2-3 ���              ");
	JButton[] SkyBt = { new JButton("         ���������ѵ��ܺ���   "),
			new JButton("          ʹ������                    "),
			new JButton("          ���������г�                "),
			new JButton("          ��ֹ��ɱ����                "),
			new JButton("         �����ϰ���ɻ�             ")};

	JPanel down = new JPanel();
	JPanel End = new JPanel();
	JLabel EndLa = new JLabel("         Area 3 ����           ");
	JButton[] EndBt = { new JButton("       ��ս      ") ,
			new JButton("       ��     	"),
			new JButton("       ��ս        ") ,
			new JButton("       ��ս         ") ,
			new JButton("       ���Ѻ�̸          ") };

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
	public Humen[] CreArray(int area, int process) {
		Humen[] enermy = new Humen[3];
		for (int index = 0; index < 3; index++) {
            if(area==0)
			    enermy[index] = new HumenZoo(process);
            else if(area==1)
            	enermy[index] = new HumenLand(process);
            else if(area==2)
            	enermy[index] = new HumenSea(process);
            else if(area==3)
            	enermy[index] = new HumenSky(process);
            else if(area==4)
            	enermy[index] = new HumenEnd(process);
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
