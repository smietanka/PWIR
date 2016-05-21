package Zadanie_3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Zadanie_3.Types.Settings;

public class Window extends JFrame {

	public ThreadManager mojThreadManager;
	
	public Window(Settings mySettings) {
		super("Bakery GUI App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(250,50);
		setSize(800, 600);
		//setLocationRelativeTo(null);
		setLayout(new GridLayout(4,1));//FlowLayout());
		
		//JPanel obrazPanel = new Image();
		//add(obrazPanel);

		JLabel backImage = new JLabel();
		backImage.setIcon(new ImageIcon("E:/logo.png"));
		//backImage.setBounds(0, 0, 325, 260);
		backImage.setSize(300, 355);
		add(backImage);
		
		JButton buttonPlay = new JButton("Play!");
		buttonPlay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	JLayeredPane lp = new JLayeredPane();
		        GameWindow gameWindow = new GameWindow(mySettings, lp);
		        		        
		        mojThreadManager = new ThreadManager(mySettings, gameWindow, lp);
				Thread mojWatek = new Thread(mojThreadManager);
				mojWatek.start();
		        
				gameWindow.show();
		        setVisible(false);
		    }
		});
		add(buttonPlay);
		
		JButton buttonSettings = new JButton("Settings");
		buttonSettings.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SettingsWindow setWindow = new SettingsWindow(mySettings);
		        setWindow.show();
		    }
		});
		add(buttonSettings);
		
		JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
		});
		add(buttonExit);
		
		//pack();
		setVisible(true);
	}
}
