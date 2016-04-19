package Zadanie_3.Types;

import java.util.*;
import Zadanie_3.Client;
import Zadanie_3.Status;

public class PointOfSale implements Runnable{
	// kolejka w danym punkcie sprzeda¿y
	private List<Client> ClientsQueue = new ArrayList<Client>();
	// iloœæ p¹czkow w danym punkcie sprzeda¿y
	private int HowManyDoughnuts;
	// czas sprzeda¿y p¹czka
	private int timeSelling;
	public String Name;
	public Client CurrentClient;
	
	public PointOfSale(int howManyDoo, int timeSelling, String name)
	{
		this.HowManyDoughnuts = howManyDoo;
		this.timeSelling = timeSelling;
		this.Name = name;
		System.out.println("Stworzono "+name);
	}
	
	public void PutClientToQueue(Client client)
	{
		ClientsQueue.add(client);
	}
	
	public void LoadDoughtnuts(int howMany)
	{
		this.HowManyDoughnuts = this.HowManyDoughnuts + howMany;
		System.out.println("Dodalo "+howMany+" paczkow do "+ this.Name);
	}
	
	public int GetHowManyDoughnuts()
	{
		return this.HowManyDoughnuts;
	}
	
	public int HowManyClients()
	{
		return ClientsQueue.size();
	}
	
	public int GetTimeSelling()
	{
		return timeSelling;
	}
	
	public void SellDoughnuts(Client client)
	{
		synchronized(this)
		{
			this.HowManyDoughnuts--;
			client.ChangeStatus(Status.Walking);
			client.AddHP();
			ClientsQueue.remove(0);
		}
	}

	@Override
	public void run() {
		while(true)
		{
			if(!ClientsQueue.isEmpty())
			{
				try
				{
					if(this.HowManyDoughnuts > 0)
					{
						CurrentClient = ClientsQueue.get(0);
						CurrentClient.ChangeStatus(Status.Buying);
						//Trwa sprzeda¿ p¹czka
						Thread.sleep(this.timeSelling);
						SellDoughnuts(CurrentClient);
						System.out.println(CurrentClient.GetName() +" kupuje paczka. Pozostalo: "+this.GetHowManyDoughnuts());
						System.out.println("Dlugosc kolejki:"+this.HowManyClients());					
					}
					else
					{
						System.out.println(this.Name + " nie ma juz paczkow. Zapraszamy kiedy indziej.");
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}		
			}
			else
			{
				//System.out.println("Nie ma ¿adnego klienta");				
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
	}
	
}
