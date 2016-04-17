package Zadanie_3.Types;

import java.util.Random;

public class Bakery implements Runnable {
	// chuj kurwa dupa cipa
	Random rand = new Random();
	//Zak³adamy, ¿e w ka¿dej kasie jest tyle samo p¹czków na starcie, randomowy czas sprzeda¿y p¹czka
	private PointOfSale Pos1 = new PointOfSale(10, rand.nextInt(1000));
	private PointOfSale Pos2 = new PointOfSale(10, rand.nextInt(1000));
	private int timeDoughtnutMake;
	
	public Bakery(int timeDoughtnutMake)
	{
		this.timeDoughtnutMake = timeDoughtnutMake;
	}
	@Override
	public void run() {
		
		while(true)
		{
			// Sprawdzamy ktory punkt sprzedazy potrzebuje zaopatrzenia paczkow i ladujemy paczkami
			PointOfSale sale = WhereIsLessDoughnut();
			if(sale != null)
			{
				sale.LoadDoughtnuts(1);
			}
			
			// usypiamy watek
			try
			{
				//Czas jaki piekarnia potrzebuje na produkcje paczka
				Thread.sleep(this.timeDoughtnutMake*1000);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/*Sprawdza w której kasie stan p¹czków jest < 2
	public PointOfSale DoughnutLoader()
	{
		PointOfSale result = null;
		if(Pos1.GetHowManyDoughnuts() < 2)
		{
			result = Pos1;
		}
		if(Pos2.GetHowManyDoughnuts() < 2)
		{
			result = Pos2;
		}
		
		return result;
	}
	*/
	public PointOfSale WhereIsLessClients()
	{
		PointOfSale result = null;
		if(Pos1.HowManyClients() < Pos2.HowManyClients())
		{
			result = Pos1;
		}
		else
		{
			result = Pos2;
		}
		return result;
	}
	
	public PointOfSale WhereIsLessDoughnut()
	{
		PointOfSale result = null;
		if(Pos1.GetHowManyDoughnuts() > Pos2.GetHowManyDoughnuts())
		{
			result = Pos2;
		}
		else 
		{
			result = Pos1;
		}
		return result;
	}
	
	public void SellDoughnut()
	{
		
	}
}
