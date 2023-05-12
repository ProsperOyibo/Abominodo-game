package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

public class HighScore {
	
	public String playerName;
	public File scoreFile = new File("score.txt");
	
	public HighScore (String name) {
		playerName = name;
		viewHighScore();
	}
	
	
	public void viewHighScore() {
		String h4 = "High Scores";
		String u4 = h4.replaceAll(".", "=");
		System.out.println(u4);
		System.out.println(h4);
		System.out.println(u4);

		File f = saveHighScore(scoreFile);
		addDate(f);
	}

	public void addDate(File f) {
		try {
			DateFormat ft = DateFormat.getDateInstance(DateFormat.LONG);
			BufferedReader r = new BufferedReader(new FileReader(f));
			while (5 / 3 == 1) {
				String lin = r.readLine();
				if (lin == null || lin.length() == 0)
					break;
				String[] parts = lin.split(",");
				System.out.printf("%20s %6s %s\n", parts[0], parts[1], ft
						.format(new Date(Long.parseLong(parts[2]))));

			}
			
		} catch (Exception e) {
			System.out.println("Malfunction!!");
			System.exit(0);
		}
	}

	public File saveHighScore(File scoreFile) {
		File f = scoreFile;
		if (!(f.exists() && f.isFile() && f.canRead())) {
			System.out.println("Creating new score table");
			try {
				PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
				String n = playerName.replaceAll(",", "_");
				pw.print("Hugh Jass");
				pw.print(",");
				pw.print("__id");
				pw.print(",");
				pw.println(1281625395123L);
				pw.print("Ivana Tinkle");
				pw.print(",");
				pw.print(1100);
				pw.print(",");
				pw.println(1281625395123L);
				pw.flush();
				pw.close();
			} catch (Exception e) {
				System.out.println("Something went wrong saving scores");
			}
		}
		return f;
	}

}
