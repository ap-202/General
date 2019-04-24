public class Location{

	private int x;
	private int y;

	public Location(int x, int y){
		this.x=x;
		this.y=y;
	}

	public int getX(){return x;}
	public int getY(){return y;}
	public void moveRight(){x+=2;}
	public void moveLeft(){x-=2;}
	public void moveUp(){y-=2;}
	public void moveDown(){y+=2;}
	public void moveRight(int s){x+=s;}
	public void moveLeft(int s){x-=s;}
	public void moveUp(int s){y-=s;}
	public void moveDown(int s){y+=s;}
	public String toString(){
		return x+","+y;
	}
}