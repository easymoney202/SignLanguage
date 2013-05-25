package SL;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;

import SL.SignLanguage.GAME_STATE;

public class GameOverScene extends GameScene
{
	private Image m_img1;
	
	/**
	 *  Constructor
	 */
	public GameOverScene()
	{
		super();
		try
		{
			File file = new File("Images/GameOver.png");
			m_img1 = ImageIO.read(file);
		}
		catch (Exception ex)
		{
			System.out.println("Could not load GameOver scene.");
		}
	}
	
	/**
	 * Paint method
	 */
	public void paint(Graphics g) {
		g.drawImage(m_img1, 0, 0, null);
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
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
