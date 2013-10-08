
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
//Michael Dorin
//ICS-462
//


public class Parent extends JFrame implements ActionListener {

	private static JButton button0 = new JButton("Open");
	private static JButton button1 = new JButton("Read");
	private static JButton button2 = new JButton("Dump");
	private static JButton button3 = new JButton("Parse");
	private static JButton button4 = new JButton("Execute");
	private static JButton button5 = new JButton("Exit");

	Vector vector1, vector2;

	FileHandler fileHandler = new FileHandler();

	public void myMain() {
		setTitle("Vector Demo-Parent");
		setLayout(new GridLayout(0,1));
		setSize(400,400);
		setLocationRelativeTo(null);
		add(button0);
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);

		button0.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String args[]) {
		Parent parent = new Parent();
		parent.myMain();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("Open")) {
			fileHandler.select();
		} else if (arg0.getActionCommand().equals("Read")) {
			fileHandler.read();
		}
		else if (arg0.getActionCommand().equals("Dump")) { 
			fileHandler.dump();
		}
		else if (arg0.getActionCommand().equals("Parse")) { 
			ArrayList<String>lines = fileHandler.getLines();
			vector1 = new Vector (lines.get(0));
			vector2 = new Vector (lines.get(1));
			System.out.println(vector1);
			System.out.println(vector2);	

		}
		else if (arg0.getActionCommand().equals("Execute")) { 
			Manager m = new Manager(vector1, vector2);
			m.store();
			m.execute();
			
		} else if (arg0.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
	}
}