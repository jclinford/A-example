/**
 * A* implementation
 * Left click to add start, finish and obstacles
 * Right click when done to run the algorithm
 * 
 * Looked at paths will be in light blue, the
 * actual path will be in dark blue
 * @author John Linford
 */


import javax.swing.*;
import java.awt.*;

public class AstarExamples 
{
	public static void main(String args[]) 
	{
		// contains our scene
		sceneGraph = new SceneGraph();
		// displays the scene
		gameView = new View(sceneGraph);
		// mouse and key listener for controls
		gameControls = new GameControls(gameView, sceneGraph);

		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("A* Example");
		frame.setLayout(new GridLayout(2, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(gameView);
		frame.addKeyListener(gameControls);
		frame.addMouseListener(gameControls);
		frame.show();
	}

	private static final int WIDTH = 805, HEIGHT = 830;

	private static GameControls gameControls;
	private static View gameView;
	private static SceneGraph sceneGraph;
}