package gameTetris;

import javax.swing.JLabel;

public class About {
	public JLabel about() {
		JLabel s1 = new JLabel("<html><font color='Green'>Game Xếp hình</font> Thiết kế bởi<br><font color='Red'> "
				+ "Nguyễn Đức Trí - ITITIU19223 <br>"
				+ "Nguyễn Gia Nam - ITITIU19032<br>"
				+ "Phạm Lê Song Tuấn - ITITIU19062<br>"
				+ "Lê Thành Chương - ITITIU19086<br>"
				+ "Lê Quang Tuấn - ITITIU19232</font><br><br>" +
				"WEB SITE : <a href='http://facebook.com/'>https://www.facebook.com/</a><br>" +
				"Lớp : OOP Thầy Tùng - Thầy Phú<br>" +
				"Email : chuacomail@gmail.com<br>" +
				"Phone : 0000 000 000<br><br>" +
				"Đây là ứng dụng game đầu tiên được chúng tôi <br>viết bằng ngôn ngữ java, dưới sự hướng dẫn<br>" +
				"của <font color='Green'>Bác Thợ Săn</font>. Vì là game java đầu tiên<br> nên khó tránh khỏi " +
				"có lỗi xảy ra, nếu có nhu cầu<br> các bạn vui lòng phản ánh với chúng tôi." +
				"</html>");
		return s1;
	}
}
