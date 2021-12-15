import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// menu
public class Beginning extends JFrame {
    JPanel panel = new JPanel();
	JLabel im = new JLabel(new ImageIcon("Images/1.png"));
	JLabel title = new JLabel("Arknights",JLabel.CENTER);
//	JPanel[] panels = new JPanel[4];
//	JButton[] buttons = {
//		new JButton("Start"),
//		new JButton ("Load"),
//		new JButton("Help"),
//        new JButton("Exit") };
	JFrame help = new JFrame();
	
	public Beginning(String name) {
		super(name);
		setLayout(new GridLayout(3,1));
		// Text Style
		title.setFont(new Font("GameName",Font.BOLD,150) );

		add(title);
		add(im);
		JMenuBar menubar = new JMenuBar();//
		JMenu menu = new JMenu("Game");//

		JMenuItem NewGame;
		JMenuItem Help;
		JMenuItem OpenGame;
		JMenuItem Exit;

		setJMenuBar(menubar);
		NewGame = NewGame();
		Help = Help();
		OpenGame = OpenGame();
		Exit = Exit();


		menu.add(NewGame);
		menu.add(OpenGame);
		menu.add(Help);

		menubar.add(menu);//
		menubar.add(Exit);//

		helpcre();

//		panel.setLayout(new GridLayout(3,2));
//		panel.add(new JPanel());
//		panel.add(new JPanel());
//		for(int index=0;index<buttons.length;index++){
//			buttons[index].addActionListener(new BeginLis());
//			panels[index]=new JPanel();
//			panels[index].add(buttons[index]);
//			panel.add(panels[index]);
//		}
//
//		getContentPane().add(panel);
	}

	private JMenuItem NewGame() {
		JMenuItem tmp = new JMenuItem("NewGame");
		class Newgame implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				run.frames[1].setVisible(true);
			}
		}
		tmp.addActionListener(new Newgame());
		return tmp;
	}
    //帮助界面
	private JMenuItem Help(){
		JMenuItem tmp = new JMenuItem("Help");
		class savegame implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				help.setVisible(true);;
			}
		}
		tmp.addActionListener(new savegame());
		return tmp;
	}
	public void helpcre(){
		JTextArea d = new JTextArea();
    	d.setEditable(false);
		d.setOpaque(false);
		d.setLineWrap(true);
    	d.setFont(new Font("",0,25));
    	d.setText(
"一、游戏剧情\n\n"+
"        该项目是一个回合制RPG 游戏，讲述了动物主角对抗人类，自逃出动物园开始，一路与人类对抗，提升能力，并寻找新队友组成团体，逼得人类不得不与之谈判，最终与人类达成了和平协议的故事。玩家可以通过全鼠标点击操控来体验游戏。   \n\n\n"+

"二、游戏基本流程\n\n"+
"        1.初始玩家拥有三个出战角色，在战斗界面Area1中完成三关后，可以平行推进Area 2-1 2-2  2-3 的各个关卡。\n\n"+
"        2.其间根据Area属性的不同会随机接到新队员请求加入的提示，接受后可以在基地安排它们上阵的顺序。\n\n"+
"        3.关卡会随机掉落不同品质的装备，也可以去商店购买，用于增强角色战斗力。\n\n"+
"        4.如果等级不够高，或者装备品质差，很有可能战斗失败，可以重复挑战先前关卡提升等级实力，积攒金钱，不建议跳关。\n\n"+
"        5.Area 2 全部结束后，进行终章Area 3 的关卡。\n");
    	
    	JButton back = new JButton("Back");
    	back.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
		        help.setVisible(false);
	            }
    	});
    	JPanel s = new JPanel();
    	s.add(back);
    	
    	help.setLayout(new BorderLayout());
    	help.add(d,BorderLayout.CENTER);
    	help.add(s,BorderLayout.SOUTH);
    	
    	help.setPreferredSize(new Dimension(1000,900));
        run.screencentre(help);
        help.pack();
	}

	private JMenuItem OpenGame(){
		JMenuItem tmp = new JMenuItem("Load");
		class OpenGame implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				run.frames[1].setVisible(true);
				S_L.Load();
			}
		}
		tmp.addActionListener(new OpenGame());
		return tmp;
	}

	private JMenuItem Exit(){
		JMenuItem tmp = new JMenuItem("Exit");
		class Exit implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		tmp.addActionListener(new Exit());
		return tmp;
	}

//    private class BeginLis implements ActionListener {
//
//	    public void actionPerformed(ActionEvent e) {
//            if(e.getSource()==buttons[0]){
//            	setVisible(false);
//            	run.frames[1].setVisible(true);
//            }
//            else if(e.getSource()==buttons[1]){
//            	setVisible(false);
//            	run.frames[1].setVisible(true);
//            	S_L.Load();
//            }
//            else if(e.getSource()==buttons[2]){
//                help.setVisible(true);
//            }
//            else if(e.getSource()==buttons[3]){
//            	System.exit(0);
//            }
//		}
//
//	}
}
