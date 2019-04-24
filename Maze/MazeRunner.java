import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class MazeRunner extends JPanel implements KeyListener, Runnable{

	JFrame frame;Thread thread;
	boolean gameOn=true;
	boolean right=false;boolean left=false;
	boolean up=false;boolean down=false;
	Location playerLoc; Hero hero; Monster monster;
	int playerRad=25;
	double height;double width;
	int wallWidth=100;int wallHeight=15;
	boolean won=false; boolean switchvar=false;
	Location end; int heroStages;int heroStageCount;
	ArrayList<Wall> walls= new ArrayList<Wall>();
	BufferedImage heroimg;BufferedImage heroimgflip;
	BufferedImage herosubimg[];BufferedImage herosubimgflip[];
	int changeA; ArrayList<Location> monsterLocations= new ArrayList<Location>();
	ArrayList<Monster> monsters=new ArrayList<Monster>();
	Door door; Keye key; boolean hasKey;
	ArrayList<Keye> keys;

	MazeRunner(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		walls=new ArrayList<Wall>();
		keys=new ArrayList<Keye>();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		playerLoc=new Location(0,0);
		hero=new Hero(18,22,playerLoc);
		heroStages=8; heroStageCount=0; changeA=0;
		herosubimg = new BufferedImage[heroStages];
		herosubimgflip = new BufferedImage[heroStages];
		try{
			for(int x=0;x<heroStages-1;x++){
				heroimg=ImageIO.read(new File("hero32.png"));
				herosubimg[x]=heroimg.getSubimage(18*x,0,hero.getWidth(),hero.getHeight());
			}
			int c=heroStages-1;
			for(int x=0;x<heroStages-1;x++){
				heroimgflip=ImageIO.read(new File("hero3flip2.png"));
				herosubimgflip[x]=heroimgflip.getSubimage(18*c,0,hero.getWidth(),hero.getHeight());
				c--;
			}
		}catch(IOException e){}
		walls=createMaze();
				for(int x=0;x<monsterLocations.size();x++){
					monsters.add(new Monster(12,17,monsterLocations.get(x)));
		}
		frame=new JFrame("Maze");
		frame.add(this);
		frame.setSize((int)width,(int)height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(this);
		thread=new Thread(this);
		thread.start();

	}

	public void paintComponent(Graphics g){

		Graphics2D g2=(Graphics2D)g;
		if(won == true){
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 100));
			g2.drawString("YOU WON", 200, 200);
		}
		super.paintComponent(g);
		g2.setColor(Color.WHITE);
		g2.fillRect(0,0,(int)width,(int)height);
		g2.setColor(Color.WHITE);
		if(!switchvar){
			g2.drawImage(herosubimg[heroStageCount],hero.getLocation().getX(),hero.getLocation().getY(),this);
		}
		else{
			g2.drawImage(herosubimgflip[heroStageCount],hero.getLocation().getX(),hero.getLocation().getY(),this);
		}
		g2.setColor(new Color(0,0,0));
		for(int x=0;x<walls.size();x++) {
			g2.setStroke(new BasicStroke(4));
			if(walls.get(x) instanceof Door){
				Door temp=(Door)walls.get(x);
				if(!temp.getOpen()){
					g2.setColor(new Color(1*(temp.getID()),2*temp.getID(),3*(temp.getID())));
					g2.fill(temp.getRectangle());
					g2.setColor(new Color(0,0,0));
				}
			}else{
				g2.fill(walls.get(x).getRectangle());
			}
		}
		g2.setColor(new Color(255,0,0));
		for(int x=0;x<monsterLocations.size();x++){
			g2.fill(monsters.get(x).getEllipse());
		}
		g2.setColor(new Color(24,255,0));
		for(int x=0;x<keys.size();x++){
			if(!keys.get(x).hasKey())
				g2.fill(keys.get(x).getRectangle());
		}
		if(heroStageCount> 4){
			heroStageCount=0;
		}
	}
	public ArrayList<Wall> createMaze(){
		File file = new File("mazeDesign.txt");
		ArrayList<Wall> wal=new ArrayList<Wall>();
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String t;
			int row=0;
			int col=0;
			while((t=in.readLine())!=null && row<height && col<width){
				for(int x=0;x<t.length();x++){
					if(t.charAt(x) == '*'){
						wal.add(new Wall((int)width/80,(int)height/40,new Location(col,row)));
					}
					if(t.charAt(x)=='.'){
						end=new Location(col,row);
					}
					if(t.charAt(x)==','){
						monsterLocations.add(new Location(col,row));
					}
					if(t.charAt(x)>=65 && t.charAt(x)<=90){
						wal.add(new Door((int)width/80,(int)height/40,new Location(col,row), false, t.charAt(x)));
					}
					if(t.charAt(x)>=97 && t.charAt(x)<=122){
						keys.add(new Keye(5,10,new Location(col+6 ,row+6),t.charAt(x)));
					}
				col+=(int)(width)/80;
				}
				row+=(int)(height)/40;
				col=0;
			}
		}catch(IOException io){
			System.err.println("File not here");
		}
		return wal;
	}
	public boolean intersects(Location loc1, Location loc2){
		return(loc2.getX()== loc1.getX() && loc2.getY() == loc1.getY());
	}
	public void run(){
		while(true){
			if(gameOn){
				playerLoc= hero.getLocation();
				for(int y=0;y<monsters.size();y++){
					if(hero.getEllipse().intersects(monsters.get(y).getRectangle())){
						gameOn=false;
						frame.setVisible(gameOn);
						System.out.print("You lose");
					}
				}
				for(int x=0;x<keys.size();x++){
					if(hero.getEllipse().intersects(keys.get(x).getRectangle()))
						keys.get(x).setHasKey(true);
				}
				for(int x=0;x<walls.size();x++){
					if(walls.get(x) instanceof Door){
						Door temp = (Door)walls.get(x);
						if(temp.checkDoorOpen(keys)){
							temp.openDoor();
						}
					}
				}
				if(playerLoc != null && end!=null && intersects(playerLoc,end)){
					gameOn=false;
					System.out.print("You Win");
				}
				if(right && down)
				{
					if(playerLoc.getX() < width-hero.getWidth() && playerLoc.getY() < height-hero.getHeight()){
						hero.move(walls,"dr");
						if(changeA==7){
							changeA=0;
						}
						changeA++;
					}
				}
				if(left && down)
				{
					if(playerLoc.getX() > 0 && playerLoc.getY() < height-hero.getHeight()){
						hero.move(walls,"dl");
						if(changeA==7){
							changeA=0;
						}
						changeA++;
					}
				}
				if(right && up)
				{
					if(playerLoc.getX() < width-hero.getWidth() && playerLoc.getY() > 0){
						hero.move(walls,"ur");
						if(changeA==7){
							changeA=0;
						}
						changeA++;
					}
				}
				if(left && up)
				{
					if(playerLoc.getX() > 0  && playerLoc.getY() > 0){
						hero.move(walls,"ul");
						if(changeA==7){
							changeA=0;
						}
						changeA++;
					}
				}
				if(right) {
					if(playerLoc.getX() < width-playerRad){
						hero.move(walls,"r");
						if(changeA==7){
							heroStageCount++;
							changeA=0;
						}
						switchvar=false;
						changeA++;
					}
				}
				if(left) {
					if(playerLoc.getX() > 0){
						hero.move(walls,"l");
						if(changeA==7){
							heroStageCount++;
							changeA=0;
						}
						switchvar=true;
						changeA++;
					}
				}
				if(up) {
					if(playerLoc.getY() > 0){
						hero.move(walls,"u");
						if(changeA==7){
							heroStageCount++;
							changeA=0;
						}
						changeA++;
					}
				}
				if(down) {
					if(playerLoc.getY() < height-playerRad){
						hero.move(walls,"d");
						if(changeA==7){
							heroStageCount++;
							changeA=0;
						}
						changeA++;
					}
				}
				for(int x=0;x<monsters.size();x++)
					monsters.get(x).move(walls,monsters.get(x).decideMove(hero),(int)height,(int)width);
				repaint();
			}
			try{
				thread.sleep(10);
			}catch(InterruptedException e){}
		}
	}

	public void keyReleased(KeyEvent k){
		if(k.getKeyCode()==68){
			right=false;
			heroStageCount=0;
		}
		if(k.getKeyCode()==87){
			up=false;
			heroStageCount=0;
		}
		if(k.getKeyCode()==65){
			left=false;
			heroStageCount=0;
		}
		if(k.getKeyCode()==83){
			down=false;
			heroStageCount=0;
		}
	}

	public void keyPressed(KeyEvent k){



		if(k.getKeyCode()==68)
			right=true;
		if(k.getKeyCode()==87)
			up=true;
		if(k.getKeyCode()==65)
			left=true;
		if(k.getKeyCode()==83)
			down=true;
		if(k.getKeyCode()==82){
			System.out.println("reset");
			hero.setLocation(new Location(0,0));
		}
		if(k.getKeyCode()==16){
			System.out.println("auto win");
			hero.setLocation(end);
		}
	}

	public void keyTyped(KeyEvent k){

	}

	public static void main(String[] args){
		MazeRunner run = new MazeRunner();
	}

}
