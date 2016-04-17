package Zadanie_3;

import java.util.*;

import Zadanie_3.Types.Bakery;
import Zadanie_3.Types.Settings;

public class ThreadManager implements Runnable{
	// Ustawienia
	private Settings mySettings;
	
	// zmienne klient�w
	private List<Client> listOfClients = new ArrayList<Client>();
	private List<Client> listOfDeadClients = new ArrayList<Client>();
	private int currentClients = 0;
	private int clientId = 0;
	
	// zmienne cukierni
	
	public ThreadManager(Settings mySetup)
	{
		this.mySettings = mySetup;
	}
	
	@Override
	public void run() {
		if(mySettings.clientOnMap == 0) return;
		
		// Tworzenie piekarni
		Bakery myBakery = new Bakery(mySettings.timeDoughnutMake);
		Thread myThreadBakery = new Thread(myBakery);
		myThreadBakery.start();
		
		while(true)
		{
			if(currentClients < mySettings.clientOnMap)
			{
				synchronized(this)
				{
					// Tworzenie nowego watku klienta
					Client newClient = new Client(clientId, mySettings.healthPoints, myBakery);
					listOfClients.add(newClient);
					Thread clientThread = new Thread(newClient);
					
					currentClients++;
					clientId++;
					
					System.out.println("Mamy nowego klienta na �wiecie z nazw�: "+newClient.GetName()+" o id: ["+newClient.GetId()+"]");
					
					clientThread.start();
				}
			}
			else
			{
				try {
					Thread.sleep(1500);
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
			// lecimy po klientach
			for(Client eachClient : listOfClients)
			{
				// jesli klient jest null to cos jest nie tak
				if(eachClient != null)
				{
					eachClient.ShowVitalFunctions();
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
				else
				{
					System.out.println("[Error] - CheckClients() in ThreadManager.java. Client is null.");
				}
			}	
		}
		
		// jesli nie jest pusta 
		if(!clientsToRemove.isEmpty())
		{
			// usuwamy klient�w z danymi indeksami
			for(Client x : clientsToRemove)
			{
				listOfClients.remove(x);
				System.out.println(x.GetName() + " zginal po " + x.GetElapsedTime() + " sekundach... Usuwamy zw�oki...");
			}
		}
	}
}
