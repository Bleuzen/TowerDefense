import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class Frame extends JFrame {
	public static String Titel = "Bleuzen´s Tower Defense";
	
	public static Dimension Size1 = new Dimension(1280, 720);
	public static Dimension Size2 = new Dimension(2560, 1440);
	public static boolean BigRes;
	
	public static int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	public Frame() {
		
		if(BigRes) {
			if(a.Fullscreen) {
				setUndecorated(true);
				setSize(ScreenWidth, ScreenHeight);
			} else {
				setSize(Size2);		
			}
		} else {
			if(a.Fullscreen) {
				setUndecorated(true);
				setSize(ScreenWidth, ScreenHeight);
			} else {
				setSize(Size1);	
			}
		}
		
		setTitle(Titel);
		setResizable(false);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		init();
	}
	
	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		Screen screen = new Screen(this);
		add(screen);
			
	}
	
	public static void main() {
		Frame frame = new Frame();
		frame.setAlwaysOnTop(true);
		if(BigRes) {
			if(ScreenHeight >= Size2.getHeight() && ScreenWidth >= Size2.getWidth()) {
				if(a.Fullscreen) {
					Size2 = new Dimension(ScreenWidth, ScreenHeight);	
				}
				frame.setVisible(true);	
			} else {
				JOptionPane.showMessageDialog(frame, "Deine Monitorauflösung ist zu niedrig!", Titel, JOptionPane.ERROR_MESSAGE);
				System.exit(100);
			}
		} else {
			if(ScreenHeight >= Size1.getHeight() && ScreenWidth >= Size1.getWidth()) {
				if(a.Fullscreen) {
					Size1 = new Dimension(ScreenWidth, ScreenHeight);	
				}
				frame.setVisible(true);	
			} else {
				JOptionPane.showMessageDialog(frame, "Deine Monitorauflösung ist zu niedrig!", Titel, JOptionPane.ERROR_MESSAGE);
				System.exit(100);
			}	
		}
	}

}
