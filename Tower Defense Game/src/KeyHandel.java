import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class KeyHandel implements MouseMotionListener, MouseListener, KeyListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		Screen.store.click(e.getButton());
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
		if(Frame.BigRes) {
			Screen.mse = new Point((e.getX()) + ((Frame.Size2.width - Screen.myWidth)/2), (e.getY()) + ((Frame.Size2.height - (Screen.myHeight)) - (Frame.Size2.width - Screen.myWidth)/2));	
		} else {
			Screen.mse = new Point(e.getX() - ((Frame.Size1.width - Screen.myWidth) / 2), e.getY() - (Frame.Size1.height - Screen.myHeight - (Frame.Size1.width - Screen.myWidth) / 2));
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(Frame.BigRes) {
			Screen.mse = new Point((e.getX()) - ((Frame.Size2.width - Screen.myWidth)/2), (e.getY()) - ((Frame.Size2.height - (Screen.myHeight)) - (Frame.Size2.width - Screen.myWidth)/2));	
		} else {
			Screen.mse = new Point(e.getX() - ((Frame.Size1.width - Screen.myWidth) / 2), e.getY() - (Frame.Size1.height - Screen.myHeight - (Frame.Size1.width - Screen.myWidth) / 2));
		}

	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(Screen.runGame) {
				Screen.runGame = false;
			} else {
				Screen.runGame = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {
		
	}

}
