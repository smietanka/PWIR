package Zadanie_3.Types;

import java.util.*;

public class Bakery implements Runnable {
	
	private int timeDoughtnutMake;
	private int howManyPos;
	
	private List<PointOfSale> myPosList = new ArrayList<PointOfSale>();
	
	public Bakery(int timeDoughtnutMake, int howManyPos)
	{
		this.timeDoughtnutMake = timeDoughtnutMake;
		this.howManyPos = howManyPos;
	}
	@Override
	public void run() {
		
		for(int i = 0;i<howManyPos;i++)
		{
			PointOfSale currentPos = new PointOfSale(5,2000,"Kasa"+i);
			Thread myThreadPos = new Thread(currentPos);
			myThreadPos.start();
			myPosList.add(currentPos);
		}
		
		while(true)
		{
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

	public PointOfSale WhereIsLessClients()
	{
		PointOfSale result = myPosList.get(0);
		
		int whereIsLess = myPosList.get(0).HowManyClients();
		for(PointOfSale x : this.myPosList)
		{
			if(x.HowManyClients() < whereIsLess)
			{
				whereIsLess = x.HowManyClients();
				result = x;
			}
		}
		return result;
	}

	public PointOfSale WhereIsLessDoughnut()
	{
		PointOfSale result = myPosList.get(0);
		int whereIsLess = myPosList.get(0).GetHowManyDoughnuts();
		
		for(PointOfSale x : this.myPosList)
		{
			if(x.GetHowManyDoughnuts() < whereIsLess)
			{
				whereIsLess = x.GetHowManyDoughnuts();
				result = x;
			}
		}
		return result;
	}
}
