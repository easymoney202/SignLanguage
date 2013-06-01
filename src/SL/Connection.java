package SL;

/**
 * This class represents the connections that a road has to other roads
 * @author Diego
 */
public class Connection {
	// Type of connection
	public enum ConType
	{
		// IN Used for one-ways to make sure cars go in and not out through there
		IN,
		OUT,
		// No direction define, cars go straight
		IN_OUT,
		// No connection to that tile
		NONE,
	}

	// Direction of connection (Where in the tile)
	public enum ConDir
	{
		UP,
		DOWN,
		LEFT,
		RIGHT,
		NONE,
	}

	public ConDir Direction;
	public ConType Type;

	/**
	 * Constructor
	 * @param _dir
	 */
	public Connection(ConDir _dir)
	{
		Direction = _dir;
		Type = ConType.IN_OUT;
	}

	/**
	 * Gets the opposite direction
	 * @param dir
	 * @return
	 */
	public ConDir GetNegDir(ConDir dir)
	{
		switch(dir)
		{
		case UP:
			return ConDir.DOWN;
		case DOWN:
			return ConDir.UP;
		case LEFT:
			return ConDir.RIGHT;
		case RIGHT:
			return ConDir.LEFT;
		default:
			return ConDir.NONE;
		}
	}
	
	/**
	 * Returns a string with info of the connection
	 * @return
	 */
	public String ToString()
	{
		switch(Type)
		{
		case IN_OUT:
			return "IN_OUT";
		case IN:
			return "IN";
		case OUT:
			return "OUT";
		case NONE:
			return "NONE";
		default:
			return "";
		}
	}
}

