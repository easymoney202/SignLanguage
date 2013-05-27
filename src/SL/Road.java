package SL;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Defines a road object, this will also handle painting it
 * Roads will be defined with formulas for painting it
 * 
 * @author Diego, Matt & Zac
 */
public class Road {
	enum ROAD_TYPE
	{
		STRAIGHT,
		CURVE
	}

	// Start and end points of the road
	Point m_start, m_end;
	// Intersection to which the road belongs
	// Intersections will only take the end point of the road
	// to be able to connect cars to the other starting roads
	// no need for having two intersections
	Intersection m_intersection;
	
	// This boolean is used to know if the road is being selected by
	// a tool to highlight it in red
	boolean m_selected;
	
	// Line object used to check mouse selection of roads
	Line2D m_roadLine;

	public Road(Point start, Point end)
	{
		m_start = start;
		m_end = end;
		m_selected = false;
		
		System.out.println(end.toString() + "," + start.toString());
		
		// Sets the Line2D object
		m_roadLine = new Line2D.Float();
		m_roadLine.setLine(m_start, m_end);
	}

	/**
	 * Returns the end point
	 * @return
	 */
	public Point GetEndPoint()
	{
		return m_end;
	}

	/**
	 * Sets an end point for a road
	 * this is used in the Intersection class only
	 * @return
	 */
	public void SetEndPoint(Point end)
	{
		m_end = end;
	}

	/**
	 * Returns the start point
	 * @return
	 */
	public Point GetStartPoint()
	{
		return m_start;
	}
	
	/**
	 * Sets the start point for a road
	 * only used in Intersection class
	 * @param start
	 */
	public void SetStartPoint(Point start)
	{
		m_start = start;
	}
	
	/**
	 * Selects/Deselects the road to be highlighted
	 * @param flag
	 */
	public void Select(boolean flag)
	{
		m_selected = flag;
	}

	public void paintSidewalk(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		if (m_selected)
			g.setColor(new Color(250, 0, 0));
		else
			g.setColor(new Color(130, 130, 130));
		g2d.setStroke(new BasicStroke(45));
		g.drawLine(m_start.x, m_start.y, m_end.x, m_end.y);

		// Return stroke to normal
		g2d.setStroke(new BasicStroke(1));
	}

	/**
	 * Paints the road
	 * @param g
	 */
	public void paintRoad(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(100, 100, 100));

		g2d.setStroke(new BasicStroke(30));
		g.drawLine(m_start.x, m_start.y, m_end.x, m_end.y);
		// Return stroke to normal
		g2d.setStroke(new BasicStroke(1));
	}
	
	/**
	 * Sets the intersection of this road
	 * @param i
	 */
	public void SetIntersection(Intersection i)
	{
		m_intersection = i;
	}
	
	/**
	 * Returns the intersection that this road belongs to
	 * @return
	 */
	public Intersection GetIntersection()
	{
		return m_intersection;
	}
	
	/**
	 * This method checks if a line is clicked
	 * @param x
	 * @param y
	 * @param box_size Size of the bounding box for checking collision
	 * @return true if the road matches the mouse
	 */
	public boolean SelectRoad(int x, int y, int box_size)
	{
		boolean result = false;
		int boxX = x - box_size / 2;
		int boxY = y - box_size / 2;

		int width = box_size;
		int height = box_size;	
		
		return m_roadLine.intersects(boxX, boxY, width, height);
	}
}
