package SL;

  public class Car {

	private int direction;
	private int xcoord;
	private int ycoord;
	private boolean stopped;
	private Road road;

	public Car(Road r, int startx, int starty, int dir) {
		road = r;
		xcoord = startx;
		ycoord = starty;
		direction = dir; // 0 - North, 1 - East, 2 - South, 3 - West
		stopped = false;
	}

	public Road getRoad() {
		return road;
	}

	public void setRoad(Road r) {
		road = r;
	}

	public boolean getStopped() {
		return stopped;
	}

	public void setStopped(boolean s) {
		stopped = s;
	}

	public int getDirection() {
		return direction;
	}

	public int getX() {
		return xcoord;
	}

	public int getY() {
		return ycoord;
	}

	public void setX(int x) {
		xcoord = x;
	}

	public void setY(int y) {
		ycoord = y;
	}

	public void setDirection(int dir) {
		direction = dir;
	}

	public void dispose() {
		getRoad().setCar(null);
		setRoad(null);
	}

	public boolean move(Road r) {
		if (r.isOccupied()) {
			return false;
		} else {
			if (getRoad() != null) {
				getRoad().setCar(null);
			}
			setRoad(r);
			r.setCar(this);
			return true;
		}
	}
}
