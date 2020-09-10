import java.awt.*;
import javax.swing.*;

public class Circle
{
	private int x;
	private int y;
	private int d;
	Color c;
	
	public Circle(int x, int y, int d, Color c){
		this.x = x;
		this.y = y;
		this.d = d;
		this.c = c;
	}
	
	public void draw(Graphics g){
		g.setColor(c);
		g.fillOval(x, y, d, d);

		g.setColor(Color.white);
		int r = d/2;
		
		g.drawLine(x+r, y+2, x+r, y+r-2);
		g.drawLine(x+r, y+2+r, x+r, y+d-2);
		g.drawLine(x+2, y+r, x+r-2, y+r);
		g.drawLine(x+r+2, y+r, x+d-2, y+r);
	}
}