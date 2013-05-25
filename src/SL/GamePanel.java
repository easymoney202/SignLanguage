package SL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel for the game
 */
public class GamePanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1L;
    public static GamePanel Instance;
    private static Boolean m_initialized = false;

    /**
     * Constructor
     */
    public GamePanel()
    {
    	addKeyListener(new InputAdapter());
    	setFocusable(true);
    	setBackground(Color.BLACK);
    	setDoubleBuffered(true);
    	if (m_initialized == false)
    	{
    		Instance = this;
    		m_initialized = true;
    	}
    }
    
    /**
     * 
     */
    public void actionPerformed(ActionEvent e){
    	repaint();
    }
    
    /**
     * Paint the game panel
     */
    public void paint(Graphics g)
    {
    	super.paint(g);
    	g.clearRect(0, 0, SignLanguage.WINDOW_WIDTH, SignLanguage.WINDOW_HEIGHT);
    	
    	Graphics2D g2 = (Graphics2D) g;
    	
    	RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
    									       RenderingHints.VALUE_ANTIALIAS_ON);
    	
    	rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    	
    	g2.setRenderingHints(rh);
    	
    	if (SignLanguage.Instance != null)
    		SignLanguage.Instance.paint(g);
    	
    	g.dispose();
    }
    
    /**
     * Passes input to the corresponding scene
     */
    private class InputAdapter extends KeyAdapter
    {
    	public void keyPressed(KeyEvent e)
    	{
    		GameScene currentScene = SignLanguage.Instance.GetCurrentScene();
    		
    		if (currentScene != null)
    			currentScene.KeyPressed(e);
    	}
    	
    	public void keyReleased(KeyEvent e)
    	{
    		GameScene currentScene = SignLanguage.Instance.GetCurrentScene();
    		
    		if (currentScene != null)
    			currentScene.KeyReleased(e);
    	}
    }
}

