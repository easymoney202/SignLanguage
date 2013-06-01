package SL;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;

import javax.imageio.ImageIO;

import SL.Connection.ConDir;
import SL.Connection.ConType;

/**
 * Defines a road object, this will also handle painting it
 * Roads will be defined with formulas for painting it
 * 
 * @author Diego, Matt & Zac
 */
public class Road {
	// Type of road
	enum RoadType
	{
		STRAIGHT,
		CURVE,
		INT_3,
		INT_4
	}
	
	// Types of signs that can be on the road
	enum SignType
	{
		NONE,
		STOP,
		STOP_AW,
		UP,
		DOWN,
		LEFT,
		RIGHT,
	}

	private static Image m_img = null;
	private Point m_position;
	private RoadType m_type;
	private int m_rotation = 0;
	
	// IsSpawn and spawn frequency (Turn wise)
	public boolean IsSpawn = false;
	public int Frequency = 0;

	public boolean Occupied = false;
	public boolean HasSign = false;
	
	private SignType m_sign;
	private static Image m_stop = null;
	private static Image m_stopAW = null;
	private static Image m_owUp = null;
	private static Image m_owDown = null;
	private static Image m_owLeft = null;
	private static Image m_owRight = null;
	
	private Image m_currentSign = null;
	
	private Connection m_uCon, m_dCon, m_lCon, m_rCon;
	
	private RoadManager m_manager;

	/**
	 * Constructor
	 * @param type
	 */
	public Road(RoadType type, int rot, Point pos, RoadManager manager)
	{
		if (m_img == null)
			m_img = LoadImage("Images/120x120_slim_tiles.png");
		
		if (m_stop == null)
			m_stop = LoadImage("Images/stop.png");
		if (m_stopAW == null)
			m_stopAW = LoadImage("Images/stop_allway.png");
		if (m_owUp == null)
			m_owUp = LoadImage("Images/oneway_up.png");
		if (m_owDown == null)
			m_owDown = LoadImage("Images/oneway_down.png");
		if (m_owLeft == null)
			m_owLeft = LoadImage("Images/oneway_left.png");
		if (m_owRight == null)
			m_owRight = LoadImage("Images/oneway_right.png");

		m_rotation = rot;
		m_position = new Point();
		m_position.x = pos.x * RoadManager.TILE_SIZE;
		m_position.y = pos.y * RoadManager.TILE_SIZE;
		m_sign = SignType.NONE;
		m_manager = manager;
		
		m_type = type;
		// Initialize logic for the instructions of the type
		m_uCon = new Connection(ConDir.UP);
		m_dCon = new Connection(ConDir.DOWN);
		m_lCon = new Connection(ConDir.LEFT);
		m_rCon = new Connection(ConDir.RIGHT);
		
		PopulateConnections();
	}
	
	/**
	 * Populate the connection objects
	 */
	private void PopulateConnections()
	{
		// Create standard behavior for unrotated tiles
		switch (m_type)
		{
		case STRAIGHT:
			m_uCon.Type = ConType.NONE;
			m_dCon.Type = ConType.NONE;
			m_lCon.Type = ConType.IN_OUT;
			m_rCon.Type = ConType.IN_OUT;
			break;
		case CURVE:
			m_uCon.Type = ConType.NONE;
			m_dCon.Type = ConType.IN_OUT;
			m_lCon.Type = ConType.IN_OUT;
			m_rCon.Type = ConType.NONE;
			break;
		case INT_3:
			m_uCon.Type = ConType.NONE;
			m_dCon.Type = ConType.IN_OUT;
			m_lCon.Type = ConType.IN_OUT;
			m_rCon.Type = ConType.IN_OUT;
			break;
		case INT_4:
			m_uCon.Type = ConType.IN_OUT;
			m_dCon.Type = ConType.IN_OUT;
			m_lCon.Type = ConType.IN_OUT;
			m_rCon.Type = ConType.IN_OUT;
			break;
		default:
			break;
		}
		
		Connection t_uC = m_uCon;
		Connection t_dC = m_dCon;
		Connection t_lC = m_lCon;
		Connection t_rC = m_rCon;
		
		// Switch rotations accordingly
		switch (m_rotation)
		{
		case 1:
			m_uCon = t_lC;
			m_lCon = t_dC;
			m_dCon = t_rC;
			m_rCon = t_uC;
			break;
		case 2:
			m_uCon = t_dC;
			m_lCon = t_rC;
			m_dCon = t_uC;
			m_rCon = t_lC;
			break;
		case 3:
			m_uCon = t_rC;
			m_lCon = t_uC;
			m_dCon = t_lC;
			m_rCon = t_dC;
			break;
		default:
			break;
		}
	}
	
