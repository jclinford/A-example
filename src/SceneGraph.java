/**
 * @author John Linford
 */

/*
 *  this is a 16x16 2d Array of Integers.
 *  0 for empty space, 1 for wall, 2 for player, 3 for enemy
 */
public class SceneGraph
{
	public SceneGraph() 
	{
		scene = new Node[NUM_SQUARES][NUM_SQUARES];

		for (int c = 0; c < NUM_SQUARES; ++c)
			for(int r = 0; r< NUM_SQUARES; ++r)
			{
				scene[c][r] = new Node(c, r);
			}
	}

	public Node[][] getScene() 
	{
		return scene;
	}

	public Node getNode(int c, int r)
	{
		return scene[c][r];
	}


	// start and goal.. in game mode this is enemy and player respectively
	public Node start;
	public Node goal;
	
	private Node[][] scene;

	private static final int NUM_SQUARES = 16;
}