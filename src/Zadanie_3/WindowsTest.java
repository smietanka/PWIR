package Zadanie_3;

import java.util.*;

import javax.swing.*;

public class WindowsTest implements Runnable {
	
	private ThreadManager myThreadManager;
	
	public WindowsTest(ThreadManager blabla)
	{
		this.myThreadManager = blabla;
	}

	@Override
	public void run() {
		JFrame window = new JFrame("blabla");
		window.setVisible(true);
		window.setSize(500, 500);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		window.add(panel);
		

		while(true)
		{
			List<Client> chujCiWDupe = myThreadManager.GetClientsList();		
			int i = 5;
			for(Client x : chujCiWDupe)
			{
				JLabel bll = new JLabel();
				bll.setText(x.GetName());
				bll.setBounds(i+20, i+20, 120, 20);
				panel.add(bll);
			}
			
			try
			{
				Thread.sleep(10000);	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}
}