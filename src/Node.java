/**
 * @author John Linford
 */

import java.awt.Color;

public class Node 
{
	public Node(int c, int r) 
	{
		col = c;
		row = r;
	}
	
	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	
	public void setCost(int c)
	{
		cost = c;
	}
	public int getCost()
	{
		return cost;
	}
	
	public void setCostFromStart(int c)
	{
		costFromStart = c;
	}
	public int getCostFromStart()
	{
		return costFromStart;
	}
	
	public void setHeuristic(int h)
	{
		heuristic = h;
	}
	public int getHeuristic()
	{
		return heuristic;
	}
	
	public void setCameFrom(Node n)
	{
		cameFrom = n;
	}
	public Node getCameFrom()
	{
		return cameFrom;
	}

	private int row;
	private int col;
	
	// A* cost, heuristic and costFromStart and Node that you came from
	private int cost;
	private int costFromStart;
	private int heuristic;
	private Node cameFrom;

	public boolean wasVisited = false;
	public boolean isPath = false;
	public boolean isWall = false;
	public boolean isStart = false;
	public boolean isFinish = false;
}