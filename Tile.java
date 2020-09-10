import java.awt.*;
import javax.swing.*;

public abstract class Tile extends JPanel
{	
	public int x;
	public int y;
	public int z;
	public int zO;
	public boolean h = false;
	
	public Tile(){
		Dimension d = new Dimension(81, 100);
		setPreferredSize(d);
		setSize(d);
		setMaximumSize(d);
	}
	
	public void setXYZ(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean matches(Tile other){
		if(this == other)
			return false;
		
		return getClass() == other.getClass();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int xR[] = {14, 80};
		int yR[] = {0, 85};
		int i = 7;
		
		Color cBack1 = new Color(80, 80, 210);
		Color cBack2 = new Color(20, 30, 100);
		Color cFace1 = new Color(255, 255, 235);
		Color cFace2 = new Color(210, 210, 200);
		
		if(h){
			cFace1 = new Color(150, 255, 150);
			cFace2 = new Color(170, 200, 160);
		}

		GradientPaint gpFace = new GradientPaint(xR[0], yR[1], cFace1, xR[1], yR[0], cFace2);
		GradientPaint gpBack = new GradientPaint(xR[0], yR[1], cBack1, xR[1], yR[0], cBack2);
		
		int xP1[] = {xR[0]-i, xR[0], xR[0], xR[0]-i};
		int yP1[] = {yR[0]+i, yR[0], yR[1], yR[1]+i};
		
		int xP2[] = {xR[0], xR[1], xR[1]-i, xR[0]-i};
		int yP2[] = {yR[1], yR[1], yR[1]+i, yR[1]+i};
		
		
		int xP3[] = {xP1[0]-i, xP1[1]-i, xP1[2]-i, xP1[3]-i};
		int yP3[] = {yP1[0]+i, yP1[1]+i, yP1[2]+i, yP1[3]+i};
		
		int xP4[] = {xP2[0]-i, xP2[1]-i, xP2[2]-i, xP2[3]-i};
		int yP4[] = {yP2[0]+i, yP2[1]+i, yP2[2]+i, yP2[3]+i};
		
		//g.setColor(tileFace);
		g2d.setPaint(gpFace);
		g2d.fillRect(xR[0], yR[0], xR[1]-xR[0], yR[1]-yR[0]);
		g2d.fillPolygon(xP1, yP1, 4);
		g2d.fillPolygon(xP2, yP2, 4);
		
		//g.setColor(tileBack);
		g2d.setPaint(gpBack);
		g2d.fillPolygon(xP3, yP3, 4);
		g2d.fillPolygon(xP4, yP4, 4);
		
		Color lg = new Color(60, 60, 60);
		g.setColor(lg);
		g.drawRect(xR[0], yR[0], xR[1]-xR[0], yR[1]-yR[0]);
		g.drawPolygon(xP1, yP1, 4);
		g.drawPolygon(xP2, yP2, 4);
		g.drawPolygon(xP3, yP3, 4);
		g.drawPolygon(xP4, yP4, 4);
		/*
		Graphics2D	g2 = (Graphics2D)g;
		
		Composite cOld = g2.getComposite();
		Composite cNew = ((AlphaComposite)cOld).derive(0.25F);
		
		g2.setComposite(cNew);
		
		int[] x = {21, 89, 89, 14};
		int[] y = {0, 0, 14, 14};
		Polygon shadow = new Polygon(x, y, 4);
		g2.setColor(Color.black);
		
		g.fillPolygon(shadow);
		g2.setComposite(cOld);*/
	}
	
	/*	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tile");

		frame.add(new Tile());

		frame.pack();
		frame.setVisible(true);
	}
	*/
}