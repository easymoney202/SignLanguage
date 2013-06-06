package SL;

public class Car {

	private int direction;
	private int xcoord;
	private int ycoord;
	private boolean stopped;
	
	public Car(int startx, int starty, int dir) {
		xcoord = startx;
		ycoord = starty;
		direction = dir; //0 - North, 1 - East, 2 - South, 3 - West
		stopped = false;
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
	
}
