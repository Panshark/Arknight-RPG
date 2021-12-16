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
    //°ïÖú½çÃæ
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
    	d.setFont(new Font("",0,20));
    	d.setText(
"Story Introduction: \n\n"+
"        This is a Turn-Based RPG game based on Arknights. You can just use your mouse to play this game. Leading your agents and enjoying your adventure.\n\n\n"+

"How to play: \n\n"+
"        1. You have three initial agents in the very beginning. And we you finish area 0, you can challenge whatever 1-1, 2-1, 3-2. They are in the same difficulty;\n\n"+
"        2. You will meet new agents. You have to weigh which agents you should use, because you can only use three in one combat;\n\n"+
"        3. It will randomly drop different quality equipments in each battle. The equipments can also be purchased from the store, used to enhance the character's combat effectiveness;\n\n"+
"        4. If you can't continue the story, try repeating the previous chapters to gain more strength;\n\n"+
"        5. After middle Areas are completed, proceed to bottom Area for the final chapter.\n");
    	
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
