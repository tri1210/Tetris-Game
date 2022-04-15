package gameTetris;

import java.awt.Color;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

public class Run_tetris extends Thread {

	Tetris jt = new Tetris();

	public void Xoa1(int i) {
		Color color = Color.WHITE;
		for (int k = 1; k <= 7; k++) {
			if (k == 1)
				color = Color.RED;
			if (k == 2)
				color = Color.YELLOW;
			if (k == 3)
				color = Color.BLUE;
			if (k == 4)
				color = Color.GREEN;
			if (k == 5)
				color = Color.BLACK;
			if (k == 6)
				color = Color.ORANGE;
			if (k == 7)
				color = Color.LIGHT_GRAY;
			for (int j = 0; j < 10; j++) {
				jt.cells[i][j].setBackground(color);
			}
			try {
				sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void Xoa2() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))
					jt.cells[j][i].setBackground(jt.B);
				else
					jt.cells[j][i].setBackground(jt.A);
			}
			try {
				sleep(100);
				for (int j = 0; j < 20; j++) {
					if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0))
						jt.cells[j][i].setBackground(jt.A);
					else
						jt.cells[j][i].setBackground(jt.B);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void AddScore() {
		jt.d += 100;
		jt.diem.setText(Long.toString(jt.d));
		if (jt.d < 500) {
			jt.lv = 1200;
			jt.cap = 1;
		} else if (jt.d < 1000) {
			jt.lv = 1100;
			jt.cap = 2;
		} else if (jt.d < 2000) {
			jt.lv = 1050;
			jt.cap = 3;
		} else if (jt.d < 4000) {
			jt.lv = 1000;
			jt.cap = 4;
		} else if (jt.d < 6000) {
			jt.lv = 950;
			jt.cap = 5;
		} else if (jt.d < 8000) {
			jt.lv = 800;
			jt.cap = 6;
		} else if (jt.d < 10000) {
			jt.lv = 750;
			jt.cap = 7;
		} else if (jt.d < 12000) {
			jt.lv = 700;
			jt.cap = 8;
		} else if (jt.d < 14000) {
			jt.lv = 650;
			jt.cap = 9;
		} else if (jt.d < 16000) {
			jt.lv = 600;
			jt.cap = 10;
		} else if (jt.d < 18000) {
			jt.lv = 550;
			jt.cap = 12;
		} else if (jt.d < 20000) {
			jt.lv = 500;
			jt.cap = 12;
		} else if (jt.d < 22000) {
			jt.lv = 450;
			jt.cap = 13;
		} else if (jt.d < 24000) {
			jt.lv = 400;
			jt.cap = 14;
		} else if (jt.d < 26000) {
			jt.lv = 350;
			jt.cap = 15;
		} else if (jt.d < 28000) {
			jt.lv = 300;
			jt.cap = 16;
		} else if (jt.d < 30000) {
			jt.lv = 250;
			jt.cap = 17;
		} else if (jt.d < 32000) {
			jt.lv = 200;
			jt.cap = 18;
		} else if (jt.d < 34000) {
			jt.lv = 150;
			jt.cap = 19;
		} else if (jt.d < 36000) {
			jt.lv = 100;
			jt.cap = 20;
		} else {
			jt.lv = 50;
			jt.cap = 21;
		}
		jt.level.setText(Integer.toString(jt.cap));
	}

	public void Xoa(int a[]) {
		boolean kt;
		for (int i = 0; i < 4; i++) {
			kt = true;
			for (int j = 0; j < 10; j++) {
				if ((jt.cells[a[i]][j].getBackground() == jt.A) || (jt.cells[a[i]][j].getBackground() == jt.B))
					kt = false;
			}
			if (kt == true) {
				AddScore();
				Xoa1(a[i]);
				for (int j = 0; j < 10; j++) {
					for (int k = a[i]; k > 0; k--) {
						if (jt.cells[k - 1][j].getBackground() == jt.A)
							jt.cells[k][j].setBackground(jt.B);
						else if (jt.cells[k - 1][j].getBackground() == jt.B)
							jt.cells[k][j].setBackground(jt.A);
						else
							jt.cells[k][j].setBackground(jt.cells[k - 1][j].getBackground());
						jt.cells[k][j].setBorder(jt.cells[k - 1][j].getBorder());
					}
				}
			}
		}
	}

