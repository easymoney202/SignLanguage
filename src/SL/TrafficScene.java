package SL;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Defines all the logic for the Explore scene
 * 
 * @author Diego, Matt & Zac
 */
public class TrafficScene extends GameScene {
	private String m_statusMsg;
	private Image m_background;
	/**
	 * Constructor
	 * 
	 * @param active
	 */
	public TrafficScene() {
		super();
		m_statusMsg = "Status: None";
		try {
			File imageFile = new File("Images/traffic_bg.png");
			m_background = ImageIO.read(imageFile);
		} catch (Exception ex) {
			System.out.println("Failed to load Explore background image or Book image");
		}
	}

	/**
	 * Draws a simple HUD for the Traffic scene
	 * 
	 * @param g
	 */
	public void DrawHUD(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	}

	/**
	 * Paint method for the Traffic Scene
	 */
	public void paint(Graphics g) {
		g.drawImage(m_background, 0, 0, null);

		DrawHUD(g);
	}

	/**
	 * Sets a status message in the HUD
	 * 
	 * @param msg
	 */
	public void SetStatusMsg(String msg) {
		m_statusMsg = msg;
	}

	/**
	 * Resets the status msg
	 */
	public void ResetStatusMsg() {
		m_statusMsg = "Status: None";
	}

	/**
	 * Update method
	 */
	public void Update() {
	
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyPressed(KeyEvent e) {
		System.out.println("Key pressed: " + e.getKeyChar());
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyReleased(KeyEvent e) {
		System.out.println("Key released: " + e.getKeyChar());
	}
}
