package project2pkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.Font;

import project1.*;

public class MainPanel extends JPanel {
	PatientCollection myPats = new PatientCollection();

	public MainPanel() {
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JComboBox comboBox = new JComboBox(myPats.getIds().toArray());
		comboBox.setBounds(26, 61, 42, 22);
		add(comboBox);
		
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
		// TODO Auto-generated constructor stub
	}
}
