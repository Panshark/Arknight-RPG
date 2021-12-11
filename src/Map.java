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
	JLabel ZooLa = new JLabel("                  Area 1 动物园                             ");
	JButton[] ZooBt = { new JButton("          兽笼脱出            "), 
			new JButton("           街道突围             "), 
			new JButton("            希望之门             ")};
	
	JPanel Land = new JPanel();
	JLabel LandLa = new JLabel("            Area 2-1 陆地                      ");
	JButton[] LandBt = { new JButton("           痛击偷猎者              ") ,
			new JButton("           大战狩猎场              "),
			new JButton("          拦截贩卖运输车       "),
			new JButton("           攻陷野味餐馆          "),
			new JButton("          摧毁毛皮工厂           ")};

	JPanel Sea = new JPanel();
	JLabel SeaLa = new JLabel("                Area 2-2 海洋                    ");
	JButton[] SeaBt = { new JButton("              阻止滥捕鱼类           "),
			new JButton("              迎战捕鲸船                ") ,
			new JButton("              整治排污企业            "),
			new JButton("              大闹海鲜餐馆             "),
			new JButton("             捣毁危险声纳              ")};

	JPanel Sky = new JPanel();
	JLabel SkyLa = new JLabel("           Area 2-3 天空              ");
	JButton[] SkyBt = { new JButton("         暴揍掏鸟窝的熊孩子   "),
			new JButton("          痛打捕鸟者                    "),
			new JButton("          征服花鸟市场                "),
			new JButton("          禁止滥杀鸟类                "),
			new JButton("         击落障碍物飞机             ")};

	JPanel down = new JPanel();
	JPanel End = new JPanel();
	JLabel EndLa = new JLabel("         Area 3 终章           ");
	JButton[] EndBt = { new JButton("       宣战      ") ,
			new JButton("       火并     	"),
			new JButton("       混战        ") ,
			new JButton("       决战         ") ,
			new JButton("       艰难和谈          ") };

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
					JOptionPane.showMessageDialog(null, "You need at least three agents！", "Inadequate fighting capacity ", JOptionPane.INFORMATION_MESSAGE);
					
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
