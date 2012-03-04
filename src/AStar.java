/**
 * @author John Linford
 */

import java.util.ArrayList;


public class AStar 
{
	public AStar(SceneGraph s)
	{
		openList = new ArrayList<Node>();
		closedList = new ArrayList<Node>();
		scene = s;
	}
	
	// run a* algorithm
	public void Run(Node s, Node g)
	{
		start = s;
		goal = g;
		
		// calc cost for start node to goal node, and add to open list
		start.setCost(calcCost(start, goal));
		start.setCostFromStart(0);
		openList.add(start);
		
		
		while (!openList.isEmpty())
		{		
			// make the new current node the one with lowest cost
			curNode = LowestCost();
			
			// mark that we have been to this node to show path later
			curNode.wasVisited = true;
			
			// check to see if we are at the goal, and we can reconstruct the path
			if (curNode == goal)
			{
				reconstrucPath(goal);
				return;
			}
			
			else
			{
				// otherwise continue search
				// add current node to the list of searched nodes
				closedList.add(curNode);
				// and remove it from the openlist
				openList.remove(curNode);
				
				// add adj nodes to open list if they have not been checked and assign costs
				CheckAdj();
			}
		}
	}

	// find the lowest cost node from the openList, and set where we came from
	public Node LowestCost()
	{
		Node tmpNode = openList.get(0);
		int lowestCost = tmpNode.getCost();
		
		for (int i = 1; i < openList.size(); i++)
		{
			if (lowestCost > openList.get(i).getCost())
			{
				lowestCost = openList.get(i).getCost();
				tmpNode = openList.get(i);
			}
		}
		
		return tmpNode;
	}
	
	// check adjacent neighbors and their costs
	public void CheckAdj()
	{
		int tmpCol;
		int tmpRow;
		Node tmpNode = null;
		
		// north node
		tmpCol = curNode.getCol();
		tmpRow = curNode.getRow() - 1;
		
		try
		{
			tmpNode = scene.getNode(tmpCol, tmpRow);
		} catch(ArrayIndexOutOfBoundsException ex){}

		if (tmpNode != null && !tmpNode.isWall && 
				!openList.contains(tmpNode) && !closedList.contains(tmpNode))
		{
			// set cost from this node to goal
			tmpNode.setCost(calcCost(tmpNode, goal));
			// new costFromStart is simply curCost + 1
			tmpNode.setCostFromStart(curNode.getCost() + 1);
			// set the parent node
			tmpNode.setCameFrom(curNode);
			openList.add(tmpNode);
		}

		// east
		tmpCol = curNode.getCol() + 1;
		tmpRow = curNode.getRow();
		
		try
		{
			tmpNode = scene.getNode(tmpCol, tmpRow);
		} catch(ArrayIndexOutOfBoundsException ex){}

		if (tmpNode != null && !tmpNode.isWall && 
				!openList.contains(tmpNode) && !closedList.contains(tmpNode))
		{
			tmpNode.setCost(calcCost(tmpNode, goal));
			tmpNode.setCostFromStart(curNode.getCost() + 1);
			tmpNode.setCameFrom(curNode);
			openList.add(tmpNode);
		}

		// south
		tmpCol = curNode.getCol();
		tmpRow = curNode.getRow() + 1;
		
		try
		{
			tmpNode = scene.getNode(tmpCol, tmpRow);
		} catch(ArrayIndexOutOfBoundsException ex){}

		if (tmpNode != null && !tmpNode.isWall &&
				!openList.contains(tmpNode) && !closedList.contains(tmpNode))
		{
			tmpNode.setCost(calcCost(tmpNode, goal));
			tmpNode.setCostFromStart(curNode.getCost() + 1);
			tmpNode.setCameFrom(curNode);
			openList.add(tmpNode);
		}

		// west
		tmpCol = curNode.getCol() - 1;
		tmpRow = curNode.getRow();
		
		try
		{
			tmpNode = scene.getNode(tmpCol, tmpRow);
		} catch(ArrayIndexOutOfBoundsException ex){}

		if (tmpNode != null && !tmpNode.isWall && 
				!openList.contains(tmpNode) && !closedList.contains(tmpNode))
		{
			tmpNode.setCost(calcCost(tmpNode, goal));
			tmpNode.setCostFromStart(curNode.getCost() + 1);
			tmpNode.setCameFrom(curNode);
			openList.add(tmpNode);
		}
	}
	
	// calculate the cost from node a to node b
	public int calcCost(Node a, Node b)
	{
		int cost;
		
		cost = Math.abs(a.getCol() - b.getCol()) + Math.abs(a.getRow() - b.getRow());
		
		return cost;
	}
	
	// reconstruct the path that was taken and mark the appropriate nodes
	public void reconstrucPath(Node node)
	{
		if (node.getCameFrom() != null && node.getCameFrom() != start)
		{
			node.getCameFrom().isPath = true;
			System.out.println(node.getCol() + " " + node.getRow() + "added to path");
			
			reconstrucPath(node.getCameFrom());
		}
		else
			return;
	}
	
	private ArrayList<Node> openList;
	private ArrayList<Node> closedList;
	private Node curNode;
	private Node start;
	private Node goal;
	private SceneGraph scene;
}
