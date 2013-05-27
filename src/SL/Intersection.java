package SL;

import java.util.ArrayList;

/**
 * Handles intersecting roads
 * @author Diego
 */
public class Intersection {
	
	public enum Direction
	{
		LEFT,
		RIGHT,
		STRAIGHT,
	}
	
	// Contains the roads in the intersection
	private ArrayList<Road> m_roads;
	// Contains the direction in which roads go
	private ArrayList<Direction> m_direction;
	
	/**
	 * Constructor
	 */
	public Intersection()
	{
		m_roads = new ArrayList<Road>();
	}
	
	/**
	 * Intersects two roads
	 * This will calculate the direction based on the angle the roads have
	 * it will also snap the end point of road1 to the start point of road2
	 * @param road1
	 * @param road2
	 */
	public void IntersectRoads(Road road1, Road road2)
	{
		// Force intersection of roads
		road2.SetStartPoint(road1.GetEndPoint());
		// Set the intersection object of road1
		road1.SetIntersection(this);
		
		// Add the roads to the intersection
		m_roads.add(road1);
		m_roads.add(road2);
	}
}