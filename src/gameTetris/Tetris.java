package gameTetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class Tetris extends JFrame implements KeyListener {
	JMenuBar mainmenu = new JMenuBar();
	JPanel menuPanel = new JPanel();
	protected JPanel game = new JPanel();
	protected JPanel left = new JPanel();
	protected JPanel right = new JPanel();
	protected JPanel[][] cells;
	protected JPanel[][] cells2;
	protected JPanel[][] cell;
	protected JPanel[][] cellr;
	protected JPanel all = new JPanel(new BorderLayout());
	protected JLabel[] picture;
	protected JPanel[] text;
	protected JLabel diem, level;
	protected long d = 0;
	protected int demI = 0;
	protected int demT = 0;
	protected int demO = 0;
	protected int demL = 0;
	protected int demJ = 0;
	protected int demS = 0;
	protected int demZ = 0;
	boolean perfect = false;
	boolean pause = false;
	boolean help = true;
	boolean newg = true;
	protected int lv = 1200;
	protected int cap;
	protected JPanel FP;
	protected JPanel chuatunghinh;
	protected Color curClo;
	Figure Fi;
	public int vitri = 0;
	public int k = -1;
	int[] a = new int[4];
	int[] b = new int[4];
	Random R = new Random();
	public int l = R.nextInt(7);
	FigureI I = new FigureI();
	FigureT T = new FigureT();
	FigureO O = new FigureO();
	FigureL L = new FigureL();
	FigureJ J = new FigureJ();
	FigureS S = new FigureS();
	FigureZ Z = new FigureZ();
	public Color A = new Color(43, 43, 43);
	public Color B = new Color(49, 49, 49);
	private static JMenuBar menuBar = new JMenuBar();
	private static JMenu menuTetris = new JMenu();
	private static JMenu menuHelp = new JMenu();
	private static JMenuItem ItemRestart = new JMenuItem();
	private static JMenuItem ItemPause = new JMenuItem();
	private static JMenuItem ItemHiscore = new JMenuItem();
	public static JMenuItem ItemExit = new JMenuItem();
	private static JMenuItem ItemJetrishelp = new JMenuItem();
	private static JMenuItem ItemAbout = new JMenuItem();
	private static JMenuItem ItemNew = new JMenuItem();
	private static JButton jnewgame = new JButton();
	private static JButton jpause = new JButton();
	private static JButton jhiscore = new JButton();
	private static JButton jrestart = new JButton();
	private static JButton jexit = new JButton();
	HiScore hi = new HiScore();
	// protected JPanel chua = new JPanel(new BoxLayout(chua,BoxLayout.LINE_AXIS));

	public Tetris() {
		super("Xếp hình");
		LashGame sg = new LashGame();
		addKeyListener(this); /// bat su kien
		setIconImage(LoadImage("mtetris.png"));

		// menuPanel.add(mainmenu);
		InitMenu();
		setJMenuBar(menuBar);
		// Panel all
		game = Playgame();
		all.add(game, BorderLayout.CENTER);

		// left Game
		left = SetLeftgame();
		right = SetRightGame();
		all.add(left, BorderLayout.WEST);
		all.add(right, BorderLayout.EAST);
		add(all);
		pack();
		// center screen
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();// HAY QUEN
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);// HAY QUEN
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void InitMenu() {
		MenuHandler Mh = new MenuHandler();
		ImageIcon newgame = new ImageIcon("iconimage.png");
		ImageIcon restart = new ImageIcon("mrestart.png");
		ImageIcon pause = new ImageIcon("mpause.png");
		ImageIcon hiscore = new ImageIcon("mhiscore.png");
		ImageIcon exit = new ImageIcon("mexit.png");
		ImageIcon help = new ImageIcon("mhelp.png");
		ImageIcon about = new ImageIcon("mabout.png");
		menuTetris = new JMenu("Option");
		menuTetris.setMnemonic('J');
		menuHelp = new JMenu("Help");
		menuHelp.setMnemonic('L');

		ItemNew = new JMenuItem("New Game", newgame);
		ItemNew.setMnemonic('N');
		ItemNew.addActionListener(Mh);

		ItemRestart = new JMenuItem("Restart", restart);
		ItemRestart.setMnemonic('R');
		ItemRestart.addActionListener(Mh);

		ItemPause = new JMenuItem("Pause", pause);
		ItemPause.setMnemonic('P');
		ItemPause.addActionListener(Mh);

		ItemHiscore = new JMenuItem("Hiscore", hiscore);
		ItemHiscore.setMnemonic('C');
		ItemHiscore.addActionListener(Mh);

		ItemExit = new JMenuItem("Exit", exit);
		ItemExit.setMnemonic('E');
		ItemExit.addActionListener(Mh);

		ItemJetrishelp = new JMenuItem("Tetris Help", help);
		ItemJetrishelp.setMnemonic('H');
		ItemJetrishelp.addActionListener(Mh);

		ItemAbout = new JMenuItem("About", about);
		ItemAbout.setMnemonic('A');
		ItemAbout.addActionListener(Mh);

		// menu Tetris
		menuTetris.add(ItemNew);
		menuTetris.add(ItemRestart);
		menuTetris.add(ItemPause);
		menuTetris.add(ItemHiscore);
		menuTetris.add(ItemExit);
		// menu Help
		menuHelp.add(ItemJetrishelp);
		menuHelp.add(ItemAbout);
		// menu Bar
		menuBar.add(menuTetris);
		menuBar.add(menuHelp);
	}

	public JPanel Playgame() {
		JPanel center = new JPanel(new GridLayout(20, 10));
		center.setPreferredSize(new Dimension(10 * 24, 20 * 24));// HAY QUEN
		cells = new JPanel[20][10];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				cells[i][j] = new JPanel();
				// cells[i][j] = new ImagePanel(new ImageIcon("in.png").getImage());
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))
					cells[i][j].setBackground(A);
				else
					cells[i][j].setBackground(B);
				cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));// HAY QUEN
				center.add(cells[i][j]);
			}
		}
		return center;
	}

	public JPanel SetLeftgame() {
		JPanel l = new JPanel();
		BoxLayout rl = new BoxLayout(l, BoxLayout.Y_AXIS);
		l.setLayout(rl);
		l.setBorder(new EtchedBorder());
		JPanel JP = new JPanel();
		JP.setLayout(new BoxLayout(JP, BoxLayout.LINE_AXIS));
		JP.add(new JLabel("Static"));
		l.add(Box.createRigidArea(new Dimension(0, 10)));
		l.add(JP);
		l.add(Box.createRigidArea(new Dimension(0, 10)));
		Dimension d = new Dimension(4 * 12, 4 * 12);
		picture = new JLabel[7];
		for (int k = 0; k < 7; k++) {
			cell = new JPanel[4][4];
			chuatunghinh = new JPanel();
			FP = new JPanel();
			FP.setLayout(new BoxLayout(FP, BoxLayout.LINE_AXIS)); // cai nay kho nho nek
			chuatunghinh.setLayout(new GridLayout(4, 4));
			chuatunghinh.setMinimumSize(d);
			chuatunghinh.setPreferredSize(d);
			chuatunghinh.setMaximumSize(d);
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					cell[i][j] = new JPanel();
					cell[i][j].setBackground(null);
					chuatunghinh.add(cell[i][j]);
				}
			}
			int h = k + 1;
			switch (h) {
				case 1: {
					FigureI fi = new FigureI();
					for (int i = 0; i < 4; i++) {
						cell[fi.arrX[i]][fi.arrY[i]].setBackground(fi.COL_I);
						cell[fi.arrX[i]][fi.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					}
				}
					break;
				case 2:
					FigureT ft = new FigureT(); {
					for (int i = 0; i < 4; i++) {
						cell[ft.arrX[i]][ft.arrY[i]].setBackground(ft.COL_T);
						cell[ft.arrX[i]][ft.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					}
				}
					break;
				case 3:
					FigureO fo = new FigureO(); {
					for (int i = 0; i < 4; i++) {
						cell[fo.arrX[i]][fo.arrY[i]].setBackground(fo.COL_O);
						cell[fo.arrX[i]][fo.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					}
				}
					break;
				case 4:
					FigureL fl = new FigureL(); {
					for (int i = 0; i < 4; i++) {
						cell[fl.arrX[i]][fl.arrY[i]].setBackground(fl.COL_L);
						cell[fl.arrX[i]][fl.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					}
				}
					break;
				case 5:
					FigureJ fj = new FigureJ(); {
					for (int i = 0; i < 4; i++) {
						cell[fj.arrX[i]][fj.arrY[i]].setBackground(fj.COL_J);
						cell[fj.arrX[i]][fj.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					}
				}
					break;
				case 6:
					FigureS fs = new FigureS(); {
					for (int i = 0; i < 4; i++) {
						cell[fs.arrX[i]][fs.arrY[i]].setBackground(fs.COL_S);
						cell[fs.arrX[i]][fs.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					}
				}
					break;
				case 7:
					FigureZ fz = new FigureZ(); {
					for (int i = 0; i < 4; i++) {
						cell[fz.arrX[i]][fz.arrY[i]].setBackground(fz.COL_Z);
						cell[fz.arrX[i]][fz.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
					}
				}
					break;
			}
			FP.add(chuatunghinh);
			JLabel text = new JLabel(" X ");
			FP.add(text);

			picture[k] = new JLabel(" 0 ");
			picture[k].setForeground(Color.BLUE);
			FP.add(picture[k]);
			l.add(FP);
			l.add(Box.createRigidArea(new Dimension(100, 15)));
		}
		l.setMinimumSize(new Dimension(150, 0));
		l.setPreferredSize(new Dimension(150, 0));
		l.setMaximumSize(new Dimension(150, 0));
		return l;
	}

	public JPanel SetRightGame() {
		JPanel r = new JPanel();
		// r.add(panel);
		r.setBorder(new EtchedBorder());
		BoxLayout lr = new BoxLayout(r, BoxLayout.Y_AXIS);
		r.setLayout(lr);

		JPanel sharp = new JPanel();

		Dimension d = new Dimension(100, 100);
		sharp.setLayout(new GridLayout(4, 4));
		sharp.setMaximumSize(d);
		sharp.setMinimumSize(d);
		sharp.setPreferredSize(d);
		cellr = new JPanel[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cellr[i][j] = new JPanel();
				sharp.add(cellr[i][j]);
			}
		}

		JLabel nextSharp = new JLabel("Next Shape         ");
		nextSharp.setForeground(Color.BLACK);
		r.add(nextSharp);
		r.add(sharp);
		r.add(Box.createRigidArea(new Dimension(0, 50)));
		JPanel score = new JPanel(new FlowLayout());
		JLabel textdiem = new JLabel("Score : ");
		textdiem.setForeground(Color.BLACK);
		diem = new JLabel("0");
		diem.setForeground(Color.RED);

		JPanel LV = new JPanel(new FlowLayout());
		JLabel textlevel = new JLabel("Level : ");
		textlevel.setForeground(Color.BLACK);
		level = new JLabel("0");
		level.setForeground(Color.RED);
		score.add(textdiem);
		score.add(diem);
		LV.add(textlevel);
		LV.add(level);
		score.setMaximumSize(new Dimension(200, 30));
		LV.setMaximumSize(new Dimension(200, 30));
		score.setBorder(new EtchedBorder());
		LV.setBorder(new EtchedBorder());
		r.add(score);
		r.add(LV);
		r.add(Box.createRigidArea(new Dimension(0, 1)));
		JPanel tutorial = new JPanel();
		tutorial.setLayout(new BoxLayout(tutorial, BoxLayout.Y_AXIS));
		JLabel sTut = new JLabel("Tutorial         ");
		sTut.setForeground(Color.RED);
		JLabel sN = new JLabel("N:");
		sN.setForeground(new Color(142, 7, 158));
		JLabel sP = new JLabel("P:");
		sP.setForeground(Color.BLUE);
		JLabel sR = new JLabel("R:");
		sR.setForeground(Color.RED);
		JLabel sH = new JLabel("H:");
		sH.setForeground(Color.GREEN);
		JLabel sT = new JLabel("T:");
		sT.setForeground(Color.GRAY);
		JLabel sE = new JLabel("E:");
		sE.setForeground(Color.ORANGE);
		JLabel sNewgame = new JLabel(" New game");
		JLabel sPause = new JLabel(" Pause        ");
		JLabel sRestart = new JLabel(" Restart      ");
		JLabel sHiscore = new JLabel(" High score");
		JLabel sPerfect = new JLabel(" Perfect       ");
		JLabel sExit = new JLabel(" Exit              ");
		JPanel pN = new JPanel();
		JPanel pP = new JPanel();
		JPanel pR = new JPanel();
		JPanel pH = new JPanel();
		JPanel pT = new JPanel();
		JPanel pE = new JPanel();
		pN.add(sN);
		pN.add(sNewgame);
		pP.add(sP);
		pP.add(sPause);
		pR.add(sR);
		pR.add(sRestart);
		pH.add(sH);
		pH.add(sHiscore);
		pT.add(sT);
		pT.add(sPerfect);
		pE.add(sE);
		pE.add(sExit);
		pN.setMaximumSize(new Dimension(150, 30));
		pP.setMaximumSize(new Dimension(150, 30));
		pR.setMaximumSize(new Dimension(150, 30));
		pH.setMaximumSize(new Dimension(150, 30));
		pT.setMaximumSize(new Dimension(150, 30));
		pE.setMaximumSize(new Dimension(150, 30));
		tutorial.add(sTut);
		tutorial.add(Box.createRigidArea(new Dimension(0, 20)));
		tutorial.add(pN);
		tutorial.add(pP);
		tutorial.add(pR);
		tutorial.add(pH);
		tutorial.add(pT);
		tutorial.add(pE);
		tutorial.setBorder(new EtchedBorder());
		r.add(tutorial);
		r.setMinimumSize(new Dimension(150, 0));
		r.setPreferredSize(new Dimension(150, 0));
		r.setMaximumSize(new Dimension(150, 0));
		return r;
	}

	public void MoveLeft() {
		boolean stop = true;
		for (int i = 0; i < 4; i++) {
			boolean kt = true;
			for (int j = 0; j < 4; j++) {
				if (a[i] == a[j] && b[i] - 1 == b[j])
					kt = false;
			}
			if (kt == true) {
				if (b[i] - 1 < 0)
					stop = false;
				else if ((cells[a[i]][b[i] - 1].getBackground() != A) && (cells[a[i]][b[i] - 1].getBackground() != B)) {
					stop = false;
				}
			}
		}

		if (stop == true) {
			curClo = cells[a[0]][b[0]].getBackground();
			for (int i = 0; i < 4; i++) {
				if ((a[i] % 2 == 0 && b[i] % 2 == 0) || (a[i] % 2 != 0 && b[i] % 2 != 0))
					cells[a[i]][b[i]].setBackground(A);
				else
					cells[a[i]][b[i]].setBackground(B);
				cells[a[i]][b[i]].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
			for (int i = 0; i < 4; i++) {
				b[i] = b[i] - 1;
				cells[a[i]][b[i]].setBackground(curClo);
				cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
			vitri--;
		}
	}

	public void MoveRight() {
		boolean stop = true;
		for (int i = 0; i < 4; i++) {
			boolean kt = true;
			for (int j = 0; j < 4; j++) {
				if (a[i] == a[j] && b[i] + 1 == b[j])
					kt = false;
			}
			if (kt == true) {
				if (b[i] + 1 >= 10)
					stop = false;
				else if ((cells[a[i]][b[i] + 1].getBackground() != A) && (cells[a[i]][b[i] + 1].getBackground() != B)) {
					stop = false;
				}
			}
		}
		if (stop == true) {
			curClo = cells[a[0]][b[0]].getBackground();
			for (int i = 0; i < 4; i++) {
				if ((a[i] % 2 == 0 && b[i] % 2 == 0) || (a[i] % 2 != 0 && b[i] % 2 != 0))
					cells[a[i]][b[i]].setBackground(A);
				else
					cells[a[i]][b[i]].setBackground(B);
				cells[a[i]][b[i]].setBorder(BorderFactory.createLineBorder(Color.BLACK));

			}
			for (int i = 0; i < 4; i++) {
				b[i] = b[i] + 1;
				cells[a[i]][b[i]].setBackground(curClo);
				cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
			vitri++;
		}

	}

	public void MoveNext() {
		boolean stop = true;
		for (int i = 0; i < 4; i++) {
			boolean kt = true;
			for (int j = 0; j < 4; j++) {
				if (a[i] + 1 == a[j] && b[i] == b[j])
					kt = false;
			}
			if (kt == true) {
				if (a[i] + 1 >= 20)
					stop = false;
				else if ((cells[a[i] + 1][b[i]].getBackground() != A) && (cells[a[i] + 1][b[i]].getBackground() != B)) {
					stop = false;
				}
			}
		}
		if (stop == true) {
			curClo = cells[a[0]][b[0]].getBackground();
			for (int i = 0; i < 4; i++) {
				if (a[i] >= 0) {
					if ((a[i] % 2 == 0 && b[i] % 2 == 0) || (a[i] % 2 != 0 && b[i] % 2 != 0))
						cells[a[i]][b[i]].setBackground(A);
					else
						cells[a[i]][b[i]].setBackground(B);
					cells[a[i]][b[i]].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			}
			for (int i = 0; i < 4; i++) {
				a[i] = a[i] + 1;
				cells[a[i]][b[i]].setBackground(curClo);
				cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
			k++;
		}
	}

	public int kt(int tempa[], int tempb[], int ArC[], int BrC[]) {
		for (int i = 0; i < 4; i++) {
			if (tempb[i] + vitri < 0 || tempb[i] + vitri > 9 || tempa[i] + k > 19) {
				return 0;
			}
			boolean ktq = true;
			for (int j = 0; j < 4; j++) {
				if ((tempa[i] == ArC[j]) && (tempb[i] == BrC[j])) {
					ktq = false;
				}
			}
			if (ktq == true) {
				if ((cells[tempa[i] + k][tempb[i] + vitri].getBackground() != A)
						&& (cells[tempa[i] + k][tempb[i] + vitri].getBackground() != B))
					return 0;
			}
		}
		return 1;
	}

	public int KiemTra() {
		int tt = 0;
		int[] ArC = new int[4];
		int[] BrC = new int[4];
		int[] tempa = new int[4];
		int[] tempb = new int[4];
		Color curC = cells[a[0]][b[0]].getBackground();
		if (curC == FigureI.COL_I) {
			tt = I.rotati;
			ArC = I.X;
			BrC = I.Y;
			I.rotation();
			tempa = I.X;
			tempb = I.Y;
			if (kt(tempa, tempb, ArC, BrC) == 0) {
				I.rotati = tt;
				I.X = ArC;
				I.Y = BrC;
				return 0;
			}
			I.rotati = tt;
			I.X = ArC;
			I.Y = BrC;
			return 1;
		} else if (curC == FigureT.COL_T) {
			tt = T.rotati;
			ArC = T.X;
			BrC = T.Y;
			T.rotation();
			tempa = T.X;
			tempb = T.Y;
			if (kt(tempa, tempb, ArC, BrC) == 0) {
				T.rotati = tt;
				T.X = ArC;
				T.Y = BrC;
				return 0;
			}
			T.rotati = tt;
			T.X = ArC;
			T.Y = BrC;
			return 1;
		} else if (curC == FigureO.COL_O) {
			tt = O.rotati;
			ArC = O.X;
			BrC = O.Y;
			O.rotation();
			tempa = O.X;
			tempb = O.Y;
			if (kt(tempa, tempb, ArC, BrC) == 0) {
				O.rotati = tt;
				O.X = ArC;
				O.Y = BrC;
				return 0;
			}
			O.rotati = tt;
			O.X = ArC;
			O.Y = BrC;
			return 1;
		} else if (curC == FigureL.COL_L) {
			tt = L.rotati;
			ArC = L.X;
			BrC = L.Y;
			L.rotation();
			tempa = L.X;
			tempb = L.Y;
			if (kt(tempa, tempb, ArC, BrC) == 0) {
				L.rotati = tt;
				L.X = ArC;
				L.Y = BrC;
				return 0;
			}
			L.rotati = tt;
			L.X = ArC;
			L.Y = BrC;
			return 1;
		} else if (curC == FigureJ.COL_J) {
			tt = J.rotati;
			ArC = J.X;
			BrC = J.Y;
			J.rotation();
			tempa = J.X;
			tempb = J.Y;
			if (kt(tempa, tempb, ArC, BrC) == 0) {
				J.rotati = tt;
				J.X = ArC;
				J.Y = BrC;
				return 0;
			}
			J.rotati = tt;
			J.X = ArC;
			J.Y = BrC;
			return 1;
		} else if (curC == FigureS.COL_S) {
			tt = S.rotati;
			ArC = S.X;
			BrC = S.Y;
			S.rotation();
			tempa = S.X;
			tempb = S.Y;
			if (kt(tempa, tempb, ArC, BrC) == 0) {
				S.rotati = tt;
				S.X = ArC;
				S.Y = BrC;
				return 0;
			}
			S.rotati = tt;
			S.X = ArC;
			S.Y = BrC;
			return 1;
		} else if (curC == FigureZ.COL_Z) {
			tt = Z.rotati;
			ArC = Z.X;
			BrC = Z.Y;
			Z.rotation();
			tempa = Z.X;
			tempb = Z.Y;
			if (kt(tempa, tempb, ArC, BrC) == 0) {
				Z.rotati = tt;
				Z.X = ArC;
				Z.Y = BrC;
				return 0;
			}
			Z.rotati = tt;
			Z.X = ArC;
			Z.Y = BrC;
			return 1;
		}
		return 0;
	}

	public void MoveRotation() {
		if (KiemTra() == 1) {
			curClo = cells[a[0]][b[0]].getBackground();
			for (int i = 0; i < 4; i++) {
				if (a[i] >= 0) {
					if ((a[i] % 2 == 0 && b[i] % 2 == 0) || (a[i] % 2 != 0 && b[i] % 2 != 0))
						cells[a[i]][b[i]].setBackground(A);
					else
						cells[a[i]][b[i]].setBackground(B);
					cells[a[i]][b[i]].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
			}
			if (curClo == FigureI.COL_I) {
				I.rotation();
				for (int i = 0; i < 4; i++) {

					a[i] = I.X[i] + k;
					b[i] = I.Y[i] + vitri;
					cells[a[i]][b[i]].setBackground(curClo);
					cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				}

			} else if (curClo == FigureT.COL_T) {
				T.rotation();
				for (int i = 0; i < 4; i++) {
					a[i] = T.X[i] + k;
					b[i] = T.Y[i] + vitri;
					cells[a[i]][b[i]].setBackground(curClo);
					cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

				}

			} else if (curClo == FigureO.COL_O) {
				O.rotation();
				for (int i = 0; i < 4; i++) {
					a[i] = O.X[i] + k;
					b[i] = O.Y[i] + vitri;
					cells[a[i]][b[i]].setBackground(curClo);
					cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

				}
			} else if (curClo == FigureL.COL_L) {
				L.rotation();
				for (int i = 0; i < 4; i++) {
					a[i] = L.X[i] + k;
					b[i] = L.Y[i] + vitri;
					cells[a[i]][b[i]].setBackground(curClo);
					cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				}
			} else if (curClo == FigureJ.COL_J) {
				J.rotation();
				for (int i = 0; i < 4; i++) {
					a[i] = J.X[i] + k;
					b[i] = J.Y[i] + vitri;
					cells[a[i]][b[i]].setBackground(curClo);
					cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				}
			} else if (curClo == FigureS.COL_S) {
				S.rotation();
				for (int i = 0; i < 4; i++) {
					a[i] = S.X[i] + k;
					b[i] = S.Y[i] + vitri;
					cells[a[i]][b[i]].setBackground(curClo);
					cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				}
			} else if (curClo == FigureZ.COL_Z) {
				Z.rotation();
				for (int i = 0; i < 4; i++) {
					a[i] = Z.X[i] + k;
					b[i] = Z.Y[i] + vitri;
					cells[a[i]][b[i]].setBackground(curClo);
					cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
				}
			}
		}
	}

	public void nextNow() {
		int t = 0;
		boolean kt = true;
		int x = 0;
		curClo = cells[a[0]][b[0]].getBackground();
		while (true) {
			for (int i = 0; i < 4; i++) {
				boolean flag = true;
				for (int j = 0; j < 4; j++) {
					if (a[i] + 1 == a[j] && b[i] == b[j])
						flag = false;
				}
				if (flag == true) {
					if (a[i] + t <= 18)
						if ((cells[a[i] + t + 1][b[i]].getBackground() != A)
								&& (cells[a[i] + t + 1][b[i]].getBackground() != B)) {
							x = 1;
							kt = false;
						}
				}
				if (a[i] + t >= 18)
					kt = false;
			}
			if (kt)
				t++;
			else
				break;
		}
		if (x == 1)
			t = t - 1;
		for (int i = 0; i < 4; i++) {
			if ((a[i] % 2 == 0 && b[i] % 2 == 0) || (a[i] % 2 != 0 && b[i] % 2 != 0))
				cells[a[i]][b[i]].setBackground(A);
			else
				cells[a[i]][b[i]].setBackground(B);
			cells[a[i]][b[i]].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		k = t + 1;
		for (int i = 0; i < 4; i++) {
			a[i] = a[i] + k;
			cells[a[i]][b[i]].setBackground(curClo);
			cells[a[i]][b[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
	}

	// ham cho menu item
	public void Thoat() {
		JOptionPane.showMessageDialog(this,
				"<html><font color='red' size='50px'>Chúc bạn có 1 ngày làm việc vui vẻ</font></html>", "Help",
				JOptionPane.PLAIN_MESSAGE,
				new ImageIcon(LoadImage("mtetris.png")));
		System.exit(k);
	}

	public void Resest() {
		newg = false;
		vitri = 0;
		k = 0;
		d = 0;
		diem.setText(Long.toString(d));
		cap = 0;
		level.setText(Integer.toString(cap));
		demI = 0;
		demT = 0;
		demO = 0;
		demL = 0;
		demJ = 0;
		demS = 0;
		demZ = 0;
		lv = 1200;
		picture[0].setText(Integer.toString(demI));
		picture[1].setText(Integer.toString(demT));
		picture[2].setText(Integer.toString(demO));
		picture[3].setText(Integer.toString(demL));
		picture[4].setText(Integer.toString(demJ));
		picture[5].setText(Integer.toString(demS));
		picture[6].setText(Integer.toString(demZ));
		// for(int i=0; i<20; i++){
		// for(int j=0; j<10; j++){
		// if((i%2==0 && j%2==0)||(i%2!=0 && j%2!=0))
		// cells[i][j].setBackground(A);
		// else
		// cells[i][j].setBackground(B);
		// cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));// HAY
		// QUEN
		// }
		// }
		l = R.nextInt(7);
	}

	public void anhao() {
		if (perfect) {
			boolean stop = true;
			boolean stop_shape = true;
			int t = 0;
			while (true) {
				for (int i = 0; i < 4; i++) {
					boolean kt = true;
					for (int j = 0; j < 4; j++) {
						if (a[i] + 1 == a[j] && b[i] == b[j]) {
							kt = false;
							break;
						}
					}
					if (kt == true) {
						if (a[i] < 19)
							if ((cells[a[i] + 1 + t][b[i]].getBackground() != A)
									&& (cells[a[i] + 1 + t][b[i]].getBackground() != B)) {
								stop = false;
								stop_shape = false;
							}
						if (a[i] + 1 + t >= 19) {
							stop = false;
						}
					}

				}
				if (stop)
					t++;
				else
					break;
			}

			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 10; j++) {
					if ((cells[i][j].getBackground() == A) || (cells[i][j].getBackground() == B))
						cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					else
						cells[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

				}
			}
			if (stop_shape)
				t++;
			int minCurShape_X = 21;
			int maxCurShape_X = -1;
			boolean kt = true;
			for (int i = 0; i < 4; i++) {
				if (a[i] > maxCurShape_X)
					maxCurShape_X = a[i];
				if (a[i] + t < minCurShape_X) {
					minCurShape_X = a[i] + t;
				}
				if (a[i] + t >= 20) {
					kt = false;
					break;
				} else if ((cells[a[i] + t][b[i]].getBackground() != A)
						&& (cells[a[i] + t][b[i]].getBackground() != B)) {
					kt = false;
					break;
				}
			}
			if (maxCurShape_X < minCurShape_X && kt == true)
				for (int i = 0; i < 4; i++) {
					cells[a[i] + t][b[i]].setBorder(BorderFactory.createLineBorder(Color.WHITE));
				}
		} else {
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 10; j++) {
					if ((cells[i][j].getBackground() == A) || (cells[i][j].getBackground() == B))
						cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					else
						cells[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		int keyCode = evt.getKeyCode();
		if ((keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) && pause == true) {
			MoveRight();
			anhao();
		} else if ((keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) && pause == true) {
			MoveLeft();
			anhao();
		} else if ((keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) && pause == true) {
			MoveNext();
		} else if ((keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) && pause == true) {
			MoveRotation();
			anhao();
		} else if (keyCode == KeyEvent.VK_N) {
			if (!pause)
				pause = !pause;
			Resest();
		} else if (keyCode == KeyEvent.VK_E) {
			System.exit(k);
		} else if (keyCode == KeyEvent.VK_P) {
			pause = !pause;
		} else if (keyCode == KeyEvent.VK_H) {
			hiScore();
		} else if (keyCode == KeyEvent.VK_T) {
			perfect = !perfect;
			anhao();
		} else if (keyCode == KeyEvent.VK_R) {
			Resest();
		} else if (keyCode == KeyEvent.VK_SPACE) {
			boolean kt = true;
			for (int i = 0; i < 4; i++) {
				boolean flag = true;
				for (int j = 0; j < 4; j++) {
					if (a[i] + 1 == a[j] && b[i] == b[j])
						flag = false;
				}
				if (flag == true) {
					if (a[i] == 19)
						kt = false;
					else if ((cells[a[i] + 1][b[i]].getBackground() != A)
							&& (cells[a[i] + 1][b[i]].getBackground() != B))
						kt = false;

				}
			}
			if (kt == true)
				nextNow();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void doAbout() {
		if (pause)
			pause = !pause;
		About ab = new About();
		JOptionPane.showMessageDialog(this, ab.about(), "ABOUT",
				JOptionPane.PLAIN_MESSAGE,
				new ImageIcon(LoadImage("mtetris.png")));
		pause = !pause;
	}

	private void dohelp() {
		if (pause)
			pause = !pause;
		Help he = new Help();
		JOptionPane.showMessageDialog(this, he.help(), "Help",
				JOptionPane.PLAIN_MESSAGE,
				new ImageIcon(LoadImage("mtetris.png")));
		pause = !pause;
	}

	public void setOver() {
		pause = !pause;
		String s;
		do {
			s = JOptionPane.showInputDialog(this, "Nhập tên của bạn...\nĐộ dài của tên nhỏ hơn 10 ký tự", "New HiScore",
					JOptionPane.PLAIN_MESSAGE);
		} while (s != null && (s.length() < 1 || s.length() > 10));
		hi.addTenNguoiChoi(s, d);
		try {
			hi.write_XML("hiscore.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Image LoadImage(String image) {
		try {
			File file = new File(image);
			InputStream is = new FileInputStream(file);
			Image im = ImageIO.read(new BufferedInputStream(is));
			return im;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void hiScore() {
		if (pause)
			pause = !pause;
		JPanel score = new JPanel();
		score = hi.showHiscore();
		JOptionPane.showMessageDialog(this, score, "HiScore",
				JOptionPane.PLAIN_MESSAGE,
				new ImageIcon(LoadImage("mtetris.png")));
		if (!pause)
			pause = !pause;
	}

	public void newGame() {
		newg = false;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))
					cells[i][j].setBackground(A);
				else
					cells[i][j].setBackground(B);
				cells[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			}
		}
		l = R.nextInt(7);
	}

	private class MenuHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				JMenuItem tmp = (JMenuItem) e.getSource();
				if (tmp == ItemExit)
					Thoat();
				if (tmp == ItemRestart)
					Resest();
				if (tmp == ItemNew) {
					if (!pause)
						pause = !pause;
					Resest();
				}
				if (tmp == ItemPause) {
					pause = !pause;
				}
				if (tmp == ItemAbout) {
					doAbout();
				}
				if (tmp == ItemJetrishelp) {
					dohelp();
				}
				if (tmp == ItemHiscore) {
					hiScore();
				}
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
	}
}