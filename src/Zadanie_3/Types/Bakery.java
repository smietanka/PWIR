package Zadanie_3.Types;

public class Bakery implements Runnable {
	
	private PointOfSale Pos1 = new PointOfSale(15);
	private PointOfSale Pos2 = new PointOfSale(20);
	
	private int _howManyDoughnuts;
	private int _timeToCreateDoughnuts;
	
	public Bakery(int howManyDoughnut, int timeToCreateDoughnut)
	{
		this._howManyDoughnuts = howManyDoughnut;
		this._timeToCreateDoughnuts = timeToCreateDoughnut;
	}
	@Override
	public void run() {
		
		while(true)
		{
			// Sprawdzamy ktory punkt sprzedazy potrzebuje zaopatrzenia paczkow i ladujemy paczkami
			PointOfSale sale = DoughnutLoader();
			if(sale != null)
			{
				sale.LoadDoughtnuts(_howManyDoughnuts);
			}
			
			// usypiamy watek
			try
			{
				Thread.sleep((_timeToCreateDoughnuts*1000) * _howManyDoughnuts);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
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
	
	public void SellDoughnut()
	{
		
	}
}
