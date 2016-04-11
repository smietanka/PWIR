package Zadanie_3;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		ThreadManager mojThreadManager = new ThreadManager(2);
		
		Thread mojWatek = new Thread(mojThreadManager);
		mojWatek.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Client> dupa = mojThreadManager.getClientsList();
		
		while(true)
		{
			if(!dupa.isEmpty())
			{
				for(Client x : dupa)
				{
					x.ShowVitalFunctions();
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
