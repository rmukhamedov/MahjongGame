import java.awt.*;
import java.net.*;
import javax.swing.*;

public class FlowerTile extends PictureTile
{
	public FlowerTile(String name){
		super(name);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String imagePath = "images/";
		int x, y;
		
		switch (name)
		{
			case "Chrysanthemum": imagePath += "Chrysanthemum.png"; break;
			case "Orchid": imagePath += "Orchid.png"; break;
			case "Plum": imagePath += "Plum.png"; break;
			case "Bamboo": imagePath += "Bamboo.png"; break;
		}
		
		URL url = getClass().getResource(imagePath);
		ImageIcon image = new ImageIcon(url);
		Image i = image.getImage();
		x = 14 + (66 - i.getWidth(this))/2;
		y = (85 - i.getHeight(this))/2;
		g.drawImage(i, x, y, this);
		
	}
	
	/*	public static void main(String[] args)
	{
		JFrame	frame = new JFrame();

		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Flower Tiles");

		frame.add(new FlowerTile("Chrysanthemum"));
		frame.add(new FlowerTile("Orchid"));
		frame.add(new FlowerTile("Plum"));
		frame.add(new FlowerTile("Bamboo"));

		frame.pack();
		frame.setVisible(true);
	}*/
}