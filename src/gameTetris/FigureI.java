package gameTetris;

class FigureI extends Figure {
	public int []arrX;
	public int []arrY;
	public FigureI(){
		arrX = new int[]{ 0,1,2,3};
		arrY = new int[]{ 2,2,2,2};
		X = new int []{ 0,1,2,3};
		Y= new int []{ 4,4,4,4};
	}
	public  void rotation(){
		if(rotati==0){
			X = new int []{3,3,3,3};
			Y= new int []{3,4,5,6};
			rotati=1;
		}
		else if(rotati==1){
			X = new int []{ 0,1,2,3};
			Y = new int []{ 4,4,4,4};
			rotati=0;
		}
	}
	public int get_rotati(){
		return rotati;
	}
}