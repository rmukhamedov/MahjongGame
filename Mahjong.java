import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import	java.beans.*;

public class Mahjong extends JPanel implements MouseListener {
	private	JEditorPane	text = new JEditorPane();
	private LinkedList<Tile> tiles = new LinkedList<Tile>();
	private boolean[][][] map = new boolean[15][8][5];
	private Tile select = null;
	private JPanel side = new JPanel();
	private	JFrame frame = null;
	private JScrollPane scroller = null;
	private boolean t = true;
	private boolean sound = true;
	private Stack<Tile> s = new Stack<Tile>();
	private int score = 0;
	private long seed = 0;
	
	public Mahjong(){
		setLayout(null);
		setBounds(0, 0, 1100, 760);
		seed = System.currentTimeMillis()%1000000;
		generate();
		
		for (Tile t : tiles)
			addTile(t, false);
		
		side.setPreferredSize(new Dimension(100, 7680));
		side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
		side.setBackground(new Color(80, 0, 0));
		scroller = new JScrollPane(side);
		
		frame = new JFrame();
		frame.setSize(1400, 800);
		frame.setLayout(new BorderLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MahJong    " + seed);

		frame.add(scroller, BorderLayout.EAST);
		frame.add(this);
		makeMenu();
		frame.setVisible(true);
	}
	
	public void play(){
		if (JOptionPane.showConfirmDialog(this,
					"Are you sure? Current game will be discarded.", "Start New Game",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION){
						startGame(System.currentTimeMillis()%1000000);
		}
	}
	
	public void startGame(long n){
		seed = n;
		for (Tile t : tiles){
			remove(t);
			side.remove(t);
			t = null;
		}
		score = 0;
		tiles = tiles = new LinkedList<Tile>();
		generate();
		for (Tile t : tiles)
			addTile(t, false);
		repaint();
		side.repaint();
		frame.setTitle("MahJong    " + seed);
	}
	
	public void restart(){
		if (JOptionPane.showConfirmDialog(this,
					"Are you sure? Progress will be lost.", "Restart Game",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION)
					startGame(seed);
	}
	
	public void numbered(){
		String in = JOptionPane.showInputDialog(this, "Seed:", "Load Game Seed");
		if (in == null || in.isEmpty() || !in.matches("[0-9]*"))
			JOptionPane.showMessageDialog(this, "Seed must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
		else
			startGame(Long.parseLong(in));
	}
	
	public void undo(){
		if(!s.empty()){
			Tile t = s.pop();
			side.remove(t);
			addTile(t, true);
			t = s.pop();
			addTile(t, true);
			updateScore(-2);
			frame.repaint();
		}
	}
	
	public void toggleBar(){
		if(t)
			frame.remove(scroller);
		else
			frame.add(scroller, BorderLayout.EAST);
		
		frame.revalidate();
		t = !t;
	}
	
	public void updateScore(int n){
		score += n;
		if (score == 144)
			reward();
	}
	
	public void soundOn(){
		sound = true;
	}
	
	public void soundOff(){
		sound = false;
	}
	
	public void reward(){
		Fireworks f = new Fireworks();
		Frame reward = new JFrame();
		
		reward.addWindowListener(new WindowAdapter()
		{	public void windowClosing(WindowEvent event)
				{ reward.setVisible(false);f.stop();}
		});

		reward.add(f.getPanel());

		reward.setSize(1000,800);
		reward.setTitle("Congratulations");
		f.setExplosions(0,1000);
		f.setSound(sound);
		f.fire();
		reward.setVisible(true);
	}
	
	public void rules(){
		Help h = new Help("Docs/MahjongRules.html", "Mahjong Rules");
		h = null;
	}
	
	public void operation(){
		Help h = new Help("Docs/Operations.html", "Mahjong Operation");
		h = null;
	}

	public void addTile(Tile t, boolean b){
		add(t);
		if(b)
			setComponentZOrder(t, t.zO);
		
			if(t.x == 0 && t.y == 4)
				t.setBounds(((t.x*67+70)+((4-t.z)*14)), (t.y*86-29)-((4-t.z)*14), 81, 100);
			else if (t.x > 12 && t.y == 3)
				t.setBounds(((t.x*67+70)+((4-t.z)*14)), (t.y*86+57)-((4-t.z)*14), 81, 100);
			else if (t.z == 0)
				t.setBounds(((t.x*67+27)+((4-t.z)*14)), (t.y*86-29)-((4-t.z)*14), 81, 100);
			else
				t.setBounds(((t.x*67+70)+((4-t.z)*14)), (t.y*86+14)-((4-t.z)*14), 81, 100);
		
		if(!b)
			t.addMouseListener(this);
		t.setOpaque(false); 
	}
	
	public void mousePressed(MouseEvent me){
		Tile t = (Tile)me.getSource();
		if (isOpen(t)){
			if(select == null){
				select = t;
				t.h = true;
			}else if(t.matches(select)){
				select.h = false;
				removeTile(t);
				side.add(t, 0);
				side.revalidate();
				removeTile(select);
				select = null;
				updateScore(2);
			}else{
				select.h = false;
				select = null;
			}
		} else {
			if(select != null)
				select.h = false;
			select = null;
		}
		repaint();
	}
	
	public void removeTile(Tile t){
		t.zO = getComponentZOrder(t);
		this.remove(t);
		s.push(t);
		map[t.x][t.y][t.z] = false;
	}
	
	public boolean isOpen(Tile t){
		if(t.x == 0 || t.x == 14)
			return true;
		
		if(t.z == 0)
			return true;
		
		if(t.z == 1)
			return !map[7][4][0];
		
		if(t.z == 4 && t.y == 3 && t.x == 1)
			return !(map[0][4][4] && map[2][3][4]);
		
		if(t.z == 4 && t.y == 4 && t.x == 12)
			return !(map[t.x+1][3][t.z] && map[t.x-1][t.y][t.z]);
		
		return !(map[t.x][t.y][t.z-1] || (map[t.x-1][t.y][t.z] && map[t.x+1][t.y][t.z]));
	}
	
	public void generate(){
		int i = 0;
		for(int a = 0; a < 4; a++){
			for(int d = 1; d < 10; d++){
				tiles.add(new CharacterTile(Character.forDigit(d, 10)));
				i++;
				tiles.add(new CircleTile(d));
				i++;
				if(d != 1){
					tiles.add(new BambooTile(d));
					i++;
				}
			}
			tiles.add(new Bamboo1Tile());i++;
			tiles.add(new CharacterTile('N'));i++;
			tiles.add(new CharacterTile('E'));i++;
			tiles.add(new CharacterTile('W'));i++;
			tiles.add(new CharacterTile('S'));i++;
			tiles.add(new CharacterTile('C'));i++;
			tiles.add(new CharacterTile('F'));i++;
			tiles.add(new WhiteDragonTile());i++;
		}
	        
		tiles.add(new FlowerTile("Chrysanthemum"));i++;
		tiles.add(new FlowerTile("Orchid"));i++;
		tiles.add(new FlowerTile("Plum"));i++;
		tiles.add(new FlowerTile("Bamboo"));i++;

		tiles.add(new SeasonTile("Spring"));i++;
		tiles.add(new SeasonTile("Summer"));i++;
		tiles.add(new SeasonTile("Fall"));i++;
		tiles.add(new SeasonTile("Winter"));i++;
		
		Collections.shuffle(tiles, new Random(seed));

		i = 0;
		tiles.get(i).setXYZ(7, 4, 0);map[7][4][0] = true;i++;
		tiles.get(i).setXYZ(6, 4, 1);map[6][4][1] = true;i++;
		tiles.get(i).setXYZ(7, 4, 1);map[7][4][1] = true;i++;
		tiles.get(i).setXYZ(6, 3, 1);map[6][3][1] = true;i++;
		tiles.get(i).setXYZ(7, 3, 1);map[7][3][1] = true;i++;

		for(int y = 5; y > 1; y--)
			for(int x = 5; x < 9; x++){
				tiles.get(i).setXYZ(x, y, 2);
				map[x][y][2] = true;
				i++;
			}

		for(int y = 6; y > 0; y--)
			for(int x = 4; x < 10; x++){
				tiles.get(i).setXYZ(x, y, 3);
				map[x][y][3] = true;
				i++;
			}

		for(int y = 7; y > -1; y--)
			for(int x = 0; x < 15; x++){
				if ((x >= 3 && x <= 10)
					|| ((y == 0 || y == 3 || y == 4 || y == 7) && (x >= 1 && x <= 12)) 
					|| ((y >= 2 && y <= 5) && (x >= 2 && x <= 11)) 
					|| ((x == 0) && (y == 4)) || ((y == 3) && (x == 13 || x == 14))){
						tiles.get(i).setXYZ(x, y, 4);
						map[x][y][4] = true;
						i++;
				}
			}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(new Color(225, 205, 205));
		
		Image image = Toolkit.getDefaultToolkit().getImage("images/dragon_bg.png");
		g.drawImage(image, 248, 163, this);
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 60, 20);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, 60, 20);
		g.drawLine(20, 0, 20, 20);
		g.drawLine(40, 0, 40, 20);
		
		g.setFont(new Font("Consolas", Font.PLAIN, 27));
		g.drawString(String.valueOf(score/100), 3, 19);
		g.drawString(String.valueOf((score%100)/10), 23, 19);
		g.drawString(String.valueOf(score%10), 43, 19);
		g.setColor(new Color(200, 0, 30));
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	private JMenuItem makeMenuItem(String label, String tip, String accelerator, char mnemonic,
			String method, Object target)
	{
		JMenuItem	menuItem = new JMenuItem(label, mnemonic);
		menuItem.setToolTipText(tip);				// adds tool tip text
		menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator));

		// sets up event handling: "method" is called when "menuItem" is selected
		menuItem.addActionListener(EventHandler.create(ActionListener.class,
					target, method));

		return menuItem;
	}
	
	private JMenuItem makeRadioMenuItem(String label, String tip, boolean selected, ButtonGroup group,
			String method, Object target)
	{
		JRadioButtonMenuItem	radioItem = new JRadioButtonMenuItem(label, selected);
		radioItem.setToolTipText(tip);				// adds tool tip text
		group.add(radioItem);

		// sets up event handling: "method" is called when "menuItem" is selected
		radioItem.addActionListener((ActionListener)EventHandler.create(ActionListener.class,
					target, method));

		return radioItem;
	}

	
	private void makeMenu(){
		JMenuBar	menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu	gameMenu = makeMenu("Game", '1');
		gameMenu.add(makeMenuItem("Play", "Start new game", "ctrl P", 'P', "play", this));
		gameMenu.add(makeMenuItem("Restart", "Restart current game", "ctrl R", 'R', "restart", this));
		gameMenu.add(makeMenuItem("Numbered", "Play numbered game", "ctrl N", 'N', "numbered", this));
		menuBar.add(gameMenu);
		
		JMenu	moveMenu = makeMenu("Move", '2');
		moveMenu.add(makeMenuItem("Undo", "Restore last tile removed", "ctrl U", 'U', "undo", this));
		moveMenu.add(makeMenuItem("Show Sidebar", "Show removed tiles", "ctrl B", 'B', "toggleBar", this));
		menuBar.add(moveMenu);
		
		JMenu	soundMenu = makeMenu("Sound", '3');
		ButtonGroup	group = new ButtonGroup();
		soundMenu.add(makeRadioMenuItem("On", "Sound on", true, group, "soundOn", this));
		soundMenu.add(makeRadioMenuItem("Off", "Sound off", true, group, "soundOff", this));
		menuBar.add(soundMenu);
		
		JMenu	helpMenu = makeMenu("Help", '4');
		helpMenu.add(makeMenuItem("Operation", "Program help", "ctrl O", 'O', "operation", this));
		helpMenu.add(makeMenuItem("Game Rules", "Game Rules", "ctrl H", 'H', "rules", this));
		menuBar.add(helpMenu);
	}
	
	private JMenu makeMenu(String label, char mnemonic){
		JMenu	menu = new JMenu(label);
		menu.setMnemonic(mnemonic);
		return menu;
	}
	
	public static void main(String[] args)
	{
		new Mahjong();
	}
}