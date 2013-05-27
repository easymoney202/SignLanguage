package SL;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.SwingUtilities;

/**
 * This class manages roads and road creation/editing
 * @author Diego
 */
public class RoadManager {

	private ArrayList<Road> m_roads;
	private ArrayList<Intersection> m_intersections;
	
	/**
	 * States for creating roads
	 * @author Diego
	 */
	enum TOOL_STATE
	{
		NONE,
		CREATING_ROAD,
		INTERSECTING_ROADS,
	}

	private TOOL_STATE m_toolState = TOOL_STATE.NONE;

	private Point m_currentStart, m_currentEnd;

	// Class for intersection creation data
	private IntersectTool m_intersectTool;
	
	private boolean m_editing = true;

	/**
	 * Constructor
	 */
	public RoadManager()
	{
		m_roads = new ArrayList<Road>();
		m_intersections = new ArrayList<Intersection>();
		m_currentStart = new Point();
		m_currentEnd = new Point();
		m_intersectTool = new IntersectTool();
	}

	/**
	 * Sets the road manager in edit mode
	 * @param flag
	 */
	public void SetEditMode(boolean flag)
	{
		m_editing = flag;
	}

	/**
	 * Update method
	 */
	public void Update()
	{

	}

	/**
	 * Draws lines to show where roads will end up being
	 * and shows anchor points
	 * @param g
	 */
	public void DrawCreatingRoadTool(Graphics g)
	{
		g.setColor(new Color(250, 0, 0));
		// Draw start point
		drawCircleOutline(g, m_currentStart.x, m_currentStart.y, 5);
		
		// Draws a reference line
		if (m_currentEnd.x != 0 && m_currentEnd.y != 0)
		{
			g.drawLine(m_currentStart.x, m_currentStart.y, m_currentEnd.x, m_currentEnd.y);
			drawCircleOutline(g, m_currentEnd.x, m_currentEnd.y, 5);
		}
	}

	/**
	 * Draws the Editor functionality
	 */
	public void DrawEditor(Graphics g)
	{
		switch (m_toolState)
		{
		case NONE:
			break;
		case CREATING_ROAD:
			DrawCreatingRoadTool(g);
			break;
		}
	}

	/**
	 * Paints all roads and interface
	 * @param g
	 */
	public void paint(Graphics g)
	{
		// Draw all roads
		for (int i = 0; i < m_roads.size(); i++)
		{
			m_roads.get(i).paintSidewalk(g);
		}
		for (int i = 0; i < m_roads.size(); i++)
		{
			m_roads.get(i).paintRoad(g);
		}

		// Draw the editor if needed
		if (m_editing)
			DrawEditor(g);
	}

	/**
	 * Handles creation of roads
	 * @param e
	 */
	public void MouseClick(MouseEvent e)
	{
		if (m_editing == false)
			return;

		PointerInfo pi = MouseInfo.getPointerInfo();
		Point pos = pi.getLocation();
		SwingUtilities.convertPointFromScreen(pos, e.getComponent());

		switch (m_toolState)
		{
		case NONE:
			System.out.println("Obtained Start point: " + pos.x + "," + pos.y);
			m_toolState = TOOL_STATE.CREATING_ROAD;
			// Now get the roads first point
			m_currentStart = pos;
			break;
		case CREATING_ROAD:
			System.out.println("Obtained End point: " + pos.x + "," + pos.y);
			m_currentEnd = pos;
			break;
		case INTERSECTING_ROADS:
			for (int i = 0; i < m_roads.size(); i++)
			{
				if (m_roads.get(i).SelectRoad(pos.x, pos.y, 20))
				{
					if (m_intersectTool.road1 == null)
					{
						m_intersectTool.road1 = m_roads.get(i);
						m_roads.get(i).Select(true);
					}
					else
					{
						// Final road for intersecting has been obtained
						// So now create the intersection
						m_intersectTool.road2 = m_roads.get(i);
						Intersection ci = new Intersection();
						// Intersect the roads
						ci.IntersectRoads(m_intersectTool.road1, m_intersectTool.road2);
						// Deselect road
						m_intersectTool.road1.Select(false);
						// Clear the tool
						m_intersectTool.ClearTool();
						// Add the intersection object
						m_intersections.add(ci);
						// Return to no tool
						m_toolState = TOOL_STATE.NONE;
					}
				}
			}
			break;
		default:
			Road current = new Road(m_currentStart, m_currentEnd);
			m_roads.add(current);
			break;
		}
	}
	
	/**
	 * Handles key presses
	 * @param e
	 */
	public void KeyPressed (KeyEvent e)
	{
		if (m_editing == false)
			return;
		
		switch (m_toolState)
		{
		case NONE:
			// If I is pressed, go into intersecting roads tool
			if (e.getKeyCode() == KeyEvent.VK_I)
				m_toolState = TOOL_STATE.INTERSECTING_ROADS;
			break;
		case CREATING_ROAD:
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				// Add the road since enter has been pressed
				Road current = new Road(m_currentStart, m_currentEnd);
				m_roads.add(current);
				m_toolState = TOOL_STATE.NONE;
				m_currentStart = new Point();
				m_currentEnd = new Point();
			}
			else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				m_toolState = TOOL_STATE.NONE;
				m_currentStart = new Point();
				m_currentEnd = new Point();
			}
			break;
		case INTERSECTING_ROADS:
			break;
		default:
			break;
		}
	}

	/**
	 * Draw a circle
	 * @param g
	 * @param x
	 * @param y
	 * @param radius
	 */
	private void drawCircle(Graphics g, int x, int y, int radius) {
		g.fillOval(x-radius, y-radius, radius*2, radius*2);
	}

	/**
	 * Draws the outline of a circle
	 * @param g
	 * @param x
	 * @param y
	 * @param radius
	 */
	private void drawCircleOutline(Graphics g, int x, int y, int radius)
	{
		g.drawOval(x-radius, y-radius, radius*2, radius*2);
	}

	/**
	 * TOOL CLASSES 
	 */
	// Class for intersect tool
	private class IntersectTool
	{
		public Road road1;
		public Road road2;

		public IntersectTool()
		{
			road1 = null;
			road2 = null;
		}

		// Clears the tool state
		public void ClearTool()
		{
			road1 = null;
			road2 = null;
		}
	}
}
