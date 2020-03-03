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
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JFrame;

import project1.*;

public class MainPanel extends JPanel {
	PatientCollection myPats = new PatientCollection();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public MainPanel() {
		setPreferredSize(new Dimension(400, 300));
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		
		JLabel lblPatients = new JLabel("Patient(s)");
		lblPatients.setBounds(26, 36, 56, 14);
		add(lblPatients);
		
		JLabel lblPatientInterface = new JLabel("Patient Interface");
		lblPatientInterface.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		lblPatientInterface.setBounds(153, 11, 114, 14);
		add(lblPatientInterface);
		
		JLabel lblPatientInfo = new JLabel("Patient Info");
		lblPatientInfo.setFont(lblPatientInfo.getFont().deriveFont(lblPatientInfo.getFont().getStyle() | Font.BOLD));
		lblPatientInfo.setBounds(26, 104, 67, 14);
		add(lblPatientInfo);

		JLabel lblPatientStr = new JLabel(myPats.getPatient("1").toString());
		lblPatientStr.setBackground(Color.WHITE);
		lblPatientStr.setBounds(26, 129, 291, 14);
		add(lblPatientStr);

		JComboBox comboBox = new JComboBox(myPats.getIds().toArray());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPatientStr.setText(myPats.getPatient(comboBox.getSelectedItem().toString()).toString());
			}
		});
		comboBox.setBounds(26, 61, 42, 22);
		add(comboBox);
		
		JRadioButton rdbtnCr = new JRadioButton("CR");
		buttonGroup.add(rdbtnCr);
		rdbtnCr.setBackground(Color.LIGHT_GRAY);
		rdbtnCr.setBounds(26, 177, 56, 23);
		add(rdbtnCr);
		
		JRadioButton rdbtnDp = new JRadioButton("DP");
		buttonGroup.add(rdbtnDp);
		rdbtnDp.setBackground(Color.LIGHT_GRAY);
		rdbtnDp.setBounds(26, 203, 56, 23);
		add(rdbtnDp);
		
		JLabel lblErrorLabel = new JLabel("");
		lblErrorLabel.setForeground(Color.RED);
		lblErrorLabel.setBounds(26, 154, 151, 22);
		add(lblErrorLabel);
		
		JButton btnSetResult = new JButton("Set Result");
		btnSetResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnSetResult.setBounds(88, 191, 89, 23);
		add(btnSetResult);
		
		JButton btnShowAllPatients = new JButton("Show All Patients");
		btnShowAllPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Patient List");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				class PatientPanel extends JPanel{
					public PatientPanel() {
						setPreferredSize(new Dimension(375, 280));
						setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
						setBackground(Color.LIGHT_GRAY);
						setLayout(null);
						
						JTextPane textPane = new JTextPane();
						textPane.setText(myPats.toString());
						JScrollPane jsp = new JScrollPane(textPane);
						jsp.setBounds(10, 11, 355, 258);
						frame.getContentPane().add(jsp);
					}
				}
				PatientPanel panel = new PatientPanel();
				frame.getContentPane().add(panel);
				frame.setResizable(false);`
				frame.pack();
				frame.setVisible(true);
			}
		});
		btnShowAllPatients.setBounds(28, 266, 124, 23);
		add(btnShowAllPatients);
		
	}
}
