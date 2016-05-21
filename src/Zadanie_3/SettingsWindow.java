package Zadanie_3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFrame;

import Zadanie_3.Types.Settings;

public class SettingsWindow extends JFrame {

	public SettingsWindow(Settings mySettings) {
		super("Settings");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(6,2));//FlowLayout());
		
		JLabel hp = new JLabel("Health Point: "); add(hp);
		JTextField healthPoints = new JTextField(Integer.toString(mySettings.healthPoints)); add(healthPoints);
		JLabel com = new JLabel("Client on map: "); add(com);
		JTextField clientOnMap = new JTextField(Integer.toString(mySettings.clientOnMap)); add(clientOnMap);
		JLabel tdm = new JLabel("Time Doughnut Make: "); add(tdm);
		JTextField timeDoughnutMake = new JTextField(Integer.toString(mySettings.timeDoughnutMake)); add(timeDoughnutMake);
		JLabel hip = new JLabel("Hungry In Percentage: "); add(hip);
		JTextField hungryInPercentage = new JTextField(Integer.toString(mySettings.hungryInPercentage)); add(hungryInPercentage);
		JLabel hmp = new JLabel("How Many POS: "); add(hmp);
		JTextField howManyPos = new JTextField(Integer.toString(mySettings.howManyPos)); add(howManyPos);
						
		JButton buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	mySettings.healthPoints = Integer.parseInt(healthPoints.getText());
		    	mySettings.clientOnMap = Integer.parseInt(clientOnMap.getText());
		    	mySettings.timeDoughnutMake = Integer.parseInt(timeDoughnutMake.getText());
		    	mySettings.hungryInPercentage = Integer.parseInt(hungryInPercentage.getText());
		    	mySettings.howManyPos = Integer.parseInt(howManyPos.getText());
		        setVisible(false);
		        dispose();
		    }
		});
		add(buttonSave);
				
		pack();
		setVisible(true);
	}
}
