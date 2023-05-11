package base;

public class DisplayMenu {
	
	public DisplayMenu() {
		
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
	
	public void playMenu(String playerName) {
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
	
	public void cheatMenu() {
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
	}
	
	

}