/**
 * @author John Linford
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class View extends JPanel implements Observer
{
	public View(SceneGraph s)
	{
		// 0 = adding start, 1 = adding end, 2 = adding obstacle
		mode = 0;
		scene = s;

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
						else if (scene.getNode(c, r).isPath)
							g2.setColor(new Color(0, 0, 100));
						else if (scene.getNode(c, r).wasVisited)
							g2.setColor(new Color(0, 0, 200));
						else 
							g2.setColor(Color.GRAY);
						
						g2.fill(r2);
						
						g2.setColor(Color.BLACK);
						g2.draw(r2);
					}     
			}
		};

		// A listener for click events
		MouseListener l = new MouseListener()
		{
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
			public void mouseClicked(MouseEvent e)
			{
				if (mode == 2 && e.getButton() == MouseEvent.BUTTON3)
				{
					mode = 3;
					AStar path = new AStar(scene);
					
					System.out.println("Running A*!");
					path.Run(start, goal);
					
					repaint();
				}
				
				
				int x = e.getX();
				int y = e.getY();

				// the coordinates of the mouse click
				int col = (int) Math.ceil(x / SIZE);
				int row = (int) Math.ceil(y / SIZE);
				
				Node tmpNode = scene.getNode(col, row);

//    		  System.out.println(scene.getData(xCoord, yCoord).getColor();

				if (mode == 0)
				{
					tmpNode.isStart = true;
					start = tmpNode;
					
					repaint();
					
					System.out.println("Start selected");
					mode = 1;
				}
				else if (mode == 1)
				{
					tmpNode.isFinish = true;
					goal = tmpNode;
					
					repaint();
					
					System.out.println("Finish selected");
					mode = 2;
				}
				else if (mode == 2)
				{
					tmpNode.isWall = true;
					
					repaint();
					
					System.out.println("Obstacle made");
				}
			}
		};

		
		add(new JLabel(nodeIcon));
		this.addMouseListener(l);
		setVisible(true);
	}

	public void update(Observable o, Object arg){}

	private SceneGraph scene;
	private Node start;
	private Node goal;
	
	public int mode;
	
	private final static int NUM_SQUARES = 16;
	private final static int SIZE = 50;
	private final static int ICON_WIDTH = 50;
	private final static int ICON_HEIGHT = 50;

}