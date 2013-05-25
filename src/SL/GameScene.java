package SL;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Sign Language
 * GameScene class
 * ABSTRACT: This class is used for having separate
 * scenes in the game. All other scenes
 * must inherit from this
 * 
 * @author Diego, Matt & Zac
 */
public abstract class GameScene {
	/**
	 * Constructor
	 * @param active If the game scene is visible now
	 */
	public GameScene()
	{

	}
	/**
	 * Dummy paint scene
	 * @param g
	 */
	public void paint(Graphics g)
	{
		
	}
	/**
	 * Update method
	 */
	public void Update()
	{
		
	}
	
	/**
	 * Obtains input to the scene
	 */
	public void KeyPressed(KeyEvent e)
	{
		
	}
	/**
	 * Obtains input to the scene
	 */
	public void KeyReleased(KeyEvent e)
	{
		
	}
}
