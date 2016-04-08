package Zadanie_2;

import java.util.Random;

public class BramaWyjsciowa implements Runnable{
	private Muzeum muzeum;
	Random rand = new Random();
	
	public BramaWyjsciowa(Muzeum mojeMuzeum)
	{
		this.muzeum = mojeMuzeum;
	}
	@Override
	public void run() {
		while(true)
		{
			if(this.muzeum.liczbaKlientow <= 0 && Muzeum.zajeteMiejsca <= 0) 
			{
				System.out.println("Wyszedl ostatni klient. Zamykamy muzeum :)");
				break;
			}
			else
			{
				this.muzeum.wypuscKlienta();
				try
				{
					Thread.sleep(rand.nextInt(500));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}	
			}
		}
	}

}
