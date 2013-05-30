package SL;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;

import javax.imageio.ImageIO;

public class Button {
	private Image m_img;
	private Point m_pos;
	private Point m_size;
	private String m_name;

	// Constructor
	public Button(String name, String filename, Point pos)
	{
		try
		{
			File imageFile = new File(filename);
			m_img = ImageIO.read(imageFile);
		}
		catch (Exception e)
		{
			System.out.println("Couldn't load button file");
		}
		
		m_name = name;
		m_pos = pos;
		m_size = new Point(m_img.getWidth(null), m_img.getHeight(null));
	}
	
	// Returns if the mouse is in the button
	public boolean IsClicked(Point mousePos)
	{
		if (mousePos.x >= m_pos.x && mousePos.x <= m_pos.x + m_size.x)
		{
			if (mousePos.y >= m_pos.y && mousePos.y <= m_pos.y + m_size.y)
			{
				return true;
			}
		}
		return false;
	}
	
	// Paint the button
	public void paint(Graphics g)
	{
		g.drawImage(m_img, m_pos.x, m_pos.y, null);
	}
}
