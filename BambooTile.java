import java.awt.*;
import javax.swing.*;

public class BambooTile extends RankTile
{
	private Bamboo[] bamboo;
	
	public BambooTile(int rank){
		super(rank);
		setToolTipText(toString());
	}
	
	public String toString(){
		return "Bamboo " + rank;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int d = 30;
		Color blue = new Color(0, 0, 160);
		Color red = new Color(210, 50, 50);
		Color green = new Color(35, 160, 60);
		bamboo = new Bamboo[rank];
		
		switch (rank)
		{
			case 2:
				bamboo[0] = new Bamboo(42, 15, blue);
				bamboo[1] = new Bamboo(42, 45, green); break;
			case 3:
				bamboo[0] = new Bamboo(42, 15, blue);
				bamboo[1] = new Bamboo(33, 45, green);
				bamboo[2] = new Bamboo(51, 45, green); break;
			case 4:
				bamboo[0] = new Bamboo(29, 15, blue);
				bamboo[1] = new Bamboo(55, 15, green);
				bamboo[2] = new Bamboo(55, 45, blue);
				bamboo[3] = new Bamboo(29, 45, green); break;
			case 5:
				bamboo[0] = new Bamboo(29, 15, green);
				bamboo[1] = new Bamboo(55, 15, blue);
				bamboo[2] = new Bamboo(55, 45, green);
				bamboo[3] = new Bamboo(29, 45, blue); 
				bamboo[4] = new Bamboo(42, 30, red); break;
			case 6:
				bamboo[0] = new Bamboo(25, 15, green);
				bamboo[1] = new Bamboo(42, 15, green);
				bamboo[2] = new Bamboo(59, 15, green);
				bamboo[3] = new Bamboo(25, 45, blue); 
				bamboo[4] = new Bamboo(42, 45, blue); 
				bamboo[5] = new Bamboo(59, 45, blue); break;
			case 7:
				bamboo[0] = new Bamboo(42, 3, red);
				bamboo[1] = new Bamboo(25, 30, green);
				bamboo[2] = new Bamboo(42, 30, blue);
				bamboo[3] = new Bamboo(59, 30, green); 
				bamboo[4] = new Bamboo(25, 57, green); 
				bamboo[5] = new Bamboo(42, 57, blue); 
				bamboo[6] = new Bamboo(59, 57, green); break;
			case 8:
				bamboo[0] = new Bamboo(22, 15, green);
				bamboo[1] = new Bamboo(62, 15, green);
				bamboo[2] = new Bamboo(22, 45, blue);
				bamboo[3] = new Bamboo(62, 45, blue);
				bamboo[4] = new Bamboo(22, 15, green);
				bamboo[5] = new Bamboo(62, 15, green); 
				bamboo[6] = new Bamboo(22, 45, blue); 
				bamboo[7] = new Bamboo(62, 49, blue); break;
			case 9:
				bamboo[0] = new Bamboo(59, 3, green);
				bamboo[1] = new Bamboo(59, 30, green);
				bamboo[2] = new Bamboo(59, 57, green);
				bamboo[3] = new Bamboo(25, 3, red);
				bamboo[4] = new Bamboo(25, 30, red);
				bamboo[5] = new Bamboo(25, 57, red); 
				bamboo[6] = new Bamboo(42, 3, blue); 
				bamboo[7] = new Bamboo(42, 30, blue);
				bamboo[8] = new Bamboo(42, 57, blue); break;
		}
		
		if (rank == 8){
			for(int i = 0; i < 4; i++)
				bamboo[i].draw(g);
			
			Graphics2D g2d = (Graphics2D) g;
			g2d.rotate(1, 27, 40);
			bamboo[4].draw(g);
			g2d.rotate(-1, 25, 40);
			g2d.rotate(-1, 70, 40);
			bamboo[5].draw(g);
			g2d.rotate(1, 70, 40);
			g2d.rotate(-1, 30, 49);
			bamboo[6].draw(g);
			g2d.rotate(1, 30, 49);
			g2d.rotate(1, 67, 49);
			bamboo[7].draw(g);
		} else {
			for (Bamboo b : bamboo)
				if (b != null)
					b.draw(g);
		}
	}
	
	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Bamboo Tiles");

		frame.add(new BambooTile(2));
		frame.add(new BambooTile(3));
		frame.add(new BambooTile(4));
		frame.add(new BambooTile(5));
		frame.add(new BambooTile(6));
		frame.add(new BambooTile(7));
		frame.add(new BambooTile(8));
		frame.add(new BambooTile(9));

		frame.pack();
		frame.setVisible(true);
	}
}