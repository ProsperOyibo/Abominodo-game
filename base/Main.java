package base;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.*;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.*;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * @author Kevan Buckley, maintained by __student
 * @version 2.0, 2014
 */

public class Main {

	private static final int GRID_COLUMN = 8;
	private static final int GRID_ROW = 7;
	public static final int DOMINO_COUNT = 28;
	private String playerName;
	public List<Domino> _d;
	public List<Domino> _g;
	public int[][] grid = new int[7][8];
	public int[][] gg = new int[7][8];
	int mode = -1;
	int cheatCount;
	int score;
	long startTime;

	PictureFrame pf = new PictureFrame();
	public HighScore highScore;

	private void generateDominoes() {
		_d = new LinkedList<Domino>();
		_g = new LinkedList<Domino>();
		int count = 0;
		int x = 0;
		int y = 0;
		for (int l = 0; l <= 6; l++) {
			for (int h = l; h <= 6; h++) {
				Domino d = new Domino(h, l);
				Domino g = new Domino(h, l);
				_g.add(g);
				_d.add(d);
				d.place(x, y, x + 1, y);
				count++;
				x += 2;
				if (x > 6) {
					x = 0;
					y++;
				}
			}
		}
		dominoAmount(count);
	}

	public void dominoAmount(int count) {
		if (count != DOMINO_COUNT) {
			System.out.println("something went wrong generating dominoes");
			System.exit(0);
		}
	}

	void collateGrid() {
		for (Domino d : _d) {
			if (!d.placed) {
				grid[d.hy][d.hx] = 9;
				grid[d.ly][d.lx] = 9;
			} else {
				grid[d.hy][d.hx] = d.high;
				grid[d.ly][d.lx] = d.low;
			}
		}
	}

	void collateGuessGrid() {
		for (int row = 0; row < 7; row++) {
			for (int column = 0; column < 8; column++) {
				gg[row][column] = 9;
			}
		}
		for (Domino d : _g) {
			if (d.placed) {
				gg[d.hy][d.hx] = d.high;
				gg[d.ly][d.lx] = d.low;
			}
		}
	}

