/* Taylor Boling, CSCI 3381 Project 2
 * This project serves to be the continuation of Project one, and to teach the implementation of GUIs
 */

package project2pkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;


import project1.*;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class MainPanel extends JPanel {
	PatientCollection myPats = new PatientCollection("./data.csv");
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public MainPanel() { //organizes the panel for the usage as well as implements functionality
		setPreferredSize(new Dimension(370, 300)); // implements the base panel characteristics
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		//for convenience of reading, all labels are placed together
		JLabel lblPatients = new JLabel("Patient:");
		lblPatients.setBounds(26, 96, 56, 14);
		add(lblPatients);
		
		JLabel lblPatientInterface = new JLabel("Patient Interface");
		lblPatientInterface.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblPatientInterface.setBounds(121, 25, 114, 14);
		add(lblPatientInterface);
		
		JLabel lblPatientInfo = new JLabel("Patient Info");
		lblPatientInfo.setFont(lblPatientInfo.getFont().deriveFont(lblPatientInfo.getFont().getStyle() | Font.BOLD));
		lblPatientInfo.setBounds(26, 46, 67, 14);
		add(lblPatientInfo);

		JLabel lblPatientStr = new JLabel(myPats.getPatient("1").toString());
		lblPatientStr.setBackground(Color.WHITE);
		lblPatientStr.setBounds(26, 71, 334, 14);
		add(lblPatientStr);

		JLabel lblErrorLabel = new JLabel("");
		lblErrorLabel.setForeground(Color.RED);
		lblErrorLabel.setBounds(26, 121, 151, 22);
		add(lblErrorLabel);
		
		JLabel lblImg = new JLabel("img");
		lblImg.setIcon(new ImageIcon("./syringe.png"));
		lblImg.setBounds(203, 109, 128, 128);
		add(lblImg);
		
		JComboBox comboBox = new JComboBox(myPats.getIds().toArray());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //changes a label based on the combobox's selected item
				lblPatientStr.setText(myPats.getPatient(comboBox.getSelectedItem().toString()).toString());
			}
		});
		comboBox.setBounds(78, 92, 42, 22);
		add(comboBox);
		
		JRadioButton rdbtnCr = new JRadioButton("CR");
		buttonGroup.add(rdbtnCr);
		rdbtnCr.setBackground(Color.LIGHT_GRAY);
		rdbtnCr.setBounds(26, 150, 56, 23);
		add(rdbtnCr);
		
		JRadioButton rdbtnDp = new JRadioButton("DP");
		buttonGroup.add(rdbtnDp);
		rdbtnDp.setBackground(Color.LIGHT_GRAY);
		rdbtnDp.setBounds(26, 176, 56, 23);
		add(rdbtnDp);
		
		JButton btnSetResult = new JButton("Set Result");
		btnSetResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// sets patient result based on the radio button selected
				if (buttonGroup.isSelected(rdbtnCr.getModel())) {
					myPats.getPatient(comboBox.getSelectedItem().toString()).setResult("CR");
					lblPatientStr.setText(myPats.getPatient(comboBox.getSelectedItem().toString()).toString());
					buttonGroup.clearSelection();
					lblErrorLabel.setText("");
				}
				else if(buttonGroup.isSelected(rdbtnDp.getModel())) {
					myPats.getPatient(comboBox.getSelectedItem().toString()).setResult("DP");
					lblPatientStr.setText(myPats.getPatient(comboBox.getSelectedItem().toString()).toString());
					buttonGroup.clearSelection();
					lblErrorLabel.setText("");

				}
				else {
					lblErrorLabel.setText("Please Select an option to set result");
				}
			}
		});
		btnSetResult.setBounds(88, 165, 99, 23);
		add(btnSetResult);
		
		JButton btnShowAllPatients = new JButton("Show All Patients");
		btnShowAllPatients.addActionListener(new ActionListener() { //creates a new frame and panel to display all patients
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Patient List");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //gets rid of the window on close, rather than exiting the program
				
				class PatientPanel extends JPanel{
					public PatientPanel() {
						setPreferredSize(new Dimension(390, 280));
						setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
						setBackground(Color.LIGHT_GRAY);
						setLayout(null);
						
						JTextPane textPane = new JTextPane();
						textPane.setText(myPats.toString());
						// in retrospect, it would've probably been easier to just do a textArea, and it would have given me points on the rubric.
						// but oh well! live and learn?
						JScrollPane jsp = new JScrollPane(textPane); 
						jsp.setBounds(10, 11, 370, 258);
						frame.getContentPane().add(jsp);
					}
				}
				PatientPanel panel = new PatientPanel();
				frame.getContentPane().add(panel);
				frame.setResizable(false);
				frame.pack();
				frame.setVisible(true);
			}
		});
		btnShowAllPatients.setBounds(28, 266, 149, 23);
		add(btnShowAllPatients);
		
		JButton btnRemovePatient = new JButton("Remove Patient");
		btnRemovePatient.addActionListener(new ActionListener() { // removes patient based on the combo box's selected item
			public void actionPerformed(ActionEvent e) {
				String pat = new String(comboBox.getSelectedItem().toString());
				if (JOptionPane.showOptionDialog(null, "Are you sure you want to delete patient " 
						+ comboBox.getSelectedItem().toString() +"?", "Confirm", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION) {
					myPats.removePatient(pat);
				}
			}
		});
		btnRemovePatient.setBounds(190, 266, 149, 23);
		add(btnRemovePatient);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 370, 22);
		add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmImportPatients = new JMenuItem("Import Patients");
		mntmImportPatients.addMouseListener(new MouseAdapter() { // implements importing patients to the patient collection
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser jfc = new JFileChooser("./");
				int rv = jfc.showOpenDialog(null);
				if(rv == JFileChooser.APPROVE_OPTION) {
					String SelectedFile = jfc.getSelectedFile().toString();
					myPats.addPatientsFromFile(SelectedFile);
				}
				comboBox.setModel(new DefaultComboBoxModel(myPats.getIds().toArray()));
			}
		});
		mnFile.add(mntmImportPatients);
		
		JMenuItem mntmSave = new JMenuItem(" Save Patient File");
		mntmSave.addMouseListener(new MouseAdapter() { //writes to file without closing
			@Override
			public void mousePressed(MouseEvent e) {
				myPats.doWrite("./data.csv");
			}
		});
		mnFile.add(mntmSave);
		
		JMenu menu = new JMenu("( \u0361\u00B0 \u035C\u0296 \u0361\u00B0)"); //Those are some nice character codes!
		menuBar.add(menu);
		
		JMenuItem mntmPAR = new JMenuItem("P A R T Y  M O D E");
		mntmPAR.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // Implements the best layout for using this program
				setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.GREEN, Color.CYAN));
				setBackground(Color.MAGENTA);
				lblErrorLabel.setForeground(Color.BLUE);
				lblPatients.setForeground(Color.GREEN);
				lblPatientInterface.setForeground(Color.GREEN);
				lblPatientStr.setForeground(Color.GREEN);
				lblPatientInfo.setForeground(Color.GREEN);
				lblImg.setIcon(new ImageIcon("./party.gif"));
				rdbtnCr.setBackground(Color.MAGENTA);
				rdbtnDp.setBackground(Color.MAGENTA);


			}
		});
		menu.add(mntmPAR);
		
		JMenuItem mntmBoringModeVv = new JMenuItem("Boring mode v_v");
		mntmBoringModeVv.addMouseListener(new MouseAdapter() { // Implements the "appropriate" original layout for this project
			@Override
			public void mousePressed(MouseEvent e) {
				setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				setBackground(Color.LIGHT_GRAY);
				lblErrorLabel.setForeground(Color.BLACK);
				lblPatients.setForeground(Color.BLACK);
				lblPatientInterface.setForeground(Color.BLACK);
				lblPatientStr.setForeground(Color.BLACK);
				lblPatientInfo.setForeground(Color.BLACK);
				lblImg.setIcon(new ImageIcon("./syringe.png"));
				rdbtnCr.setBackground(Color.LIGHT_GRAY);
				rdbtnDp.setBackground(Color.LIGHT_GRAY);

			}
		});
		menu.add(mntmBoringModeVv);
		
		
	}
	
	public void doClose() {
		myPats.doWrite("./data.csv");
	}
}
