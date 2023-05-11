package base;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class RulesFrame extends JFrame {

	public RulesFrame() {
		super("Rules by __student");
		setSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		String h4 = "Rules";
		String u4 = h4.replaceAll(".", "=");
		System.out.println(u4);
		System.out.println(h4);
		System.out.println(u4);
		JEditorPane w;
		try {
			w = new JEditorPane("http://www.scit.wlv.ac.uk/~in6659/abominodo/");

		} catch (Exception e) {
			w = new JEditorPane("text/plain", "Problems retrieving the rules from the Internet");
		}
		setContentPane(new JScrollPane(w));
		setVisible(true);
	}
	
}