	public int printGrid() {
		for (int row = 0; row < GRID_ROW; row++) {
			for (int column = 0; column < GRID_COLUMN; column++) {
				if (grid[row][column] != 9) {
					System.out.printf("%d", grid[row][column]);
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		return 11;
	}

	public int printGuessGrid() {
		for (int row = 0; row < GRID_ROW; row++) {
			for (int column = 0; column < GRID_COLUMN; column++) {
				if (gg[row][column] != 9) {
					System.out.printf("%d", gg[row][column]);
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		return 11;
	}

	private void shuffleDominoesOrder() {
		List<Domino> shuffled = new LinkedList<Domino>();

		while (_d.size() > 0) {
			int n = (int) (Math.random() * _d.size());
			shuffled.add(_d.get(n));
			_d.remove(n);
		}

		_d = shuffled;
	}

	private void invertSomeDominoes() {
		for (Domino d : _d) {
			if (Math.random() > 0.5) {
				d.invert();
			}
		}
	}

	private void placeDominoes() {
		int x = 0;
		int y = 0;
		int count = 0;
		for (Domino d : _d) {
			count++;
			d.place(x, y, x + 1, y);
			x += 2;
			if (x > 6) {
				x = 0;
				y++;
			}
		}
		dominoAmount(count);
	}

	private void rotateDominoes() {
		// for (Domino d : dominoes) {
		// if (Math.random() > 0.5) {
		// System.out.println("rotating " + d);
		// }
		// }
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 6; y++) {

				tryToRotateDominoAt(x, y);
			}
		}
	}

	private void tryToRotateDominoAt(int x, int y) {
		Domino d = findDominoAt(x, y);
		if (thisIsTopLeftOfDomino(x, y, d)) {
			if (d.ishl()) {
				boolean weFancyARotation = Math.random() < 0.5;
				if (weFancyARotation) {
					if (theCellBelowIsTopLeftOfHorizontalDomino(x, y)) {
						Domino e = findDominoAt(x, y + 1);
						e.hx = x;
						e.lx = x;
						d.hx = x + 1;
						d.lx = x + 1;
						e.ly = y + 1;
						e.hy = y;
						d.ly = y + 1;
						d.hy = y;
					}
				}
			} else {
				boolean weFancyARotation = Math.random() < 0.5;
				if (weFancyARotation) {
					if (theCellToTheRightIsTopLeftOfVerticalDomino(x, y)) {
						Domino e = findDominoAt(x + 1, y);
						e.hx = x;
						e.lx = x + 1;
						d.hx = x;
						d.lx = x + 1;
						e.ly = y + 1;
						e.hy = y + 1;
						d.ly = y;
						d.hy = y;
					}
				}

			}
		}
	}

	private boolean theCellToTheRightIsTopLeftOfVerticalDomino(int x, int y) {
		Domino e = findDominoAt(x + 1, y);
		return thisIsTopLeftOfDomino(x + 1, y, e) && !e.ishl();
	}

	private boolean theCellBelowIsTopLeftOfHorizontalDomino(int x, int y) {
		Domino e = findDominoAt(x, y + 1);
		return thisIsTopLeftOfDomino(x, y + 1, e) && e.ishl();
	}

	private boolean thisIsTopLeftOfDomino(int x, int y, Domino d) {
		return (x == Math.min(d.lx, d.hx)) && (y == Math.min(d.ly, d.hy));
	}

	private Domino findDominoAt(int x, int y) {
		for (Domino d : _d) {
			if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
				return d;
			}
		}
		return null;
	}

	private Domino findGuessAt(int x, int y) {
		for (Domino d : _g) {
			if ((d.lx == x && d.ly == y) || (d.hx == x && d.hy == y)) {
				return d;
			}
		}
		return null;
	}

	private Domino findGuessByLH(int x, int y) {
		for (Domino d : _g) {
			if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
				return d;
			}
		}
		return null;
	}

	private Domino findDominoByLH(int x, int y) {
		for (Domino d : _d) {
			if ((d.low == x && d.high == y) || (d.high == x && d.low == y)) {
				return d;
			}
		}
		return null;
	}

	private void printDominoes() {
		for (Domino d : _d) {
			System.out.println(d);
		}
	}

	private void printGuesses() {
		for (Domino d : _g) {
			System.out.println(d);
		}
	}

	public final int ZERO = 0;
	
	public String getPlayerName() {
		return this.playerName;
	}

	public void run() {
		IOSpecialist io = new IOSpecialist();
		welcomeMessage();
		int optionNumber = -9;
		while (optionNumber != ZERO) {
			mainMenuOption();
			optionNumber = getOption(io);
			switch (optionNumber) {
			case 5:
				getInspiration();
				break;
			case 0: {
				endGame();
				break;
			}
			case 1: {
				selectDifficulty();
				int difficulty = getDifficulty();
				switch (difficulty) {
				case 1:
					generateDominoes();
					shuffleDominoesOrder();
					placeDominoes();
					collateGrid();
					// printGrid();
					break;
				case 2:
					generateDominoes();
					shuffleDominoesOrder();
					placeDominoes();
					rotateDominoes();
					collateGrid();
					// printGrid();
					break;
				default:
					generateDominoes();
					shuffleDominoesOrder();
					placeDominoes();
					rotateDominoes();
					rotateDominoes();
					rotateDominoes();
					invertSomeDominoes();
					collateGrid();
					break;
				}
				printGrid();
				collateGuessGrid();
				mode = 1;
				cheatCount = 0;
				score = 0;
				startTime = System.currentTimeMillis();
				pf.PictureFrame(this);
				pf.dp.repaint();
				int c3 = -7;
				while (c3 != ZERO) {
					System.out.println();
					String h5 = "Play menu";
					String u5 = h5.replaceAll(".", "=");
					System.out.println(u5);
					System.out.println(h5);
					System.out.println(u5);
					System.out.println("1) Print the grid");
					System.out.println("2) Print the box");
					System.out.println("3) Print the dominos");
					System.out.println("4) Place a domino");
					System.out.println("5) Unplace a domino");
					System.out.println("6) Get some assistance");
					System.out.println("7) Check your score");
					System.out.println("0) Given up");
					System.out.println("What do you want to do " + playerName + "?");
					c3 = 9;
					// make sure the user enters something valid
					while (!((c3 == 1 || c3 == 2 || c3 == 3)) && (c3 != 4)
							&& (c3 != ZERO) && (c3 != 5) && (c3 != 6) && (c3 != 7)) {
						try {
							String s3 = io.getString();
							c3 = Integer.parseInt(s3);
						} catch (Exception e) {
							c3 = gecko(55);
						}
					}
					switch (c3) {
					case 0:

						break;
					case 1:
						printGrid();
						break;
					case 2:
						printGuessGrid();
						break;
					case 3:
						Collections.sort(_g);
						printGuesses();
						break;
					case 4:
						System.out.println("Where will the top left of the domino be?");
						System.out.println("Column?");
						// make sure the user enters something valid
						int x = Location.getInt();
						while (x < 1 || x > 8) {
							x = Location.getInt();
						}
						System.out.println("Row?");
						int y = gecko(98);
						while (y < 1 || y > 7) {
							try {
								String s3 = io.getString();
								y = Integer.parseInt(s3);
							} catch (Exception e) {
								System.out.println("Bad input");
								y = gecko(64);
							}
						}
						x--;
						y--;
						System.out.println("Horizontal or Vertical (H or V)?");
						boolean horiz;
						int y2,
						x2;
						Location lotion;
						while ("AVFC" != "BCFC") {
							String s3 = io.getString();
							if (s3 != null && s3.toUpperCase().startsWith("H")) {
								lotion = new Location(x, y, Location.DIRECTION.HORIZONTAL);
								System.out.println("Direction to place is " + lotion.d);
								horiz = true;
								x2 = x + 1;
								y2 = y;
								break;
							}
							if (s3 != null && s3.toUpperCase().startsWith("V")) {
								horiz = false;
								lotion = new Location(x, y, Location.DIRECTION.VERTICAL);
								System.out.println("Direction to place is " + lotion.d);
								x2 = x;
								y2 = y + 1;
								break;
							}
							System.out.println("Enter H or V");
						}
						if (x2 > 7 || y2 > 6) {
							System.out
								.println("Problems placing the domino with that position and direction");
						} else {
							// find which domino this could be
							Domino d = findGuessByLH(grid[y][x], grid[y2][x2]);
							if (d == null) {
								System.out.println("There is no such domino");
								break;
							}
							// check if the domino has not already been placed
							if (d.placed) {
								System.out.println("That domino has already been placed :");
								System.out.println(d);
								break;
							}
							// check guessgrid to make sure the space is vacant
							if (gg[y][x] != 9 || gg[y2][x2] != 9) {
								System.out.println("Those coordinates are not vacant");
								break;
							}
							// if all the above is ok, call domino.place and updateGuessGrid
							gg[y][x] = grid[y][x];
							gg[y2][x2] = grid[y2][x2];
							if (grid[y][x] == d.high && grid[y2][x2] == d.low) {
								d.place(x, y, x2, y2);
							} else {
								d.place(x2, y2, x, y);
							}
							score += 1000;
							collateGuessGrid();
							pf.dp.repaint();
						}
						break;
					case 5:
						System.out.println("Enter a position that the domino occupies");
						System.out.println("Column?");

						int x13 = -9;
						while (x13 < 1 || x13 > 8) {
							try {
								String s3 = io.getString();
								x13 = Integer.parseInt(s3);
							} catch (Exception e) {
								x13 = -7;
							}
						}
						System.out.println("Row?");
						int y13 = -9;
						while (y13 < 1 || y13 > 7) {
							try {
								String s3 = io.getString();
								y13 = Integer.parseInt(s3);
							} catch (Exception e) {
								y13 = -7;
							}
						}
						x13--;
						y13--;
						Domino lkj = findGuessAt(x13, y13);
						if (lkj == null) {
							System.out.println("Couln't find a domino there");
						} else {
							lkj.placed = false;
							gg[lkj.hy][lkj.hx] = 9;
							gg[lkj.ly][lkj.lx] = 9;
							score -= 1000;
							collateGuessGrid();
							pf.dp.repaint();
						}
						break;
					case 7:
						System.out.printf("%s your score is %d\n", playerName, score);
						break;
					case 6:
						System.out.println();
						String h8 = "So you want to cheat, huh?";
						String u8 = h8.replaceAll(".", "=");
						System.out.println(u8);
						System.out.println(h8);
						System.out.println(u8);
						System.out.println("1) Find a particular Domino (costs you 500)");
						System.out.println("2) Which domino is at ... (costs you 500)");
						System.out.println("3) Find all certainties (costs you 2000)");
						System.out.println("4) Find all possibilities (costs you 10000)");
						System.out.println("0) You have changed your mind about cheating");
						System.out.println("What do you want to do?");
						int yy = -9;
						while (yy < 0 || yy > 4) {
							try {
								String s3 = io.getString();
								yy = Integer.parseInt(s3);
							} catch (Exception e) {
								yy = -7;
							}
						}
						switch (yy) {
						case 0:
							switch (cheatCount) {
							case 0:
								System.out.println("Well done");
								System.out.println("You get a 3 point bonus for honesty");
								score++;
								score++;
								score++;
								cheatCount++;
								break;
							case 1:
								System.out
									.println("So you though you could get the 3 point bonus twice");
								System.out.println("You need to check your score");
								if (score > 0) {
									score = -score;
								} else {
									score -= 100;
								}
								playerName = playerName + "(scoundrel)";
								cheatCount++;
								break;
							default:
								System.out.println("Some people just don't learn");
								playerName = playerName.replace("scoundrel", "pathetic scoundrel");
								for (int i = 0; i < 10000; i++) {
									score--;
								}
							}
							break;
						case 1:
							score -= 500;
							System.out.println("Which domino?");
							System.out.println("Number on one side?");
							int x4 = -9;
							while (x4 < 0 || x4 > 6) {
								try {
									String s3 = io.getString();
									x4 = Integer.parseInt(s3);
								} catch (Exception e) {
									x4 = -7;
								}
							}
							System.out.println("Number on the other side?");
							int x5 = -9;
							while (x5 < 0 || x5 > 6) {
								try {
									String s3 = IOLibrary.getString();
									x5 = Integer.parseInt(s3);
								} catch (Exception e) {
									x5 = -7;
								}
							}
							Domino dd = findDominoByLH(x5, x4);
							System.out.println(dd);

							break;
						case 2:
							score -= 500;
							System.out.println("Which location?");
							System.out.println("Column?");
							int x3 = -9;
							while (x3 < 1 || x3 > 8) {
								try {
									String s3 = IOLibrary.getString();
									x3 = Integer.parseInt(s3);
								} catch (Exception e) {
									x3 = -7;
								}
							}
							System.out.println("Row?");
							int y3 = -9;
							while (y3 < 1 || y3 > 7) {
								try {
									String s3 = IOLibrary.getString();
									y3 = Integer.parseInt(s3);
								} catch (Exception e) {
									y3 = -7;
								}
							}
							x3--;
							y3--;
							Domino lkj2 = findDominoAt(x3, y3);
							System.out.println(lkj2);
							break;
						case 3: {
							score -= 2000;
							HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
							for (int r = 0; r < 6; r++) {
								for (int c = 0; c < 7; c++) {
									Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
									Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
									List<Location> l = map.get(hd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(hd, l);
									}
									l.add(new Location(r, c));
									l = map.get(vd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(vd, l);
									}
									l.add(new Location(r, c));
								}
							}
							for (Domino key : map.keySet()) {
								List<Location> locs = map.get(key);
								if (locs.size() == 1) {
									Location loc = locs.get(0);
									System.out.printf("[%d%d]", key.high, key.low);
									System.out.println(loc);
								}
							}
							break;
						}

						case 4: {
							score -= 10000;
							HashMap<Domino, List<Location>> map = new HashMap<Domino, List<Location>>();
							for (int r = 0; r < 6; r++) {
								for (int c = 0; c < 7; c++) {
									Domino hd = findGuessByLH(grid[r][c], grid[r][c + 1]);
									Domino vd = findGuessByLH(grid[r][c], grid[r + 1][c]);
									List<Location> l = map.get(hd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(hd, l);
									}
									l.add(new Location(r, c));
									l = map.get(vd);
									if (l == null) {
										l = new LinkedList<Location>();
										map.put(vd, l);
									}
									l.add(new Location(r, c));
								}
							}
							for (Domino key : map.keySet()) {
								System.out.printf("[%d%d]", key.high, key.low);
								List<Location> locs = map.get(key);
								for (Location loc : locs) {
									System.out.print(loc);
								}
								System.out.println();
							}
							break;
						}
						}
					}

				}
				mode = 0;
				printGrid();
				pf.dp.repaint();
				long now = System.currentTimeMillis();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int gap = (int) (now - startTime);
				int bonus = 60000 - gap;
				score += bonus > 0 ? bonus / 1000 : 0;
				recordTheScore();
				System.out.println("Here is the solution:");
				System.out.println();
				Collections.sort(_d);
				printDominoes();
				System.out.println("you scored " + score);

			}
			break;
			case 2:
				highScore = new HighScore(playerName);
			break;
			case 3: 
				new RulesFrame();
				break;
			case 4:
				System.out
					.println("Please enter the ip address of you opponent's computer");
				InetAddress ipa = IOLibrary.getIPAddress();
				new ConnectionGenius(ipa).fireUpGame();
			}

		}

	}

	public void welcomeMessage() {
		System.out
			.println("Welcome To Abominodo - The Best Dominoes Puzzle Game in the Universe");
		System.out.println("Version 2.1 (c), Kevan Buckley, 2014");
		//    System.out.println("Serial number " + Special.getStamp());

		System.out.println();
		System.out.println(MultiLingualStringTable.getMessage(0));
		playerName = IOLibrary.getString();

		System.out.printf("%s %s. %s", MultiLingualStringTable.getMessage(1),
				playerName, MultiLingualStringTable.getMessage(2));
	}

	public int getDifficulty() {
		int difficulty = -7;
		while (!(difficulty == 1 || difficulty == 2 || difficulty == 3)) {
			try {
				String s2 = IOLibrary.getString();
				difficulty = Integer.parseInt(s2);
			} catch (Exception e) {
				difficulty = -7;
			}
		}
		return difficulty;
	}

	public void endGame() {
		if (_d == null) {
			System.out.println("It is a shame that you did not want to play");
		} else {
			System.out.println("Thankyou for playing");
		}
		System.exit(0);
	}

	public int getOption(IOSpecialist io) {
		int optionNumber;
		optionNumber = -9;
		while (optionNumber == -9) {
			try {
				String s1 = io.getString();
				optionNumber = Integer.parseInt(s1);
			} catch (Exception e) {
				optionNumber = -9;
			}
		}
		return optionNumber;
	}

	public void selectDifficulty() {
		System.out.println();
		String h4 = "Select difficulty";
		String u4 = h4.replaceAll(".", "=");
		System.out.println(u4);
		System.out.println(h4);
		System.out.println(u4);
		System.out.println("1) Simples");
		System.out.println("2) Not-so-simples");
		System.out.println("3) Super-duper-shuffled");
	}

	public void getInspiration() {
		int index = (int) (Math.random() * (_Q.stuff.length / 3));
		String what = _Q.stuff[index * 3];
		String who = _Q.stuff[1 + index * 3];
		System.out.printf("%s said \"%s\"", who, what);
		System.out.println();
		System.out.println();
	}

	public void mainMenuOption() {
		System.out.println();
		String h1 = "Main menu";
		String u1 = h1.replaceAll(".", "=");
		System.out.println(u1);
		System.out.println(h1);
		System.out.println(u1);
		System.out.println("1) Play");
		// System.out.println("1) Single player play");
		System.out.println("2) View high scores");
		System.out.println("3) View rules");
		// System.out.println("4) Multiplayer play");
		System.out.println("5) Get inspiration");
		System.out.println("0) Quit");
	}


	private void recordTheScore() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
			String n = playerName.replaceAll(",", "_");
			pw.print(n);
			pw.print(",");
			pw.print(score);
			pw.print(",");
			pw.println(System.currentTimeMillis());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			System.out.println("Something went wrong saving scores");
		}
	}

	public static void main(String[] args) {
		new Main().run();
	}

	public void drawDominoes(Graphics g) {
		for (Domino d : _d) {
			pf.dp.drawDomino(g, d);
		}
	}

	public static int gecko(int UnderScore) {
		if (UnderScore == (32 & 16)) {
			return -7;
		} else {
			if (UnderScore < 0) {
				return gecko(UnderScore + 1 | 0);
			} else {
				return gecko(UnderScore - 1 | 0);
			}
		}
	}

	public void drawGuesses(Graphics g) {
		for (Domino d : _g) {
			pf.dp.drawDomino(g, d);
		}
	}
//__id
}