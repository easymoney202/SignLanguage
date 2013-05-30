package SL;

import java.awt.event.KeyEvent;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

import SL.SignLanguage.GAME_STATE;

/**
 * Class for the starting point of the game A simple menu with start and exit
 * with a title screen
 * 
 * @author Matt, Diego & Zac
 */
public class MenuScene extends GameScene {
	private int m_selection;
	private Image m_background;
	private int m_numberselections;

	/**
	 * Constructor
	 * 
	 * @param active
	 */
	public MenuScene() {
		super();
		m_selection = 0;
		m_numberselections = 3;
		try {
			File imageFile = new File("Images/Title.png");
			m_background = ImageIO.read(imageFile);
		} catch (Exception ex) {
			System.out.println("Failed to load Title screen image");
		}
	}

	/**
	 * Paint method
	 */
	public void paint(Graphics g) {
		g.drawImage(m_background, 0, 0, null);
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("ARIAL", Font.BOLD, 35);
		m_numberselections = 3;
		int x_o = 100, y_o = 100;
		switch(m_selection)
		{
		case 0:
			g2.setFont(font);
			g2.setColor(Color.RED);
			g2.drawString("> Start Game <", x_o + 180, y_o + 230);
			g2.setColor(Color.WHITE);
			g2.drawString("   How To Play ", x_o + 180, y_o + 300);
			g2.drawString("   Exit  ", x_o + 230, y_o + 370);
			break;
		case 1:
			g2.setFont(font);
			g2.setColor(Color.WHITE);
			g2.drawString("   Start Game ", x_o + 180, y_o + 230);
			g2.drawString("   Exit  ", 230 + x_o, y_o + 370);
			g2.setColor(Color.RED);
			g2.drawString("> How To Play <", x_o + 180, y_o + 300);
			break;
		case 2:
			g2.setFont(font);
			g2.setColor(Color.WHITE);
			g2.drawString("   Start Game  ", x_o + 180, y_o + 230);
			g2.drawString("   How To Play ", x_o + 180, y_o + 300);
			g2.setColor(Color.RED);
			g2.drawString("> Exit <", x_o + 230, y_o + 370);
			break;
		}
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_UP:
			if (m_selection > 0) {
				m_selection--;
			} else {
				m_selection = m_numberselections - 1;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (m_selection < m_numberselections - 1) {
				m_selection++;
			} else {
				m_selection = 0;
			}
			break;
		case KeyEvent.VK_SPACE:
			switch (m_selection)
			{
			case 0:
				SignLanguage.Instance.SetGameState(GAME_STATE.Traffic);
				break;
			case 1:
				SignLanguage.Instance.SetGameState(GAME_STATE.HowTo);
				break;
			case 2:
				System.exit(0);
				break;
			}
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
