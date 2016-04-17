//1.Randomowy czas sprzeda�y dla ka�dej kasy

package Zadanie_3.Types;

import java.util.*;
import Zadanie_3.Client;

public class PointOfSale {
	// kolejka w danym punkcie sprzeda�y
	private List<Client> ClientsQueue = new ArrayList<Client>();
	// ilo�� p�czkow w danym punkcie sprzeda�y
	private int HowManyDoughnuts;
	
	public PointOfSale(int howManyDoo)
	{
		this.HowManyDoughnuts = howManyDoo;
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
}