	public void nextShape(int l) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				jt.cellr[i][j].setBackground(null);
				jt.cellr[i][j].setBorder(null);
			}
		}
		if (jt.l == 0) {
			for (int i = 0; i < 4; i++) {
				jt.cellr[jt.I.arrX[i]][jt.I.arrY[i]].setBackground(jt.I.COL_I);
				jt.cellr[jt.I.arrX[i]][jt.I.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
		}
		if (jt.l == 1) {
			for (int i = 0; i < 4; i++) {
				jt.cellr[jt.T.arrX[i]][jt.T.arrY[i]].setBackground(jt.T.COL_T);
				jt.cellr[jt.T.arrX[i]][jt.T.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
		}
		if (jt.l == 2) {
			for (int i = 0; i < 4; i++) {
				jt.cellr[jt.O.arrX[i]][jt.O.arrY[i]].setBackground(jt.O.COL_O);
				jt.cellr[jt.O.arrX[i]][jt.O.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
		}
		if (jt.l == 3) {
			for (int i = 0; i < 4; i++) {
				jt.cellr[jt.L.arrX[i]][jt.L.arrY[i]].setBackground(jt.L.COL_L);
				jt.cellr[jt.L.arrX[i]][jt.L.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
		}
		if (jt.l == 4) {
			for (int i = 0; i < 4; i++) {
				jt.cellr[jt.J.arrX[i]][jt.J.arrY[i]].setBackground(jt.J.COL_J);
				jt.cellr[jt.J.arrX[i]][jt.J.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
		}
		if (jt.l == 5) {
			for (int i = 0; i < 4; i++) {
				jt.cellr[jt.S.arrX[i]][jt.S.arrY[i]].setBackground(jt.S.COL_S);
				jt.cellr[jt.S.arrX[i]][jt.S.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
		}
		if (jt.l == 6) {
			for (int i = 0; i < 4; i++) {
				jt.cellr[jt.Z.arrX[i]][jt.Z.arrY[i]].setBackground(jt.Z.COL_Z);
				jt.cellr[jt.Z.arrX[i]][jt.Z.arrY[i]].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			}
		}
	}

	public boolean overGame() {
		if (jt.k == 0) {
			boolean an = true;
			for (int i = 0; i < 4; i++) {
				boolean tinh = true;
				for (int j = 0; j < 4; j++) {
					if (jt.a[i] + 1 == jt.a[j] && jt.b[i] == jt.b[j])
						tinh = false;
				}
				if (tinh == true) {
					if (jt.a[i] >= 19)
						break;
					if ((jt.cells[jt.a[i] + 1][jt.b[i]].getBackground() != jt.A)
							|| (jt.cells[jt.a[i] + 1][jt.b[i]].getBackground() != jt.B))
						an = false;
				}
			}
			if (an == false)
				return false;
		}
		return true;

	}

	public Run_tetris() {
		int ll = 0;
		Random rd = new Random();
		while (true) {
			if (overGame() == false) {
				jt.setOver();
				Xoa2();
			}
			ll = jt.l;

			if (ll == 0)
				jt.picture[0].setText(Integer.toString(++jt.demI));
			else if (ll == 1)
				jt.picture[1].setText(Integer.toString(++jt.demT));
			else if (ll == 2)
				jt.picture[2].setText(Integer.toString(++jt.demO));
			else if (ll == 3)
				jt.picture[3].setText(Integer.toString(++jt.demL));
			else if (ll == 4)
				jt.picture[4].setText(Integer.toString(++jt.demJ));
			else if (ll == 5)
				jt.picture[5].setText(Integer.toString(++jt.demS));
			else if (ll == 6)
				jt.picture[6].setText(Integer.toString(++jt.demZ));

			jt.l = rd.nextInt(7);
			jt.vitri = 0;
			for (jt.k = 0; jt.k < 20; jt.k++) {
				if (!jt.pause)
					jt.k--;
				else {
					nextShape(jt.l);
					if (!jt.newg) {
						Xoa2();
						ll = rd.nextInt(7);
						jt.newg = true;
					}
					switch (ll) {
						case 0: {
							for (int i = 0; i < 4; i++) {
								jt.a[i] = jt.I.X[i] + jt.k;
								jt.b[i] = jt.I.Y[i] + jt.vitri;
								if (jt.I.Y[i] + jt.vitri < 10 && jt.I.Y[i] + jt.vitri >= 0) {
									jt.cells[jt.a[i]][jt.b[i]].setBackground(jt.I.COL_I);
									jt.cells[jt.a[i]][jt.b[i]]
											.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
								}
								jt.anhao();
							}

						}
							break;
						case 1: {
							for (int i = 0; i < 4; i++) {
								if (jt.T.Y[i] + jt.vitri < 10 && jt.T.Y[i] + jt.vitri >= 0) {
									jt.cells[jt.T.X[i] + jt.k][jt.T.Y[i] + jt.vitri].setBackground(jt.T.COL_T);
									jt.cells[jt.T.X[i] + jt.k][jt.T.Y[i] + jt.vitri]
											.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
								}
								jt.a[i] = jt.T.X[i] + jt.k;
								jt.b[i] = jt.T.Y[i] + jt.vitri;
							}
							jt.anhao();
						}
							break;
						case 2: {
							for (int i = 0; i < 4; i++) {
								if (jt.O.Y[i] + jt.vitri < 10 && jt.O.Y[i] + jt.vitri >= 0) {
									jt.cells[jt.O.X[i] + jt.k][jt.O.Y[i] + jt.vitri].setBackground(jt.O.COL_O);
									jt.cells[jt.O.X[i] + jt.k][jt.O.Y[i] + jt.vitri]
											.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
								}

								jt.a[i] = jt.O.X[i] + jt.k;
								jt.b[i] = jt.O.Y[i] + jt.vitri;
							}
							jt.anhao();
						}
							break;
						case 3: {
							for (int i = 0; i < 4; i++) {
								if (jt.L.Y[i] + jt.vitri < 10 && jt.L.Y[i] + jt.vitri >= 0) {
									jt.cells[jt.L.X[i] + jt.k][jt.L.Y[i] + jt.vitri].setBackground(jt.L.COL_L);
									jt.cells[jt.L.X[i] + jt.k][jt.L.Y[i] + jt.vitri]
											.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
								}
								jt.a[i] = jt.L.X[i] + jt.k;
								jt.b[i] = jt.L.Y[i] + jt.vitri;
							}
							jt.anhao();
						}
							break;
						case 4: {
							for (int i = 0; i < 4; i++) {
								if (jt.J.Y[i] + jt.vitri < 10 && jt.J.Y[i] + jt.vitri >= 0) {
									jt.cells[jt.J.X[i] + jt.k][jt.J.Y[i] + jt.vitri].setBackground(jt.J.COL_J);
									jt.cells[jt.J.X[i] + jt.k][jt.J.Y[i] + jt.vitri]
											.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
								}
								jt.a[i] = jt.J.X[i] + jt.k;
								jt.b[i] = jt.J.Y[i] + jt.vitri;
							}
							jt.anhao();
						}
							break;
						case 5: {
							for (int i = 0; i < 4; i++) {
								if (jt.S.Y[i] + jt.vitri < 10 && jt.S.Y[i] + jt.vitri >= 0) {
									jt.cells[jt.S.X[i] + jt.k][jt.S.Y[i] + jt.vitri].setBackground(jt.S.COL_S);
									jt.cells[jt.S.X[i] + jt.k][jt.S.Y[i] + jt.vitri]
											.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
								}
								jt.a[i] = jt.S.X[i] + jt.k;
								jt.b[i] = jt.S.Y[i] + jt.vitri;
							}
							jt.anhao();
						}
							break;
						case 6: {
							for (int i = 0; i < 4; i++) {
								if (jt.Z.Y[i] + jt.vitri < 10 && jt.Z.Y[i] + jt.vitri >= 0) {
									jt.cells[jt.Z.X[i] + jt.k][jt.Z.Y[i] + jt.vitri].setBackground(jt.Z.COL_Z);
									jt.cells[jt.Z.X[i] + jt.k][jt.Z.Y[i] + jt.vitri]
											.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
								}
								jt.a[i] = jt.Z.X[i] + jt.k;
								jt.b[i] = jt.Z.Y[i] + jt.vitri;
							}
							jt.anhao();
						}
							break;
						default:
							ll = 1;
							break;
					}
					try {
						sleep(jt.lv);
						boolean stop = true;
						for (int i = 0; i < 4; i++) {
							boolean kt = true;
							for (int j = 0; j < 4; j++) {
								if (jt.a[i] + 1 == jt.a[j] && jt.b[i] == jt.b[j])
									kt = false;
							}
							if (kt == true) {
								if (jt.a[i] + 1 >= 20)
									stop = false;
								else if ((jt.cells[jt.a[i] + 1][jt.b[i]].getBackground() != jt.A)
										&& (jt.cells[jt.a[i] + 1][jt.b[i]].getBackground() != jt.B)) {
									stop = false;
								}
							}
						}
						if (jt.k == 19 || stop == false) {
							Xoa(jt.a);
							break;
						}
						for (int i = 0; i < 4; i++) {
							if (jt.a[i] >= 0) {
								if ((jt.a[i] % 2 == 0 && jt.b[i] % 2 == 0) || (jt.a[i] % 2 != 0 && jt.b[i] % 2 != 0))
									jt.cells[jt.a[i]][jt.b[i]].setBackground(jt.A);
								else
									jt.cells[jt.a[i]][jt.b[i]].setBackground(jt.B);
								jt.cells[jt.a[i]][jt.b[i]].setBorder(BorderFactory.createLineBorder(Color.BLACK));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
