package SL;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

/**
 * Defines all the logic for the Explore scene
 * 
 * @author Diego, Matt & Zac
 */
public class TrafficScene extends GameScene {

	private String m_statusMsg;
	private Image m_background;
	private Image m_hudBar;
	
	private RoadManager m_roadMgr;
	
	/**
	 * Constructor
	 * 
	 * @param active
	 */
	public TrafficScene() {
		super();
		m_roadMgr = new RoadManager();
		m_statusMsg = "Status: None";
		try {
			File imageFile = new File("Images/BG1.png");
			m_background = ImageIO.read(imageFile);
			imageFile = new File("Images/HUDBar.png");
			m_hudBar = ImageIO.read(imageFile);
		} catch (Exception ex) {
			System.out.println("Failed to load Traffic Scene images");
		}
	}

	/**
	 * Draws a simple HUD for the Traffic scene
	 * 
	 * @param g
	 */
	public void DrawHUD(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(m_hudBar, 600, 0, null);
	}

	/**
	 * Paint method for the Traffic Scene
	 */
	public void paint(Graphics g) {
		g.drawImage(m_background, 0, 0, null);

		m_roadMgr.paint(g);

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
		m_roadMgr.Update();
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyPressed(KeyEvent e) {
		m_roadMgr.KeyPressed(e);
	}

	/**
	 * Obtains input to the scene
	 */
	public void KeyReleased(KeyEvent e) {
	}
	
	/***
	 *  Mouse clicks!
	 */
	public void MouseClick(MouseEvent e)
	{
		m_roadMgr.MouseClick(e);
	}
}
