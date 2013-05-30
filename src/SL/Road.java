package SL;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.File;

import javax.imageio.ImageIO;

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

	private static Image m_img = null;
	private Point m_position;
	private RoadType m_type;
	private int m_rotation = 0;;

	public boolean Occupied = false;;

	/**
	 * Constructor
	 * @param type
	 */
	public Road(RoadType type, int rot, Point pos)
	{
		if (m_img == null)
		{
			try
			{
				File imgFile = new File("Images/120x120_slim_tiles.png");
				m_img = ImageIO.read(imgFile);
			}
			catch (Exception e){}
		}

		m_type = type;
		m_rotation = rot;
		m_position = new Point();
		m_position.x = pos.x * RoadManager.TILE_SIZE;
		m_position.y = pos.y * RoadManager.TILE_SIZE;
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
		
		g.drawImage(m_img, m_position.x, m_position.y, m_position.x + RoadManager.TILE_SIZE,
					m_position.y + RoadManager.TILE_SIZE, x_pos, y_pos, x_epos, y_epos, null);
	}
}
