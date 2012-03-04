/**
 * @author John Linford
 */

import javax.swing.*;
import java.awt.*;

public class View extends JPanel
{
	public View(SceneGraph s)
	{
		// 0 = adding start, 1 = adding end, 2 = adding obstacle
		mode = 0;
		scene = s;
		aStar = new AStar(scene);

		setLocation(0, 200);
		setLayout(new BorderLayout());

		Icon nodeIcon = new Icon()
		{
			public int getIconWidth() { return ICON_WIDTH; }
			public int getIconHeight() { return ICON_HEIGHT; }
			public void paintIcon(Component comp, Graphics g, int x, int y)
			{
				Graphics2D g2 = (Graphics2D) g;
				//g2.setBackground(Color.BLACK);

				for (int c = 0; c < NUM_SQUARES; c++)
					for (int r = 0; r < NUM_SQUARES; r++)
					{
						Rectangle r2 = new Rectangle(c * SIZE, r * SIZE, SIZE, SIZE);
						
						if (scene.getNode(c, r).isStart)
							g2.setColor(new Color(0, 100, 0));
						else if (scene.getNode(c, r).isFinish)
							g2.setColor(new Color(100, 0, 0));
						else if (scene.getNode(c, r).isWall)
							g2.setColor(new Color(175, 125, 0));
						else if (scene.getNode(c, r).isPath && mode == 3)	// only show paths in example mode
							g2.setColor(new Color(0, 0, 100));
						else if (scene.getNode(c, r).wasVisited && mode == 3)
							g2.setColor(new Color(0, 0, 200));
						else 
							g2.setColor(Color.GRAY);
				
						
						g2.fill(r2);
						
						g2.setColor(Color.BLACK);
						g2.draw(r2);
					}
				
				// display help information at top left
				g2.setColor(Color.WHITE);
				if (mode == 0)
					g2.drawString("Left click to select start node", 0, 10);
				else if (mode == 1)
					g2.drawString("Left click to select end node", 0, 10);
				else if (mode == 2)
					g2.drawString("Left click to select obstacles. When finished right click to run display mode, shift+right click to play game", 0, 10);
				else if (mode == 3)
					g2.drawString("Algorithm finished. Light blue are visited cells, dark blue is the actual path taken", 0, 10);
				else if (mode == 4)
					g2.drawString("Use arrow keys to run away!", 0, 10);
				else if (mode == 5)
					g2.drawString("Game over!", 0, 10);
			}
		};
		
		add(new JLabel(nodeIcon));
		setVisible(true);
	}
	
	// update the computer
	public void Update()
	{
		aStar.Run(scene.start, scene.goal, mode);
		
		// if they hit us, game over
		if (scene.start == scene.goal)
			mode = 5;
		
		myTurn = true;

		repaint();

	}

	public SceneGraph scene;
	public int mode;
	
	// used for game mode, turn = true is player turn, turn = false is computer turn
	public boolean myTurn = true;
	
	private AStar aStar;
	
	private final static int NUM_SQUARES = 16;
	private final static int SIZE = 50;
	private final static int ICON_WIDTH = 50;
	private final static int ICON_HEIGHT = 50;

}