package Zadanie_3.Types;

public class Bakery implements Runnable {
	
	private int timeDoughtnutMake;
	private PointOfSale Pos1,Pos2;
	
	public Bakery(int timeDoughtnutMake, PointOfSale pos1, PointOfSale pos2)
	{
		this.timeDoughtnutMake = timeDoughtnutMake;
		this.Pos1 = pos1;
		this.Pos2 = pos2;
	}
	@Override
	public void run() {
		
		while(true)
		{
			
			PointOfSale sale = WhereIsLessDoughnut();
			if(sale != null)
			{
				sale.LoadDoughtnuts(1);
				System.out.println("---------------------------");
				System.out.println("Kasa numer "+sale.name+" otrzyma³a 1 paczka!");
				System.out.println("---------------------------");
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
}
