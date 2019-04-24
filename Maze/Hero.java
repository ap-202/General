import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Hero {

	private int width;
	private int height;
	private Location loc;

	public Hero(int width, int height, Location loc) {
		this.height=height;
		this.width=width;
		this.loc=loc;
	}

	public Location getLocation() {return loc;}

	public int getWidth() {return width;}

	public int getHeight() {return height;}

	public void setLocation(Location loc){this.loc=loc;}

	public Ellipse2D getEllipse(){
		return (new Ellipse2D.Double(loc.getX(),loc.getY(),width,height));
	}
	public Ellipse2D getEllipse(int x, int y){
		return (new Ellipse2D.Double(x,y,width,height));
	}

	public void move(ArrayList<Wall> walls, String dir) {
			if(dir.equals("dr") && canMove(walls,dir)){
					loc.moveDown();loc.moveRight();
			}
			else if(dir.equals("dl") && canMove(walls,dir)){
					loc.moveDown();loc.moveLeft();
			}
			else if(dir.equals("ur") && canMove(walls,dir)){
					loc.moveUp();loc.moveRight();
			}
			else if(dir.equals("ul") && canMove(walls,dir)){
					loc.moveUp();loc.moveLeft();
			}
			else if(dir.equals("d") && canMove(walls,dir)){
					loc.moveDown();
			}
			else if(dir.equals("r") && canMove(walls,dir)){
					loc.moveRight();
			}
			else if(dir.equals("l") && canMove(walls,dir)){
					loc.moveLeft();
			}
			else if(dir.equals("u") && canMove(walls,dir)){
					loc.moveUp();
			}
	}

	public boolean canMove(ArrayList<Wall> walls, String dir){
		boolean canMove=true;

		if(dir.equals("dr")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()+2,loc.getY()+2)).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}
		else if(dir.equals("dl")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()-2,loc.getY()+2)).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}
		else if(dir.equals("ur")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()+2,loc.getY()-2)).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}
		else if(dir.equals("ul")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()-2,loc.getY()-2)).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}
		else if(dir.equals("l")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()-2,loc.getY())).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}
		else if(dir.equals("r")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()+2,loc.getY())).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}
		else if(dir.equals("d")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX(),loc.getY()+2)).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}
		else if(dir.equals("u")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX(),loc.getY()-2)).intersects(walls.get(x).getRectangle())){
					canMove=false;
				}
			}
		}

		return canMove;
	}

}
