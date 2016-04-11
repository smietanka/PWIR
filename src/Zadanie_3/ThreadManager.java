package Zadanie_3;

import java.util.Random;

public class ThreadManager implements Runnable{

	private int maxClients;
	public int currentClients = 0;
	
	private Random rand = new Random();
	public ThreadManager(int maxClients)
	{
		this.maxClients = maxClients;
	}
	
	@Override
	public void run() {
		while(true)
		{
			if(currentClients < maxClients)
			{
				synchronized(this)
				{
					// Tworzenie nowego watku klienta
					Client newClient = new Client(currentClients);
					new Thread(newClient).start();
					currentClients++;
					System.out.println("Stworzono nowego klienta o id: ["+newClient.getId()+"]");
				}	
			}
			
			try {
				Thread.sleep(rand.nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
