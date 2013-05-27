package SL;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.*;

import javax.imageio.ImageIO;

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
	
	private static Image m_roadImg = null;
	
	Point m_start, m_end;
	float m_sampling;
	
	public Road(Point start, Point end)
	{
		// Load the road image only once for all Road instances
		if (m_roadImg == null)
		{
			try {
				File imageFile = new File("Images/Road.png");
				m_roadImg = ImageIO.read(imageFile);
			} catch (Exception ex) {
				System.out.println("Failed to load Road image");
			}
		}
		
		m_start = start;
		m_end = end;
		m_sampling = (int)m_start.distance(m_end)/4;
		System.out.println(m_sampling);
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
	 * Returns the start point
	 * @return
	 */
	public Point GetStartPoint()
	{
		return m_start;
	}
	
	public void paintSidewalk(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
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
	 * Draws a circle
	 * @param g
	 * @param x
	 * @param y
	 * @param radius
	 */
	private void drawCircle(Graphics g, int x, int y, int radius) {
		g.fillOval(x-radius, y-radius, radius*2, radius*2);
	}
}
