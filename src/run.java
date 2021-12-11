import java.awt.*;
import java.io.Serializable;

import javax.swing.JFrame;

public class run implements Serializable{
	static String title = "ArkNights";
	static Beginning begin = new Beginning(title);
	static Map map = new Map(title);
	static Data data = new Data(title);
	static Base base = new Base(title);
	static Bag bag = new Bag(title);
	static Shop shop = new Shop(title);

	static JFrame[] frames = { begin,map,data,bag,shop,base};

	public static void main(String args[]) throws InterruptedException {

		for (int index = 0; index < frames.length; index++) {
			frames[index].setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frames[index].setPreferredSize(new Dimension(1000,900));
            screencentre(frames[index]);

			frames[index].pack();
		}
		frames[0].setVisible(true);

	}

	public static void screencentre(JFrame frame) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();

		int screenWidth = screenSize.width;

		int screenHeight = screenSize.height;

		
		frame.setLocation(screenWidth/4, screenHeight/15);
	}
}