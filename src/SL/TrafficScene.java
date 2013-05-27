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
	
	/**
	 * States for creating roads
	 * 
	 * @author Diego
	 *
	 */
	enum TOOL_STATE
	{
		NONE,
		CLICK_1,
		CLICK_2
	}
	
	private String m_statusMsg;
	private Image m_background;
	
	private TOOL_STATE m_toolState = TOOL_STATE.NONE;
	private Point m_currentStart, m_currentEnd;
	
	private ArrayList<Road> m_roads;
	
	
	/**
	 * Constructor
	 * 
	 * @param active
	 */
	public TrafficScene() {
		super();
		m_statusMsg = "Status: None";
		m_roads = new ArrayList<Road>();
		try {
			File imageFile = new File("Images/traffic_bg.png");
			m_background = ImageIO.read(imageFile);
		} catch (Exception ex) {
			System.out.println("Failed to load Traffic image");
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

		// Draw all roads
		for (int i = 0; i < m_roads.size(); i++)
		{
			m_roads.get(i).paintSidewalk(g);
		}
		for (int i = 0; i < m_roads.size(); i++)
		{
			m_roads.get(i).paintRoad(g);
		}

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
	
	/***
	 *  Mouse clicks!
	 */
	public void MouseClick(MouseEvent e)
	{
		PointerInfo pi = MouseInfo.getPointerInfo();
		Point pos = pi.getLocation();
		SwingUtilities.convertPointFromScreen(pos, e.getComponent());
		
		switch (m_toolState)
		{
		case NONE:
			System.out.println("Obtained Start point: " + pos.x + "," + pos.y);
			m_toolState = TOOL_STATE.CLICK_1;
			// Now get the roads first point
			m_currentStart = pos;
			break;
		case CLICK_1:
			System.out.println("Obtained End point: " + pos.x + "," + pos.y);
			m_toolState = TOOL_STATE.NONE;
			// Now get the end point
			m_currentEnd = pos;
			Road current = new Road(m_currentStart, m_currentEnd);
			// Add the new road
			m_roads.add(current);
			break;
		case CLICK_2:
			break;
		}
	}
}
