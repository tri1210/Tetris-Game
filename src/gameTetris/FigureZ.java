package gameTetris;

public class FigureZ extends Figure {
	protected int []arrX;
	protected int []arrY;
	public static int rotati=0;
	protected FigureZ(){
		arrX = new int[]{ 1,1,2,2};
		arrY = new int[]{ 2,3,1,2};
		X = new int []{1,1,0,0};
		Y= new int []{4,5,5,6};
	}
	public void rotation(){
		if(rotati==0){
			X = new int []{0,1,1,2};
			Y= new int []{5,5,6,6};
			rotati=1;
		}
		else if(rotati==1){
			X = new int []{1,1,0,0};
			Y= new int []{4,5,5,6};
			rotati=0;
		}
	}
	@Override
	public int get_rotati() {
		// TODO Auto-generated method stub
		return 0;
	}
}