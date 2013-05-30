package SL;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.SwingUtilities;
import SL.Road.RoadType;

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

	private ArrayList<Road> m_roads;


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
					System.out.println("Pos: " + x + "," + y + "; Value: " + level[y][x]);
					System.out.println("Type: " + type);
					System.out.println("Rot: " + rot);
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

					cr = new Road(r_type, rot, pos);

					if (cr != null)
						m_roads.add(cr);
				}
			}
		}
	}

	public RoadManager()
	{
		m_roads = new ArrayList<Road>();
		Point pos = new Point();
		pos.x = 2;
		pos.y = 1;

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
		for (int i = 0; i < m_roads.size(); i++)
		{
			m_roads.get(i).paint(g);
		}
	}

	public void KeyPressed(KeyEvent e)
	{

	}

	public void MouseClick(MouseEvent e)
	{

	}
}