	/**
	 * Sets this road as a spawn point for cars
	 * @param flag
	 */
	void SetSpawnPoint(boolean flag)
	{
		IsSpawn = flag;
		
		// Initialize data here
	}

	/**
	 * Paints the road
	 * @param g
	 */
	public void paint (Graphics g)
	{
		int x_offset = 0;
		switch (m_type)
		{
		case STRAIGHT:
			x_offset = 0;
			break;
		case CURVE:
			x_offset = 1;
			break;
		case INT_3:
			x_offset = 2;
			break;
		case INT_4:
			x_offset = 3;
			break;
		default:
			break;
		}

		// Source cut
		int x_pos = x_offset * RoadManager.TILE_SIZE;
		int y_pos = m_rotation * RoadManager.TILE_SIZE;
		int x_epos = x_pos + RoadManager.TILE_SIZE;
		int y_epos = y_pos + RoadManager.TILE_SIZE;

		// Draws the road
		g.drawImage(m_img, m_position.x, m_position.y, m_position.x + RoadManager.TILE_SIZE,
				m_position.y + RoadManager.TILE_SIZE, x_pos, y_pos, x_epos, y_epos, null);
		
		// Draw the sign
		if (m_currentSign != null)
		{
			g.drawImage(m_currentSign, m_position.x, m_position.y, null);
		}
	}
	
	// Loads a image for a sign
	private Image LoadImage(String filename)
	{
		Image img = null;
		try
		{
			File imgFile = new File(filename);
			img = ImageIO.read(imgFile);
		}
		catch (Exception e)
		{
			System.out.println("Problem loading: " + filename);
		}
		
		return img;
	}
	
	// Sets a sign to the road
	public void SetSign(SignType sign)
	{
		m_sign = sign;
		
		if (sign != SignType.NONE)
			HasSign = true;
		
		switch (sign)
		{
		case STOP:
			m_currentSign = m_stop;
			break;
		case STOP_AW:
			m_currentSign = m_stopAW;
			break;
		case UP:
			m_currentSign = m_owUp;
			break;
		case DOWN:
			m_currentSign = m_owDown;
			break;
		case LEFT:
			m_currentSign = m_owLeft;
			break;
		case RIGHT:
			m_currentSign = m_owRight;
			break;
		default:
			m_currentSign = null;
			break;
		}
	}
	
	/**
	 * Processes a turn for the cars movement and sign control
	 */
	public void ProcessTurn()
	{
		if (IsSpawn)
		{
			if (!Occupied && m_manager.CurrentTurn % Frequency == 0)
			{
				// This turn we should spawn a car
				Occupied = true;
				
				System.out.println("Car has spawned!");
				// TO-DO: Add car creation
			}
		}
		
	}
	
	/**
	 * Dumps the way connections are established.
	 * This is for debugging
	 */
	public void DumpConnectionInfo()
	{
		System.out.println("Up Connection is: " + m_uCon.ToString());
		System.out.println("Down Connection is: " + m_dCon.ToString());
		System.out.println("Left Connection is: " + m_lCon.ToString());
		System.out.println("Right Connection is: " + m_rCon.ToString());
	}

	// Returns true if road has been selected
	public boolean IsClicked(Point mousePos)
	{
		if (mousePos.x >= m_position.x && mousePos.x <= m_position.x + RoadManager.TILE_SIZE)
		{
			if (mousePos.y >= m_position.y && mousePos.y <= m_position.y + RoadManager.TILE_SIZE)
			{
				//DumpConnectionInfo();
				return true;
			}
		}
		return false;
	}
}
