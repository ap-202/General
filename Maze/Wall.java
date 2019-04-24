import java.awt.Rectangle;
public class Wall{

	private int width;
	private int height;
	private Location loc;

	public Wall(int width, int height, Location loc){
		this.width=width;
		this.height=height;
		this.loc=loc;
	}

	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public Location getLocation(){return loc;}
	public Rectangle getRectangle(){
		return (new Rectangle(loc.getX(),loc.getY(),width,height));
	}
	public String toString(){
		return width+" "+height+" "+loc.toString()+"\n";
	}
}