package gameTetris;

public class FigureO extends Figure //Tạo 1 class figure O
{
	protected int []arrX;
	protected int []arrY;
	protected FigureO(){
		arrX = new int[]{ 1,1,2,2};
		arrY = new int[]{ 1,2,1,2};
		X = new int []{0,1,0,1};
		Y= new int []{4,4,5,5};
	}
	public  void rotation(){
		X = new int []{0,1,0,1};
		Y= new int []{4,4,5,5};
	}
	@Override
	public int get_rotati() {
		// TODO Auto-generated method stub
		return 0;
	}
}
