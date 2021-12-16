
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.*;

public class ActMenu extends JPanel implements Serializable {
	JFrame options = new JFrame("Options");
	JFrame Loads = new JFrame("Loads");
	JFrame Saves = new JFrame("Saves");

	JButton[] buttons = { new JButton("Scenes"), new JButton("Combat Group"), new JButton("Bag"), new JButton("Shop"),
			new JButton("Lounge"), new JButton("Options") };

	JButton[] ops = { new JButton("Continue"),new JButton("Save"), new JButton("Load"),new JButton("Help")};

	JButton[] Load = { new JButton("Load 1"),new JButton("Load 2"), new JButton("Load 3"),new JButton("Load 4")};

	JButton[] Save = { new JButton("Load 1"),new JButton("Load 2"), new JButton("Load 3"),new JButton("Load 4")};
	
	public ActMenu() {
 
		setLayout(new FlowLayout());

		options(options);

		Loads(Loads);

		Saves(Saves);
		
		for (int index = 0; index < buttons.length; index++)
			buttons[index].addActionListener(new ActLis());

		for (int index = 0; index < buttons.length; index++)
			add(buttons[index]);

	}

	public void shutdown() {
		for (int index = 0; index < run.frames.length; index++)
			run.frames[index].setVisible(false);
	}

	private class ActLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buttons[0]) {
				shutdown();
				run.frames[1].setVisible(true);
			} else if (e.getSource() == buttons[1]) {
				shutdown();
				run.frames[2].setVisible(true);
			} else if (e.getSource() == buttons[2]) {
				shutdown();
				run.frames[3].setVisible(true);
			} else if (e.getSource() == buttons[3]) {
				shutdown();
				run.frames[4].setVisible(true);
			} else if (e.getSource() == buttons[4]) {
				shutdown();
				run.frames[5].setVisible(true);
				
			} else if (e.getSource() == buttons[5]) {
				options.setVisible(true);

			}
		}
	}
	
	//Option button
	
	public void options(JFrame options) {
		
		JPanel[] three = new JPanel[4];
		
		options.setLayout(new GridLayout(2, 1));
		for (int index = 0; index < three.length; index++) {
			three[index] = new JPanel();
			ops[index].addActionListener(new OpLis());
			three[index].add(ops[index]);
			options.add(three[index]);
		}
		options.setPreferredSize(new Dimension(300, 200));
		run.screencentre(options);
		options.pack();
		
		
	}

	public void Loads(JFrame options) {

		JPanel[] three = new JPanel[4];

		options.setLayout(new GridLayout(2, 1));
		for (int index = 0; index < three.length; index++) {
			three[index] = new JPanel();
			Load[index].addActionListener(new LoadsList());
			three[index].add(Load[index]);
			options.add(three[index]);
		}
		options.setPreferredSize(new Dimension(300, 200));
		run.screencentre(options);
		options.pack();
	}

	public void Saves(JFrame options) {

		JPanel[] three = new JPanel[4];

		options.setLayout(new GridLayout(2, 1));
		for (int index = 0; index < three.length; index++) {
			three[index] = new JPanel();
			Save[index].addActionListener(new SaveList());
			three[index].add(Save[index]);
			options.add(three[index]);
		}
		options.setPreferredSize(new Dimension(300, 200));
		run.screencentre(options);
		options.pack();
	}

	private class OpLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ops[0]) {
				options.setVisible(false);
			}
			else if(e.getSource()==ops[1]){
				Saves.setVisible(true);
			}
			else if(e.getSource()==ops[2]){
				Loads.setVisible(true);
			}
			else if(e.getSource()==ops[3]){
				run.begin.help.setVisible(true);
				options.setVisible(false);
			}
		}
	}

	private class LoadsList implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Load[0]) {
				S_L.Load();
				JOptionPane.showMessageDialog(null, "Game Loaded", "Load", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
			else if(e.getSource()==Load[1]){
				S_L.Load();
				JOptionPane.showMessageDialog(null, "Game Loaded", "Load", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
			else if(e.getSource()==Load[2]){
				S_L.Load();
				JOptionPane.showMessageDialog(null, "Game Loaded", "Load", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
			else if(e.getSource()==Load[3]){
				S_L.Load();
				JOptionPane.showMessageDialog(null, "Game Loaded", "Load", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
		}
	}

	private class SaveList implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == Save[0]) {
				S_L.Save();
				JOptionPane.showMessageDialog(null, "Game Saved", "Save", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
			else if(e.getSource()==Save[1]){
				S_L.Save();
				JOptionPane.showMessageDialog(null, "Game Saved", "Save", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
			else if(e.getSource()==Save[2]){
				S_L.Save();
				JOptionPane.showMessageDialog(null, "Game Saved", "Save", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
			else if(e.getSource()==Save[3]){
				S_L.Save();
				JOptionPane.showMessageDialog(null, "Game Saved", "Save", JOptionPane.INFORMATION_MESSAGE);
				options.setVisible(false);
			}
		}
	}
	
}
