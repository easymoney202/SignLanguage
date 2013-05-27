package SL;

/**
 * Defines a 2D position
 * 
 * @author Diego, Matt & Zac
 */
public class Point2D {
	public float x, y;
	
	// Constructor
	public Point2D()
	{
		x = 0;
		y = 0;
	}
	// Constructor 2
	public Point2D(float _x, float _y)
	{
		x = _x;
		y = _y;
	}
	
	// Adds two points together
	Point2D Add(Point2D p)
	{
		Point2D cp = new Point2D();
		
		cp.x = this.x + p.x;
		cp.y = this.y + p.y;
		
		return cp;
	}
}
