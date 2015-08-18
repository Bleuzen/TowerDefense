import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class a extends JFrame {
//Launcher
	
	private JPanel contentPane;
	
	public static boolean Fullscreen = false;
	
	InfoFrame infoFrame = new InfoFrame();

	public static void main(String[] args) {
		
		if(args.length == 0) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						a frame = new a();
						frame.setVisible(true);
						registerEscListener();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});	
			
		} else {
			
			String argsStr = "";
			for(int i = 0; args.length > i; i++) {
				argsStr += args[i];
			}
			
			if(argsStr.contains("-fullscreen")) {
				Fullscreen = true;
			}
			
			if(argsStr.contains("-720p")) {
				Frame.BigRes = false;
				Frame.main();	
			} else if(argsStr.contains("-1440p")) {
				Frame.BigRes = true;
				Frame.main();
			} else {
				System.out.println();
				
				System.out.println("Argumente:");
				
				System.out.println("-720p");
				System.out.println("-1440p");
				System.out.println("-fullscreen");
				
				System.out.println();
			}
			
		}
		
	}

	public a() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("res" + File.separator + "ico.png"));
		setAlwaysOnTop(true);
		setTitle(Frame.Titel + " - Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 180);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowLostFocus(WindowEvent e) {}
			public void windowGainedFocus(WindowEvent e) {
				if(infoFrame.isVisible()) {
					infoFrame.requestFocus();
				}
				
			}
		});
		
		String[] resForComboBox = {Frame.Size1.width + " x " + Frame.Size1.height,
				Frame.Size2.width + " x " + Frame.Size2.height};
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBoxRes = new JComboBox(resForComboBox);
		comboBoxRes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JCheckBox chckbxVollbild = new JCheckBox("Vollbild");
		chckbxVollbild.setSelected(true);
		chckbxVollbild.setFont(new Font("Cambria Math", Font.PLAIN, 12));
		
		JButton btnSpielen = new JButton("Spielen");
		btnSpielen.setForeground(Color.DARK_GRAY);
		btnSpielen.setBackground(Color.ORANGE);
		btnSpielen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(comboBoxRes.getSelectedItem().equals(resForComboBox[1])) {
					Frame.BigRes = true;
				}
				
				if(chckbxVollbild.isSelected()) {
					Fullscreen = true;
				}
				
				Frame.main();
				
				setVisible(false);
				dispose();
				
			}
		});
		btnSpielen.setFont(UIManager.getFont("InternalFrame.titleFont"));
		btnSpielen.setBounds(234, 101, 180, 30);
		contentPane.add(btnSpielen);
		
		
		comboBoxRes.setBounds(254, 12, 160, 22);
		contentPane.add(comboBoxRes);
		
		JLabel lblAuflsung = new JLabel("Aufl\u00F6sung:");
		lblAuflsung.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAuflsung.setBounds(10, 14, 90, 18);
		contentPane.add(lblAuflsung);
		
		chckbxVollbild.setBackground(Color.GREEN);
		chckbxVollbild.setBounds(6, 112, 90, 23);
		contentPane.add(chckbxVollbild);
		
		JButton btnInfo = new JButton("Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(infoFrame.isVisible()) {
					infoFrame.requestFocus();
				} else {
					infoFrame.setVisible(true);
				}
				
			}
		});
		btnInfo.setBackground(Color.ORANGE);
		btnInfo.setBounds(234, 66, 180, 30);
		contentPane.add(btnInfo);
	}
	
	public static void registerEscListener() {
		
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(
		        new EventQueue() {
		            @Override
					protected void dispatchEvent(AWTEvent event) {
		                if (event instanceof KeyEvent) {
		                    KeyEvent keyEvent = (KeyEvent) event;
		                    if (keyEvent.getID() == KeyEvent.KEY_PRESSED
		                            && (keyEvent).getKeyCode() == KeyEvent.VK_ESCAPE) {
		                    	
		                    	if(Screen.runGame) {
		                    		if(Screen.isGameEnding) {
				                    	if(Screen.saveFile.exists()) {
				                    		Screen.saveFile.delete();
				                    	}
		                    		} else {
				                    	TextFileWriter.writeLineinTextFile(Screen.saveFile.getAbsolutePath(), String.valueOf(Screen.startingLevel));	
		                    		}	
		                    	}
		                    	System.exit(0);
		                    }
		                }
		                super.dispatchEvent(event);
		            }
		        });
		
	}
}
