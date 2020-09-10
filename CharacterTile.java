import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CharacterTile extends Tile
{
	protected char symbol;
	private static final Map<Character, String> CODE_MAP = new HashMap<Character, String>(){{
			put('1', "\u4e00");
			put('2', "\u4e8c");
			put('3', "\u4e09");
			put('4', "\u56db");
			put('5', "\u4e94");
			put('6', "\u516d");
			put('7', "\u4e03");
			put('8', "\u516b");
			put('9', "\u4e5d");
			put('N', "\u5317");
			put('E', "\u6771");
			put('W', "\u897f");
			put('S', "\u5357");
			put('C', "\u4e2d");
			put('F', "\u767c");
	}};
	
	public CharacterTile(char symbol){
		this.symbol = symbol;
		setToolTipText(toString());
	}
	
	public boolean matches(Tile other){
		if (!super.matches(other))
			return false;
		
		return this.symbol == ((CharacterTile)other).symbol;
	}
	
	public String toString(){
		switch (symbol)
		{
			case 'N': return "North Wind";
			case 'E': return "East Wind";
			case 'W': return "West Wind";
			case 'S': return "South Wind";
			case 'C': return "Red Dragon";
			case 'F': return "Green Dragon";
			default: return "Character " + symbol;
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Color red = new Color(210, 50, 50);
		Color green = new Color(35, 160, 60);
		
		g.setColor(red);
		g.drawString(String.valueOf(symbol), 65, 29-14);
		
		if (symbol >= '1' && symbol <= '9'){
			g.setFont(g.getFont().deriveFont(32.0f));
			g.drawString("\u842c", 32, 89-14);
			g.setColor(Color.black);
			g.drawString(CODE_MAP.get(symbol), 32, 54-14);
		} else {
			g.setFont(g.getFont().deriveFont(59.0f));
			
			switch (symbol)
			{
				case 'C': g.setColor(red); break;
				case 'F': g.setColor(green); break;
				default: g.setColor(Color.black);
			}
			
			g.drawString(CODE_MAP.get(symbol), 18, 82-14);
		}
		
	}
	
		public static void main(String[] args)
	{
		JFrame		frame = new JFrame();
		JPanel		tiles = new JPanel();
		JScrollPane	scroller = new JScrollPane(tiles);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Character Tiles");
		frame.add(scroller);

		// Try something like this if your tiles don't fit on the screen.
		// Replace "tile width" and "tile height" with your values.
		//scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));

		tiles.add(new CharacterTile('1'));
		tiles.add(new CharacterTile('2'));
		tiles.add(new CharacterTile('3'));
		tiles.add(new CharacterTile('4'));
		tiles.add(new CharacterTile('5'));
		tiles.add(new CharacterTile('6'));
		tiles.add(new CharacterTile('7'));
		tiles.add(new CharacterTile('8'));
		tiles.add(new CharacterTile('9'));
		tiles.add(new CharacterTile('N'));
		tiles.add(new CharacterTile('E'));
		tiles.add(new CharacterTile('W'));
		tiles.add(new CharacterTile('S'));
		tiles.add(new CharacterTile('C'));
		tiles.add(new CharacterTile('F'));

		frame.pack();
		frame.setVisible(true);
	}
}