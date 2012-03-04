/**
 * @author John Linford
 */

import java.util.Observable;
import java.awt.Color;

/*
 *  this is a 16x16 2d Array of Integers.
 *  0 for empty space, 1 for wall, 2 for player, 3 for enemy
 */
public class SceneGraph extends Observable
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

	/**
      Change the data in the model at a particular location
      @param location the index of the field to change
      @param value the new value
	 */
	public void changePoint(int cCol, int cRow, int nCol, int nRow)
	{
		super.setChanged();
		super.notifyObservers("change");
	}

	public Node[][] getScene() 
	{
		return scene;
	}

	public Node getNode(int c, int r)
	{
		return scene[c][r];
	}


	public int[] selected = new int[2];	//the selected piece
	private Node[][] scene;

	private static final int NUM_SQUARES = 16;
}