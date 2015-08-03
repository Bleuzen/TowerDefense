import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javazoom.jl.player.Player;


@SuppressWarnings("serial")
public class Frame extends JFrame {
	public static String Titel = "Bleuzen´s Tower Defense";
	
	public static Dimension Size1 = new Dimension(1280, 720);
	public static Dimension Size2 = new Dimension(2560, 1440);
	public static boolean BigRes;
	
	public static Player musicPlayer;
	
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
		
		if(a.Music) {
			playBackgroundMusic();
		}		
	}
	
	public static void playBackgroundMusic() {
		new Thread(new Runnable() {
			public void run() {
				try {
					
					File f = new File("sound" + File.separator + "Background.mp3");
					
					FileInputStream musicIn = new FileInputStream(f);
					musicPlayer = new Player(musicIn);
					
					musicPlayer.play();
					
				while(musicPlayer.isComplete()) {
						
						musicIn = new FileInputStream(f);
						musicPlayer = new Player(musicIn);
						
						musicPlayer.play();
						
				}
					
				} catch (Exception e) {
					System.err.println("Fehler: " + e.getMessage());
				}
			}
		}).start();
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
