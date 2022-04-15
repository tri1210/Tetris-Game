package gameTetris;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
public class LashGame extends JWindow {
    
    private static final int W = 567;
    private static final int H = 191;
    
    LashGame() {
        super();
        getContentPane().add(new JLabel(
                new ImageIcon(LoadImage("lashtetris.png"))), 
               BorderLayout.CENTER);
        setSize(new Dimension(W,H));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width/2 - W/2, screenSize.height/2 - H/2);
        setVisible(true);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }
    public Image LoadImage(String image){
		try{
			File file = new File(image);
			InputStream is = new FileInputStream(file);
			Image im = ImageIO.read(new BufferedInputStream(is));
			return im;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
