package gameTetris;

import javax.swing.JLabel;

public class Help {
	public JLabel help(){
		JLabel s1= new JLabel("<html>"+
		"<center><font color='red'>Hướng dẫn chơi game</font><br><br>"+
				"<font color='red'>-------------------------------------------------</font></center><br>"+
				"<font color='blue'>A >> Thao tác trên bàn phím:</font><br>"+
				
				"<font color='green'> </font>Phím <font color='red'>UP/W :</font> quay khối chữ<br>"+
				"<font color='green'> </font>Phím <font color='red'>DOWN/S :</font> dịch khối chữ xuống 1 ô<br>"+
				"<font color='green'> </font>Phím <font color='red'>LEFT/A :</font> dịch khối chữ sang trái 1 ô<br>"+
				"<font color='green'> </font>Phím <font color='red'>RIGHT/D :</font> dịch khối chữ sang phải 1 ô<br>"+
				"<font color='green'> </font>Phím <font color='red'>N :</font> chơi game mới (New game)<br>"+
				"<font color='green'> </font>Phím <font color='red'>P :</font> tạm dừng và chơi tiếp (pause)<br>"+
				"<font color='green'> </font>Phím <font color='red'>R :</font> Phục hổi lại (Restart)<br>"+
				"<font color='green'> </font>Phím <font color='red'>H : </font>Xem điểm cao nhất (high Score)<br>"+
				"<font color='green'> </font>Phím <font color='red'>T :</font> Hiện bản sau khối hình (Perfect)<br>"+
				"<font color='green'>- </font>Phím <font color='red'>E :</font> Thoát Game (Exit)<br>"+
				"<font color='blue'>B >> Thao tác trên menu của game:</font><br>"+
				"Các chức năng đầy đủ hiển trị trên thanh menu của game. "+
				"</html>");
		return s1;
	}
}
