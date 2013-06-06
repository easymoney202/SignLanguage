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
 * @author Diego, Zac, Matt
 */
public class RoadManager {

	public static int TILE_SIZE = 120;
	public static int TILE_W = 5;
	public static int TILE_H = 5;
	private static int LEVEL_COUNT = 1;
	private int level[][];
	public int cars_saved = 0;
	private int till_next_level = 10;

	// 0 - Nothing
	// 1-4 - Straight
	// 5-8 - Curve
	// 9-12 - Intersection 3
	// 13 - Intersection 4
	private int level1[][] = {
			{1,9,5,2,0},
			{0,2,2,2,0},
			{1,10,7,13,1},
			{0,12,1,10,0},
			{0,2,0,2,0}
	};
	private int level2[][] = {
			{1,9,5,2,0},
			{0,2,2,2,0},
			{0,2,7,13,5},
			{0,2,0,2,2},
			{0,2,0,2,2}
	};
	private int level3[][] = {
			{0,0,2,0,0},
			{1,9,13,9,1},
			{0,12,13,10,0},
			{1,11,13,11,1},
			{0,0,2,0,0}
	};
	
	
	private Road m_roads[][];
	
	public int CurrentTurn = 0;

	private void ClearRoads() {
		for (int y = 0; y < TILE_H; y++)
		{
			for (int x = 0; x < TILE_W; x++)
			{
				if(m_roads[y][x]!= null)
					m_roads[y][x] = null;
			}
		}
	}
	
	private void ClearExplosions() {
		for (int y = 0; y < TILE_H; y++)
		{
			for (int x = 0; x < TILE_W; x++)
			{
				if(m_roads[y][x]!= null) {
					if(m_roads[y][x].Explosion == true)
						m_roads[y][x].Explosion = false;
				}
			}
		}
	}
	
	/**
	 * Generates all roads based on the level array
	 */
	public void GenerateLevel()
	{
		ClearRoads();
		if(LEVEL_COUNT == 1)
			level = level1;
		if(LEVEL_COUNT == 2)
			level = level2;
		if(LEVEL_COUNT == 3)
			level = level3;
		
		for (int y = 0; y < TILE_H; y++)
		{
			for (int x = 0; x < TILE_W; x++)
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
		if(LEVEL_COUNT == 1) {
			// Set the top tile as spawn point to test
			m_roads[0][0].SetSpawnPoint(true);
			m_roads[0][0].Frequency = 3;
			m_roads[0][0].SetSign(SignType.RIGHT);
			//m_roads[0][0].SignVisible = false;
			m_roads[0][0].SignRemoveable = false;
		
			m_roads[2][0].SetSpawnPoint(true);
			m_roads[2][0].Frequency = 5;
			m_roads[2][0].SetSign(SignType.RIGHT);
			m_roads[2][0].SignRemoveable = false;
		
			m_roads[3][3].SetSign(SignType.UP);
			m_roads[3][3].SignRemoveable = false;
		}
		if(LEVEL_COUNT == 2) {
			// Set the top tile as spawn point to test
			m_roads[0][0].SetSpawnPoint(true);
			m_roads[0][0].Frequency = 3;
			m_roads[0][0].SetSign(SignType.RIGHT);
			m_roads[0][0].SignRemoveable = false; 
			m_roads[0][3].SetSpawnPoint(true);
			m_roads[0][3].Frequency = 3;
			m_roads[0][3].SetSign(SignType.DOWN);
			m_roads[0][3].SignRemoveable = false;
		}
		if(LEVEL_COUNT == 3) {
			// Set the top tile as spawn point to test
			m_roads[0][2].SetSpawnPoint(true);
			m_roads[0][2].Frequency = 3;
			m_roads[0][2].SetSign(SignType.DOWN);
			m_roads[0][2].SignRemoveable = false; 
			m_roads[1][0].SetSpawnPoint(true);
			m_roads[1][0].Frequency = 3;
			m_roads[1][0].SetSign(SignType.RIGHT);
			m_roads[1][0].SignRemoveable = false;
			m_roads[1][4].SetSpawnPoint(true);
			m_roads[1][4].Frequency = 3;
			m_roads[1][4].SetSign(SignType.LEFT);
			m_roads[1][4].SignRemoveable = false;
			m_roads[3][0].SetSpawnPoint(true);
			m_roads[3][0].Frequency = 3;
			m_roads[3][0].SetSign(SignType.RIGHT);
			m_roads[3][0].SignRemoveable = false;
			m_roads[3][4].SetSpawnPoint(true);
			m_roads[3][4].Frequency = 3;
			m_roads[3][4].SetSign(SignType.LEFT);
			m_roads[3][4].SignRemoveable = false; 
		}
		
	}

	public RoadManager()
	{
		m_roads = new Road[TILE_W][TILE_H];
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
		for (int i = 0; i < TILE_H; i++)
		{
			for (int j = 0; j < TILE_W; j++)
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
		if (x >= TILE_W)
			x = TILE_W - 1;
		if (y >= TILE_H)
			y = TILE_H - 1;
		
		//System.out.println("Returning road " + x +"," + y);
		
		if (m_roads[y][x] == null) {
			//System.out.println("Whoops! Null road!  Hope he has good insurance!");
			return null;
		}
		
		return m_roads[y][x];
	}
	
	/**
	 * Process a "Turn" in which cars moves
	 */
	public void ProcessTurn()
	{
		ClearExplosions();
		for (int i = 0; i < TILE_H; i++)
		{
			for (int j = 0; j < TILE_W; j++)
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
		// Check if the level is beat
		if(cars_saved >= till_next_level){
			cars_saved = 0;
			CurrentTurn = 0;
			LEVEL_COUNT++;
			if(LEVEL_COUNT > 3)
				LEVEL_COUNT = 1;
			GenerateLevel();
		}
			
	}

	/**
	 * Handles key presses
	 * @param e
	 */
	public void KeyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			// Process a turn (TEST FOR NOW)
			ProcessTurn();
		}
	}

	// Sets signs on the road
	public void MouseClick(MouseEvent e, SignType sign)
	{
		for (int i = 0; i < TILE_H; i++)
		{
			for (int j = 0; j < TILE_W; j++)
			{
				if (m_roads[i][j] != null)
				{
					if (m_roads[i][j].IsClicked(e.getPoint()) && m_roads[i][j].SignRemoveable)
						m_roads[i][j].SetSign(sign);
					if (e.getButton() == MouseEvent.BUTTON2)
						m_roads[i][j].DumpConnectionInfo();
				}
			}
		}
	}
}
