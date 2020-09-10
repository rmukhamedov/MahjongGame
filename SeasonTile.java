import java.awt.*;
import java.net.*;
import javax.swing.*;

public class SeasonTile extends PictureTile
{
	public SeasonTile(String name){
		super(name);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String imagePath = "images/";
		int x, y;
		
		switch (name)
		{
			case "Spring": imagePath += "Spring.png"; break;
			case "Winter": imagePath += "Winter.png"; break;
			case "Summer": imagePath += "Summer.png"; break;
			case "Fall": imagePath += "Fall.png"; break;
		}
		URL url = getClass().getResource(imagePath);
		ImageIcon image = new ImageIcon(url);
		Image i = image.getImage();
		x = 14 + (66 - i.getWidth(this))/2;
		y = (85 - i.getHeight(this))/2;
		g.drawImage(i, x, y, this);
		
	}
}