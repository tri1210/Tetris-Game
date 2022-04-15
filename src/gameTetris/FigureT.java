package gameTetris;

public class FigureT extends Figure {
	protected int []arrX;
	protected int []arrY;
	public int rotati=0;
	protected FigureT(){
		arrX = new int[]{ 1,1,1,2};
		arrY = new int[]{ 1,2,3,2};
		X = new int []{0,0,1,0};
		Y= new int []{4,5,5,6};
	}
	public void rotation(){
		if(rotati==0){
			X = new int []{0,1,1,2};
			Y= new int []{5,5,6,5};
			rotati=1;
		}
		else if(rotati==1){
			X = new int []{0,1,1,1};
			Y= new int []{5,4,5,6};
			rotati=2;
		}
		else if(rotati==2){
			X = new int []{0,1,1,2};
			Y= new int []{5,4,5,5};
			rotati=3;
		}
		else if(rotati==3){
			X = new int []{0,0,1,0};
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
