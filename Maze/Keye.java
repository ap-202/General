import java.awt.Rectangle;
public class Keye{

	private int width;
	private int height;
	private Location loc;
	char id;
	private boolean hasKey;

	public Keye(int width, int height, Location loc,char id){
		this.width=width;
		this.height=height;
		this.loc=loc;
		this.id=id;
		hasKey=false;
	}

	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public Location getLocation(){return loc;}
	public Rectangle getRectangle(){
		return (new Rectangle(loc.getX(),loc.getY(),width,height));
	}
	public boolean hasKey(){return hasKey;}
	public void setHasKey(boolean b){hasKey=b;}
	public char getID(){return id;}
	public String toString(){
		return width+" "+height+" "+loc.toString()+"\n";
	}
}