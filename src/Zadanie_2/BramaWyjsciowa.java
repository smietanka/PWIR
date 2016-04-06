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
			this.muzeum.wypuscKlienta();
			try
			{
				Thread.sleep(rand.nextInt(600));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
