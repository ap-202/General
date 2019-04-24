import java.awt.Rectangle;
import java.util.ArrayList;
public class Door extends Wall{

	boolean open;
	char id;

	public Door(int width, int height, Location loc,boolean open,char id){
		super(width,height,loc);
		this.open=open;
		this.id=id;
	}
	public int getWidth(){return super.getWidth();}
	public int getHeight(){return super.getHeight();}
	public Location getLocation(){return super.getLocation();}
	public Rectangle getRectangle(){
		return super.getRectangle();
	}
	public char getID(){return id;}
	public void openDoor(){open=true;}
	public void closeDoor(){open=false;}
	public boolean getOpen(){return open;}
	public String toString(){
		return getWidth()+" "+getHeight()+" "+getLocation().toString()+"\n";
	}
	public boolean checkDoorOpen(ArrayList<Keye> keys){
		for(int y=0; y<keys.size();y++){
			if(getID()+32 == keys.get(y).getID() && keys.get(y).hasKey())
				return true;
		}
		return false;
	}
}