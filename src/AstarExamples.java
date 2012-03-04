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
		sceneGraph = new SceneGraph();

		gameView = new View(sceneGraph);
		sceneGraph.addObserver(gameView);

		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("A* Example");
		frame.setLayout(new GridLayout(2, 1));
		frame.setContentPane(gameView);
		frame.show();
	}

	private static final int WIDTH = 805, HEIGHT = 830;

	public static View gameView;
	public static SceneGraph sceneGraph;
}