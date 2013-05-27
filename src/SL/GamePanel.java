package SL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel for the game
 */
public class GamePanel extends JPanel implements ActionListener, MouseListener
{
    private static final long serialVersionUID = 1L;
    public static GamePanel Instance;
    private static boolean m_initialized = false;
    
    private boolean m_clicking = false;

    /**
     * Constructor
     */
    public GamePanel()
    {
    	addKeyListener(new InputAdapter());
    	addMouseListener(this);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (m_clicking == false)
		{
			System.out.println("Clicked!");
			GameScene currentScene = SignLanguage.Instance.GetCurrentScene();
		
			if (currentScene != null)
				currentScene.MouseClick(e);
			
			m_clicking = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		m_clicking = false;
	}
}

