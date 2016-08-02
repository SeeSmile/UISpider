package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {
	
	private static final int WINDOWS_WIDTH = 700;
	private static final int WINDOWS_HEIGHT = 450;
	
	public static void initWindows(JFrame jFrame) {
		jFrame.setSize(WINDOWS_WIDTH, WINDOWS_HEIGHT);
		jFrame.setResizable(false);
		int windowWidth = jFrame.getWidth(); 
		int windowHeight = jFrame.getHeight(); 
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		jFrame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);
	}
}
