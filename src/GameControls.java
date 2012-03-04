import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameControls implements KeyListener, MouseListener
{
	public GameControls(View view, SceneGraph s)
	{
		parentView = view;
		scene = s;
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (!parentView.myTurn)
			return;
		
		System.out.println("Key down: " + parentView.mode + " " + parentView.myTurn);
		// only allow player movement if its their turn and if they're in game mode
		if (parentView.mode == 4)
		{
			Node newNode = null;
			int newCol = 0;
			int newRow = 0;

			// left arrow, move goal(player) left
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				newCol = scene.goal.getCol() - 1;
				newRow = scene.goal.getRow();
			}
			// right arrow, move goal(player) right
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				newCol = scene.goal.getCol() + 1;
				newRow = scene.goal.getRow();
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				newCol = scene.goal.getCol();
				newRow = scene.goal.getRow() - 1;
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				newCol = scene.goal.getCol();
				newRow = scene.goal.getRow() + 1;
			}
			else
				return;


			// check if we can move
			try
			{
				newNode = scene.getNode(newCol, newRow);

				if (newNode.isWall)
					return;

				scene.goal.isFinish = false;
				newNode.isFinish = true;
				scene.goal = newNode;
				parentView.myTurn = false;
				
				parentView.repaint();
				
				// run computer
				parentView.Update();

			} catch(ArrayIndexOutOfBoundsException ex){}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// if we are in mode 2 and right click then user wants to see
		// a* algorithm example
		if (parentView.mode == 2 && e.getButton() == MouseEvent.BUTTON3 && 
				!e.isShiftDown())
		{
			// display mode = 3
			parentView.mode = 3;
			AStar path = new AStar(scene);
			
			System.out.println("Running A*!");
			path.Run(scene.start, scene.goal, parentView.mode);
		}
		// if we are in mode 2 and right click and shift then user wants to
		// play a* game
		else if (parentView.mode == 2 && e.getButton() == MouseEvent.BUTTON3 &&
				e.isShiftDown())
		{
			// game mode = 4
			parentView.mode = 4;
			System.out.println("Playing game");
		}
		
		
		int x = e.getX();
		int y = e.getY();

		// the coordinates of the mouse click
		//int col = (int) Math.ceil(x / SIZE);
		//int row = (int) Math.ceil(y / SIZE);
		int col = (int) ( (x) / SIZE);
		int row = (int) ( (y - SIZE / 2) / SIZE);
		
		Node tmpNode = scene.getNode(col, row);


		if (parentView.mode == 0)
		{
			tmpNode.isStart = true;
			scene.start = tmpNode;
			
			System.out.println("Start selected");
			parentView.mode = 1;
		}
		else if (parentView.mode == 1)
		{
			tmpNode.isFinish = true;
			scene.goal = tmpNode;
			
			System.out.println("Finish selected");
			parentView.mode = 2;
		}
		else if (parentView.mode == 2)
		{
			tmpNode.isWall = true;
			
			System.out.println("Obstacle made");
		}
		
		parentView.repaint();
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

	AStar aStar;
	View parentView;
	SceneGraph scene;
	
	private final static int SIZE = 50;
}
