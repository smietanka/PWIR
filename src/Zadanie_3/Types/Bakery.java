package Zadanie_3.Types;

public class Bakery implements Runnable {
	
	private int timeDoughtnutMake;
	PointOfSale Pos1,Pos2;
	
	public Bakery(int timeDoughtnutMake)
	{
		this.timeDoughtnutMake = timeDoughtnutMake;

	}
	@Override
	public void run() {
		Pos1 = new PointOfSale(10,2000,"Kasa 1");
		Pos2 = new PointOfSale(10,4000,"Kasa 2");
		Thread myThreadPos1 = new Thread(Pos1);
		myThreadPos1.start();
		Thread myThreadPos2 = new Thread(Pos2);
		myThreadPos2.start();
		while(true)
		{
			PointOfSale sale = WhereIsLessDoughnut();
			if(sale != null)
			{
				sale.LoadDoughtnuts(1);

				//System.out.println("Kasa numer "+sale.name+" otrzyma³a 1 paczka!");
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

	public PointOfSale WhereIsLessClients()
	{
		PointOfSale result = null;
		if(this.Pos1.HowManyClients() < this.Pos2.HowManyClients())
		{
			result = this.Pos1;
		}
		else
		{
			result = this.Pos2;
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
