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

import SL.Road.SignType;

/**
 * Defines all the logic for the Explore scene
 * 
 * @author Diego, Matt & Zac
 */
public class TrafficScene extends GameScene {

	private String m_statusMsg;
	private Image m_background;
	private Image m_hudBar;
	
	private Button m_stopBtn;
	private Button m_stopAWBtn;
	private Button m_owRightBtn;
	private Button m_owLeftBtn;
	private Button m_owUpBtn;
	private Button m_owDownBtn;
	private Button m_nextBtn;
	
	private RoadManager m_roadMgr;
	
	private SignType m_signSelected = SignType.NONE;
	
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
		
		m_stopBtn = new Button("Stop", "Images/stop_btn.png", new Point(610, 30));
		m_stopAWBtn = new Button("Stop AW", "Images/stopaw_btn.png", new Point(710, 30));
		m_owRightBtn = new Button("OW Right", "Images/ow_right_btn.png", new Point(610, 200));
		m_owLeftBtn = new Button("OW Left", "Images/ow_left_btn.png", new Point(710, 200));
		m_owUpBtn = new Button("OW Up", "Images/ow_up_btn.png", new Point(610, 300));
		m_owDownBtn = new Button("OW Down", "Images/ow_down_btn.png", new Point(710, 300));
		m_nextBtn = new Button("Next Turn", "Images/next_btn.png", new Point(660, 450));
	}

	/**
	 * Draws a simple HUD for the Traffic scene
	 * 
	 * @param g
	 */
	public void DrawHUD(Graphics g) {
		g.drawImage(m_hudBar, 600, 0, null);
		
		m_stopBtn.paint(g);
		m_stopAWBtn.paint(g);
		m_owRightBtn.paint(g);
		m_owLeftBtn.paint(g);
		m_owUpBtn.paint(g);
		m_owDownBtn.paint(g);
		m_nextBtn.paint(g);
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

		// Left click
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			// Check all buttons
			if (m_stopBtn.IsClicked(e.getPoint()))
			{
				m_signSelected = SignType.STOP;
			}

			if (m_stopAWBtn.IsClicked(e.getPoint()))
			{
				m_signSelected = SignType.STOP_AW;
			}

			if (m_owUpBtn.IsClicked(e.getPoint()))
			{
				m_signSelected = SignType.UP;
			}

			if (m_owDownBtn.IsClicked(e.getPoint()))
			{
				m_signSelected = SignType.DOWN;
			}

			if (m_owLeftBtn.IsClicked(e.getPoint()))
			{
				m_signSelected = SignType.LEFT;
			}

			if (m_owRightBtn.IsClicked(e.getPoint()))
			{
				m_signSelected = SignType.RIGHT;
			}
			
			if (m_nextBtn.IsClicked(e.getPoint()))
			{
				m_roadMgr.ProcessTurn();
			}
			
			m_roadMgr.MouseClick(e, m_signSelected);
		}
		else if (e.getButton() == MouseEvent.BUTTON3)
		{
			// Right click roads to take out signs
			m_roadMgr.MouseClick(e, SignType.NONE);
		}
	}
}
