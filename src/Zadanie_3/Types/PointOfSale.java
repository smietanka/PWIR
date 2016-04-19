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
	public String name;
	public Client CurrentClient;
	
	public PointOfSale(int howManyDoo, int timeSelling, String name)
	{
		this.HowManyDoughnuts = howManyDoo;
		this.timeSelling = timeSelling;
		this.name = name;
	}
	
	public void PutClientToQueue(Client client)
	{
		ClientsQueue.add(client);
	}
	
	public void LoadDoughtnuts(int howMany)
	{
		this.HowManyDoughnuts = this.HowManyDoughnuts + howMany;
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
		this.HowManyDoughnuts--;
		//client = ClientsQueue.get(0);
		client.ChangeStatus(Status.Walking);
		client.AddHP();
		//ClientsQueue.remove(0);
		
	}

	@Override
	public void run() {
		while(true)
		{
			if(!ClientsQueue.isEmpty())
			{
				try
				{
					CurrentClient = ClientsQueue.get(1);
					CurrentClient.ChangeStatus(Status.Buying);
					//SellDoughnuts(CurrentClient);
					//Trwa sprzeda¿ p¹czka
					//Thread.sleep(200);//this.timeSelling);
					CurrentClient.AddHP();
					CurrentClient.ChangeStatus(Status.Walking);
					ClientsQueue.remove(1);
					System.out.println(this.name+" kupuje paczka. Pozostalo: "+this.GetHowManyDoughnuts());
					System.out.println("Dlugosc kolejki:"+this.HowManyClients());
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
		}
	}
	
}
