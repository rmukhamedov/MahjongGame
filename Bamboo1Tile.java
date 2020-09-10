import java.awt.*;
import java.net.*;
import javax.swing.*;

public class Bamboo1Tile extends PictureTile
{
	public Bamboo1Tile()
	{
		super("Sparrow");
	}

	public String toString()
	{
		return "Bamboo 1";
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int x, y;
		URL url = getClass().getResource("images/Sparrow.png");
		ImageIcon image = new ImageIcon(url);
		Image i = image.getImage();
		x = 14 + (66 - i.getWidth(this))/2;
		y = (85 - i.getHeight(this))/2;
		g.drawImage(i, x, y, this);
	}
}

