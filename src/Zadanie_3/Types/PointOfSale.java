package Zadanie_3.Types;

import java.util.*;
import Zadanie_3.Client;

public class PointOfSale {
	// kolejka w danym punkcie sprzeda¿y
	private List<Client> ClientsQueue = new ArrayList<Client>();
	// iloœæ p¹czkow w danym punkcie sprzeda¿y
	private int HowManyDoughnuts;
	// czas sprzeda¿y p¹czka
	private int timeSelling;
	
	public PointOfSale(int howManyDoo, int timeSelling)
	{
		this.HowManyDoughnuts = howManyDoo;
		this.timeSelling = timeSelling;
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
}
