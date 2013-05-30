package SL;

import javax.swing.JFrame;
import java.awt.*;
import java.util.ArrayList;

public class SignLanguage extends JFrame {
	private static final long serialVersionUID = 2635031231518739273L;
	public static Boolean m_running = true;
	public static GamePanel m_gamePanel;
	public static SignLanguage Instance = null;
	public static Integer WINDOW_WIDTH = 800;
	public static Integer WINDOW_HEIGHT = 600;
	public static Integer NUM_ROOMS = 10;

	MenuScene m_menuScene;
	TrafficScene m_trafficScene;
	HowToScene m_howToScene;
	GameOverScene m_gameOverScene;

	public enum GAME_STATE {
		Menu, Traffic, HowTo, GameOver
	};

	public static GAME_STATE m_gameState = GAME_STATE.Menu;

	public SignLanguage() {
		Instance = this;
		m_gamePanel = new GamePanel();
		add(m_gamePanel);
		setTitle("SignLanguage");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

		System.out.println(Instance);
		
		m_trafficScene = new TrafficScene();
		m_menuScene = new MenuScene();

		GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

		GameLoop();
	}

	/**
	 * Resets the game after a game over
	 */
	public void ResetGame() {

	}

	/**
	 * Paints the game. This gets called from the GamePanel paint method
	 */
	public void paint(Graphics g) {
		switch (m_gameState) {
		case Menu:
			if (m_menuScene != null)
				m_menuScene.paint(g);
			break;
		case Traffic:
			if (m_trafficScene != null)
				m_trafficScene.paint(g);
			break;
		case HowTo:
			if (m_howToScene != null)
				m_howToScene.paint(g);
			break;
		case GameOver:
			if (m_gameOverScene != null)
				m_gameOverScene.paint(g);
		default:
			// Shouldn't happen
			break;
		}
	}

	/**
	 * Main game loop
	 */
	public void GameLoop() {
		while (m_running) {
			switch (m_gameState) {
			case Menu:
				if (m_menuScene != null)
					m_menuScene.Update();
				break;
			case Traffic:
				if (m_trafficScene != null)
					m_trafficScene.Update();
				break;
			case HowTo:
				if (m_howToScene != null)
					m_howToScene.Update();
				break;
			case GameOver:
				if (m_gameOverScene != null)
					m_gameOverScene.Update();
				break;
			default:
				break;
			}
			
			m_gamePanel.repaint();
		}
	}

	/**
	 * Gets the current GameScene being displayed
	 * 
	 * @return
	 */
	public GameScene GetCurrentScene() {
		switch (m_gameState) {
		case Menu:
			if (m_menuScene != null)
				return m_menuScene;
			break;
		case Traffic:
			if (m_trafficScene != null)
				return m_trafficScene;
			break;
		case HowTo:
			if (m_howToScene != null)
				return m_howToScene;
			break;
		case GameOver:
			if (m_gameOverScene != null)
				return m_gameOverScene;
			break;
		default:
			return null;
		}

		return null;
	}

	/**
	 * Sets the game state of the game
	 * 
	 * @param state
	 */
	public void SetGameState(GAME_STATE state) {
		m_gameState = state;

		if (state == GAME_STATE.GameOver) {
			ResetGame();
		}
	}

	public static void main(String[] args) {
		new SignLanguage();
	}
}
