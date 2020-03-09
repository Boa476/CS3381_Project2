/* Taylor Boling, CSCI 3381 Project 2
 * This project serves to be the continuation of Project one, and to teach the implementation of GUIs
*/

package project2pkg;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class MainFrame {

	public MainFrame() { //creates a Jframe and adds a JPanel to it
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Doctor Stuff");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MainPanel panel = new MainPanel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				panel.doClose();
			}
		});
	}

}
