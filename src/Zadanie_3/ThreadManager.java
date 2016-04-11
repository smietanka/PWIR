package Zadanie_3;

import java.util.*;

public class ThreadManager implements Runnable{

	private int maxClients;
	private List<Client> listOfClients = new ArrayList<Client>();
	public int currentClients = 0;
	
	private Random rand = new Random();
	public ThreadManager(int maxClients)
	{
		this.maxClients = maxClients;
	}
	
	public List<Client> getClientsList()
	{
		return this.listOfClients;
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
					listOfClients.add(newClient);
					Thread clientThread = new Thread(newClient);
					
					currentClients++;
					System.out.println("Stworzono nowego klienta o id: ["+newClient.getId()+"]");
					clientThread.start();
				}	
			}
			else
			{
				try {
					Thread.sleep(rand.nextInt(500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		}
		
	}

}
