package Zadanie_3;

import java.util.*;

public class ThreadManager implements Runnable{

	private int maxClients;
	private List<Client> listOfClients = new ArrayList<Client>();
	private List<Client> listOfDeadClients = new ArrayList<Client>();
	private int currentClients = 0;
	private int clientId = 0;
	private Random rand = new Random();
	
	public ThreadManager(int maxClients)
	{
		this.maxClients = maxClients;
	}
	
	@Override
	public void run() {
		if(this.maxClients == 0) return;
		
		while(true)
		{
			if(currentClients < maxClients)
			{
				synchronized(this)
				{
					// Tworzenie nowego watku klienta
					Client newClient = new Client(clientId, 100);
					listOfClients.add(newClient);
					Thread clientThread = new Thread(newClient);
					
					currentClients++;
					clientId++;
					
					System.out.println("Stworzono nowego klienta o id: ["+newClient.GetId()+"]. Nazwa: ["+newClient.GetName()+"]");
					
					clientThread.start();
				}
			}
			else
			{
				try {
					Thread.sleep(rand.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				CheckClients();
			}
		}
	}
	
	public List<Client> GetClientsList()
	{
		return this.listOfClients;
	}
	
	public void CheckClients()
	{
		// indeksy zmar�ych klient�w.
		List<Client> clientsToRemove = new ArrayList<Client>();
		
		// czy lista klient�w jest pusta ?
		if(!listOfClients.isEmpty())
		{
			int i = 0;
			// lecimy po klientach
			for(Client eachClient : listOfClients)
			{
				// jesli klient jest null to cos jest nie tak
				if(eachClient != null)
				{
					//eachClient.ShowVitalFunctions();
					synchronized(this)
					{
						// jesli nie zyje to
						if(!eachClient.GetIsAlive())
						{
							// dekrementujemy 
							currentClients--;
							// dodajemy do list pomocniczej indeksy zmar�ych klient�w
							// nie mo�emy edytowac kolekcji korzystaj�c z niej w p�tli. Trzeba to zrobi� gdzie� na zewn�trz
							clientsToRemove.add(eachClient);
							listOfDeadClients.add(eachClient);
						}	
					}	
				}
				else
				{
					System.out.println("[Error] - CheckClients() in ThreadManager.java. Client is null.");
				}
				i++;
			}	
		}
		
		// jesli nie jest pusta 
		if(!clientsToRemove.isEmpty())
		{
			// usuwamy klient�w z danymi indeksami
			for(Client x : clientsToRemove)
			{
				listOfClients.remove(x);
				System.out.println(x.GetName() + " zginal.. Usuwamy zw�oki...");
			}
		}
	}
}
