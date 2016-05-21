package Zadanie_3;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Zadanie_3.Types.Bakery;
import Zadanie_3.Types.Settings;

public class ThreadManager implements Runnable{
	Random rand = new Random();
	// Ustawienia
	private Settings mySettings;
	
	// zmienne klientów
	private List<Client> listOfClients = new ArrayList<Client>();
	private List<Client> listOfDeadClients = new ArrayList<Client>();
	private int currentClients = 0;
	private int clientId = 0;
	private Bakery myBakery;
	public Client newClient;
	private GameWindow myWindow;
	private JLayeredPane myPane;
	
	public ThreadManager(Settings Setup, GameWindow Window, JLayeredPane Pane)
	{
		this.mySettings = Setup;
		this.myWindow = Window;
		this.myPane = Pane;
	}
	
	@Override
	public void run() {
		if(mySettings.clientOnMap == 0) return;
			
		// Tworzenie piekarni
		makeBakery();
		
		while(true)
		{
			if(currentClients < mySettings.clientOnMap)
			{
				synchronized(this)
				{
					// Tworzenie nowego watku klienta
					makeNewClient();
				}
				try
				{
					Thread.sleep(1000);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				try {
					Thread.sleep(500);
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
	
	public void makeBakery()
	{
		myBakery = new Bakery(mySettings.timeDoughnutMake, mySettings.howManyPos);
		Thread myThreadBakery = new Thread(myBakery);
		myThreadBakery.start();
	}
	
	public boolean makeNewClient()
	{
		newClient = new Client(clientId, mySettings.healthPoints, myBakery, mySettings.hungryInPercentage, myWindow, myPane);
		listOfClients.add(newClient);
		Thread clientThread = new Thread(newClient);
		
		currentClients++;
		clientId++;
		
		//System.out.println("Mamy nowego klienta na œwiecie z nazw¹: "+newClient.GetName()+" o id: ["+newClient.GetId()+"]");
		
		clientThread.start();
		
		newClient.RandDestinationXY();
		return true;
	}
	
	public void CheckClients()
	{
		// indeksy zmar³ych klientów.
		List<Client> clientsToRemove = new ArrayList<Client>();
		
		// czy lista klientów jest pusta ?
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
						// dodajemy do list pomocniczej indeksy zmar³ych klientów
						// nie mo¿emy edytowac kolekcji korzystaj¹c z niej w pêtli. Trzeba to zrobiæ gdzieœ na zewn¹trz
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
			// usuwamy klientów z danymi indeksami
			for(Client x : clientsToRemove)
			{
				listOfClients.remove(x);
				System.out.println(x.GetName() + " zginal po " + x.GetElapsedTime() + " sekundach... Usuwamy zw³oki...");
			}
		}
	}
}
