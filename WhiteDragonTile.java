import java.awt.*;
import javax.swing.*;

public class WhiteDragonTile extends Tile
{
	public WhiteDragonTile(){
		setToolTipText(toString());
	}
	
	public String toString()
	{
		return "White Dragon";
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Color blue = new Color(0, 0, 160);
		
		g.setColor(blue);
		for (int x = 18; x < 60; x+=20){
			g.fillRect(x, 6, 10, 5);
			g.fillRect(x, 75, 10, 5);
		}
		
		for (int y = 10; y < 75; y+=25){
			g.fillRect(70, y, 6, 16);
		}
		
		for (int y = 21; y < 70; y+=25){
			g.fillRect(18, y, 6, 16);
		}
		
		g.setColor(Color.black);
		g.drawRect(18, 5, 58, 75);
		g.drawRect(24, 10, 46, 65);
	}
}

