import java.awt.*;
import javax.swing.*;

public class Bamboo
{
	private int x;
	private int y;
	private Color c;
	
	public Bamboo(int x, int y, Color c){
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public void draw(Graphics g){
		g.setColor(c);
		g.fillOval(x, y, 10, 5);
		g.fillOval(x, y+10, 10, 5);
		g.fillOval(x, y+20, 10, 5);
		g.fillOval(x+2, y, 5, 25);
		Color hl = new Color(230, 230, 230);
		g.setColor(hl);
		g.drawLine(x+5, y+4, x+5, y+10);
		g.drawLine(x+5, y+14, x+5, y+20);
	}
	
	/*public void drawSpecial(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(c);
		
		g.fillOval(x, y, 10, 5);
		g.fillOval(x, y+10, 10, 5);
		g.fillOval(x, y+20, 10, 5);
		g.fillOval(x+2, y, 5, 25);
		Color hl = new Color(230, 230, 230);
		g.setColor(hl);
		g.drawLine(x+5, y+4, x+5, y+10);
		g.drawLine(x+5, y+14, x+5, y+20);	
	}*/
	
}