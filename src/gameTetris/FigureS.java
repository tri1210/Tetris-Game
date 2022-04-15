package gameTetris;

public class FigureS extends Figure {
	protected int []arrX;
	protected int []arrY;
	public static int rotati=0;
	protected FigureS(){
		arrX = new int[]{ 1,1,2,2};
		arrY = new int[]{ 1,2,2,3
				};
		X = new int []{0,0,1,1};
		Y= new int []{3,4,4,5};
	}
	public void rotation(){
		if(rotati==0){
			X = new int []{0,1,1,2};
			Y= new int []{5,4,5,4};
			rotati=1;
		}
		else if(rotati==1){
			X = new int []{0,0,1,1};
			Y= new int []{3,4,4,5};
			rotati=0;
		}
	}
	@Override
	public int get_rotati() {
		// TODO Auto-generated method stub
		return 0;
	}
}




























