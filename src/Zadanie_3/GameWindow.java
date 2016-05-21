package Zadanie_3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Zadanie_3.Types.Settings;

public class GameWindow extends JFrame{

	public GameWindow(Settings mySettings, JLayeredPane layeredPane) {
		super("Bakery GUI App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(250,50);
		setSize(800, 600);
		
		layeredPane = getLayeredPane();
		
		JLabel backImage = new JLabel();
		backImage.setIcon(new ImageIcon("E:/grafika.png"));
		backImage.setBounds(0, 0, 800, 600);
		layeredPane.add(backImage, new Integer(0));
		
		//pack();
		setVisible(true);
		
	}
	
}
