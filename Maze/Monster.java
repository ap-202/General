import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.Ellipse2D;
public class Monster {

	private int width;
	private int height;
	private Location loc;
	private int noMoveCount;

	public Monster(int width, int height, Location loc) {
		this.height=height;
		this.width=width;
		this.loc=loc;
		noMoveCount=0;
	}

	public Location getLocation() {return loc;}

	public int getWidth() {return width;}

	public int getHeight() {return height;}

	public void setLocation(Location loc){this.loc=loc;}

	public Ellipse2D getEllipse(){
		return (new Ellipse2D.Double(loc.getX(),loc.getY(),width,height));
	}
	public Rectangle getRectangle(){
		return (new Rectangle(loc.getX(),loc.getY(),width-5,height-5));
	}
	public Ellipse2D getEllipse(int x, int y){
		return (new Ellipse2D.Double(x,y,width,height));
	}

	public void move(ArrayList<Wall> walls, String dir, int windowHeight, int windowWidth) {
			if(dir.equals("dr") && canMove(walls,dir,windowHeight,windowWidth)){
					loc.moveDown(2);loc.moveRight(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}
			if(dir.equals("dl") && canMove(walls,dir,windowHeight,windowWidth)){
					loc.moveDown(2);loc.moveLeft(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}
			if(dir.equals("ur") && canMove(walls,dir,windowHeight, windowWidth)){
					loc.moveUp(2);loc.moveRight(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}
			if(dir.equals("ul") && canMove(walls,dir,windowHeight, windowWidth)){
					loc.moveUp(2);loc.moveLeft(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}
			if(dir.equals("d") && canMove(walls,dir, windowHeight,windowWidth)){
					loc.moveDown(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}
			if(dir.equals("r") && canMove(walls,dir, windowHeight, windowWidth)){
					loc.moveRight(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}
			if(dir.equals("l") && canMove(walls,dir, windowHeight, windowWidth)){
					loc.moveLeft(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}
			if(dir.equals("u") && canMove(walls,dir, windowHeight, windowWidth)){
					loc.moveUp(2);noMoveCount=0;
			}else{
				noMoveCount++;
			}

	}
	public boolean canMove(ArrayList<Wall> walls, String dir, int windowHeight, int windowWidth){
		boolean canMove=true;/*
		if(dir.equals("dr")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()+3,loc.getY()+3)).intersects(walls.get(x).getRectangle())){
					if(loc.getX() < windowWidth-getWidth() && loc.getY() < windowHeight-getHeight())
						canMove=false;noMoveCount++;
				}
			}
		}
		else if(dir.equals("dl")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()-3,loc.getY()+3)).intersects(walls.get(x).getRectangle())){
					if(loc.getX() > 0 && loc.getY() < windowHeight-getHeight())
						canMove=false;noMoveCount++;
				}
			}
		}
		else if(dir.equals("ur")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()+3,loc.getY()-3)).intersects(walls.get(x).getRectangle())){
					if(loc.getX() < windowWidth-getWidth() && loc.getY() > 0)
						canMove=false;noMoveCount++;
				}
			}
		}
		else if(dir.equals("ul")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()-3,loc.getY()-3)).intersects(walls.get(x).getRectangle())){
					if(loc.getX() > 0  && loc.getY() > 0)
						canMove=false;noMoveCount++;
				}
			}
		}*/
		if(dir.equals("l")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()-3,loc.getY())).intersects(walls.get(x).getRectangle())){
					if(loc.getX() > 0)
						canMove=false;noMoveCount++;
				}
			}
		}
		else if(dir.equals("r")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX()+3,loc.getY())).intersects(walls.get(x).getRectangle())){
					if(loc.getX() < windowWidth-width)
						canMove=false;noMoveCount++;
				}
			}
		}
		else if(dir.equals("d")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX(),loc.getY()+3)).intersects(walls.get(x).getRectangle())){
					if(loc.getY() < windowHeight-height)
						canMove=false;noMoveCount++;
				}
			}
		}
		else if(dir.equals("u")){
			for(int x=0;x<walls.size();x++){
				if((getEllipse(loc.getX(),loc.getY()-3)).intersects(walls.get(x).getRectangle())){
					if(loc.getY() > 0)
						canMove=false;noMoveCount++;
				}
			}
		}

		return canMove;
	}
	public String decideMove(Hero hero){
	String dir="";/*
		if(hero.getLocation().getX() > loc.getX() && hero.getLocation().getY() < loc.getY()){
			dir="ur";
		}
		if(hero.getLocation().getX() < loc.getX() && hero.getLocation().getY() < loc.getY()){
			dir="ul";
		}
		if(hero.getLocation().getX() > loc.getX() && hero.getLocation().getY() > loc.getY()){
			dir="dr";
		}
		if(hero.getLocation().getX() < loc.getX() && hero.getLocation().getY() < loc.getY()){
			dir="dl";
		}*/
		if(hero.getLocation().getY() < loc.getY()){
			dir="u";
		}
		if(hero.getLocation().getX() > loc.getX()){
			dir="r";
		}
		if(hero.getLocation().getY() > loc.getY()){
			dir="d";
		}
		if(hero.getLocation().getX() < loc.getX()){
			dir="l";
		}
		return dir;
	}

}