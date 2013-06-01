package SL;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.SwingUtilities;
import SL.Road.RoadType;
import SL.Road.SignType;

/**
 * This class manages roads and road creation/editing
 * @author Diego
 */
public class RoadManager {

	public static int TILE_SIZE = 120;

	// 0 - Nothing
	// 1-2 - Straight
	// 5-8 - Curve
	// 9-13 - Intersection 3
	// 11 - Intersection 4
	private int level[][] = {
			{1,9,5,2,0},
			{0,2,2,2,0},
			{0,2,7,13,1},
			{0,2,0,2,0},
			{0,2,0,2,0}};
	
	private Road m_roads[][];
	
	public int CurrentTurn = 0;

	/**
	 * Generates all roads based on the level array
	 */
	public void GenerateLevel()
	{
		for (int y = 0; y < 5; y++)
		{
			for (int x = 0; x < 5; x++)
			{
				if (level[y][x] != 0)
				{
					Road cr = null;
					Point pos = new Point(x,y);
					int type = (level[y][x]-1)/4;
					int rot = (level[y][x]-1)%4;
					RoadType r_type = null;
					
					switch (type)
					{
					case 0:
						r_type = RoadType.STRAIGHT;
						break;
					case 1:
						r_type = RoadType.CURVE;
						break;
					case 2:
						r_type = RoadType.INT_3;
						break;
					case 3:
						r_type = RoadType.INT_4;
					default:
						break;
					}

					cr = new Road(r_type, rot, pos, this);

					if (cr != null)
						m_roads[y][x] = cr;
				}
			}
		}
		
		// Set the top tile as spawn point to test
		m_roads[0][0].SetSpawnPoint(true);
		m_roads[0][0].Frequency = 2;
		m_roads[0][0].SetSign(SignType.RIGHT);
		m_roads[0][0].SignVisible = false;
	}

	public RoadManager()
	{
		m_roads = new Road[5][5];
		GenerateLevel();
	}

	// Updates
	public void Update()
	{

	}

	public void LoadLevel()
	{

	}

	// Paints the roads
	public void paint(Graphics g)
	{
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if (m_roads[i][j] != null)
					m_roads[i][j].paint(g);
			}
		}
	}
	
	/**
	 * Gets a road in a position
	 * @param x x position
	 * @param y y position
	 * @return
	 */
	public Road GetRoad(int x, int y)
	{
		// Make sure we don't screw the array
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		if (x >= 5)
			x = 4;
		if (y >= 5)
			y = 4;
		
		return m_roads[y][x];
	}
	
	/**
	 * Process a "Turn" in which cars moves
	 */
	public void ProcessTurn()
	{
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if (m_roads[i][j] != null)
				{
					// Process the turn for each road
					m_roads[i][j].ProcessTurn();
				}
			}
		}
		
		// Add one to the current turn
		CurrentTurn++;
	}

	/**
	 * Handles key presses
	 * @param e
	 */
	public void KeyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			// Process a turn (TEST FOR NOW)
			ProcessTurn();
		}
	}

	// Sets signs on the road
	public void MouseClick(MouseEvent e, SignType sign)
	{
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if (m_roads[i][j] != null)
				{
					if (m_roads[i][j].IsClicked(e.getPoint()))
						m_roads[i][j].SetSign(sign);
				}
			}
		}
	}
}
