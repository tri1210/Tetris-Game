package gameTetris;

public class FigureL extends Figure {
	protected int []arrX;
	protected int []arrY;
	public static int rotati=0;
	protected FigureL(){
		arrX = new int[]{ 1,2,3,3};
		arrY = new int[]{ 2,2,2,3};
		X = new int []{  0,1,2,2};
		Y= new int []{4,4,4,5};
	}
	public void rotation(){
		if(rotati==0){
			X = new int []{1,1,1,2};
			Y= new int []{3,4,5,3};
			rotati=1;
		}
		else if(rotati==1){
			X = new int []{0,0,1,2};
			Y= new int []{3,4,4,4};
			rotati=2;
		}
		else if(rotati==2){
			X = new int []{0,1,1,1};
			Y= new int []{5,3,4,5};
			rotati=3;
		}
		else if(rotati==3){
			X = new int []{  0,1,2,2};
			Y= new int []{4,4,4,5};
			rotati=0;
		}
	}
	@Override
	public int get_rotati() {
		// TODO Auto-generated method stub
		return 0;
	}
}