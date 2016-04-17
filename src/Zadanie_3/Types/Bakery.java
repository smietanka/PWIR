//1.Produkcja 1 p�czka zajmuje x czasu okre�lonego w Settings
//2.Wyprodukowany p�czek trafia do kasy gdzie jest mniej p�czk�w

package Zadanie_3.Types;

public class Bakery implements Runnable {
	
	//Zak�adamy, �e w ka�dej kasie jest tyle samo p�czk�w na starcie
	private PointOfSale Pos1 = new PointOfSale(10);
	private PointOfSale Pos2 = new PointOfSale(10);
	
	@Override
	public void run() {
		
		while(true)
		{
			// Sprawdzamy ktory punkt sprzedazy potrzebuje zaopatrzenia paczkow i ladujemy paczkami
			PointOfSale sale = DoughnutLoader();
			if(sale != null)
			{
				sale.LoadDoughtnuts(15);
			}
			
			// usypiamy watek
			try
			{
				Thread.sleep(2000);
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
