import java.awt.*;
import javax.swing.*;

public class CircleTile extends RankTile
{
	private Circle[] circles;

	public CircleTile(int rank){
		super(rank);
		setToolTipText(toString());
	}
	
	public String toString(){
		return "Circle " + rank;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int d = 30;
		Color blue = new Color(0, 0, 160);
		Color red = new Color(210, 50, 50);
		Color green = new Color(35, 160, 60);
		circles = new Circle[rank];
		
		switch (rank)
		{
			case 1:
				circles[0] = new Circle(33, 27, d, red); break;
			case 2:
				circles[0] = new Circle(33, 10, d, green);
				circles[1] = new Circle(33, 45, d, blue); break;
			case 3:
				circles[0] = new Circle(34, 29, d-4, red);
				circles[1] = new Circle(19, 5, d-4, blue);
				circles[2] = new Circle(49, 54, d-4, green); break;
			case 4:
				circles[0] = new Circle(21, 10, d-6, blue);
				circles[1] = new Circle(47, 10, d-6, green);
				circles[2] = new Circle(47, 51, d-6, blue);
				circles[3] = new Circle(21, 51, d-6, green); break;
			case 5:
				circles[0] = new Circle(21, 10, d-7, blue);
				circles[1] = new Circle(47, 10, d-7, green);
				circles[2] = new Circle(47, 51, d-7, blue);
				circles[3] = new Circle(21, 51, d-7, green); 
				circles[4] = new Circle(34, 30, d-7, red); break;
			case 6:
				circles[0] = new Circle(22, 6, d-7, green);
				circles[1] = new Circle(49, 6, d-7, green);
				circles[2] = new Circle(22, 31, d-7, red);
				circles[3] = new Circle(49, 31, d-7, red); 
				circles[4] = new Circle(22, 56, d-7, red); 
				circles[5] = new Circle(49, 56, d-7, red); break;
			case 7:
				circles[0] = new Circle(20, 4, d-11, green);
				circles[1] = new Circle(38, 13, d-11, green);
				circles[2] = new Circle(56, 22, d-11,green);
				circles[3] = new Circle(27, 41, d-11, red); 
				circles[4] = new Circle(46, 62, d-11, red); 
				circles[5] = new Circle(27, 62, d-11, red); 
				circles[6] = new Circle(46, 41, d-11, red); break;
			case 8:
				circles[0] = new Circle(24, 3, d-11, blue);
				circles[1] = new Circle(51, 3, d-11, blue);
				circles[2] = new Circle(24, 22, d-11, blue);
				circles[3] = new Circle(51, 22, d-11, blue);
				circles[4] = new Circle(24, 42, d-11, blue);
				circles[5] = new Circle(51, 42, d-11, blue); 
				circles[6] = new Circle(24, 62, d-11, blue); 
				circles[7] = new Circle(51, 62, d-11, blue); break;
			case 9:
				circles[0] = new Circle(17, 7, d-12, green);
				circles[1] = new Circle(38, 7, d-12, green);
				circles[2] = new Circle(59, 7, d-12, green);
				circles[3] = new Circle(17, 33, d-12, red);
				circles[4] = new Circle(38, 33, d-12, red);
				circles[5] = new Circle(59, 33, d-12, red); 
				circles[6] = new Circle(17, 59, d-12, blue); 
				circles[7] = new Circle(38, 59, d-12, blue);
				circles[8] = new Circle(59, 59, d-12, blue); break;
		}
		
		if (rank == 1){
			g.setColor(blue);
			g.fillOval(23, 17, 50, 50);
			g.setColor(green);
			g.fillOval(31, 25, 34, 34);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.white);
			g.fillOval(32, 26, 32, 32);
			for (int i = 0; i < 12; i++){
				g2d.fillOval(46, 19, 4, 4);
				g2d.rotate(0.524, 48, 42);
			}
		}
		
		for (Circle c : circles)
			if (c != null)
				c.draw(g);
	}
	
		public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Circle Tiles");

		frame.add(new CircleTile(1));
		frame.add(new CircleTile(2));
		frame.add(new CircleTile(3));
		frame.add(new CircleTile(4));
		frame.add(new CircleTile(5));
		frame.add(new CircleTile(6));
		frame.add(new CircleTile(7));
		frame.add(new CircleTile(8));
		frame.add(new CircleTile(9));

		frame.pack();
		frame.setVisible(true);
	}
}