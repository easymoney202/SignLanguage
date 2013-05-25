package SL;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.imageio.ImageIO;

import SL.SignLanguage.GAME_STATE;

public class HowToScene extends GameScene{
	
	private Image m_img1;
	
	private int m_page = 1;
	
	/**
	 *  Constructor
	 */
	public HowToScene()
	{
		super();
		try
		{
			File file = new File("Images/HowToPlay1.png");
			m_img1 = ImageIO.read(file);
		}
		catch (Exception ex)
		{
			System.out.println("Could not load How To Play scenes.");
		}
	}
	
	/**
	 * Paint method
	 */
	public void paint(Graphics g) {
		switch(m_page)
		{
		case 1:
			g.drawImage(m_img1, 0, 0, null);
			break;
		default:
			// Shouldn't happen
			break;
		}
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_N:
			m_page++;
			if (m_page >= 1)
				m_page = 1;
			break;
		case KeyEvent.VK_Q:
			SignLanguage.Instance.SetGameState(GAME_STATE.Menu);
			break;
		default:
			break;
		}
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyReleased(KeyEvent e) {

	}
}
