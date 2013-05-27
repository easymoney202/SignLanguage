package SL;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.SwingUtilities;
import javax.swing.JComboBox.KeySelectionManager;

/**
 * This class manages roads and road creation/editing
 * @author Diego
 */
public class RoadManager {

	private ArrayList<Road> m_roads;

	/**
	 * States for creating roads
	 * @author Diego
	 */
	enum TOOL_STATE
	{
		NONE,
		CREATING_ROAD,
		FINISH_ROAD_CREATE,
	}

	private TOOL_STATE m_toolState = TOOL_STATE.NONE;

	private Point m_currentStart, m_currentEnd;

	private boolean m_editing = true;

	/**
	 * Constructor
	 */
	public RoadManager()
	{
		m_roads = new ArrayList<Road>();
		m_currentStart = new Point();
		m_currentEnd = new Point();
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
		case FINISH_ROAD_CREATE:
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
			break;
		case FINISH_ROAD_CREATE:
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
}
