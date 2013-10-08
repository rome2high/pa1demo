// Michael Dorin
// ICS-462
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class FileHandler extends JPanel {

	private File file;
	private ArrayList<String> lines = new ArrayList<String>();
	JFileChooser chooser = new JFileChooser();


	public void select() {
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Example");
		chooser.showOpenDialog(this);
		file = chooser.getSelectedFile();
	}

	public void read() {
		if (file == null)  {
			JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green.");
			return;
		}
		try {
			Scanner input = new Scanner(file);
			while(input.hasNext()) {
				String line = input.nextLine();
				lines.add(line);
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green.");
		}
	}

	public void dump() {
		for (int i=0;i<lines.size();i++)
			System.out.println(lines.get(i));
	}

	public ArrayList<String> getLines() {
		return lines;
	}

}
