package Zadanie_2;

import java.util.Random;

public class BramaWejsciowa implements Runnable{
    private Muzeum muzeum;
    String nazwaWatku;
    Random rand = new Random();
    
    public BramaWejsciowa(Muzeum mojeMuzeum, String nazwaWatku) {
        this.muzeum = mojeMuzeum;
        this.nazwaWatku = nazwaWatku;
    }
    
	@Override
	public void run() {
		while(true)
		{
			if(this.muzeum.liczbaKlientow<=0)
			{
				break;
			}
			else
			{
				this.muzeum.wpuscKlienta(nazwaWatku);
	            try {
	                Thread.sleep(rand.nextInt(200));
	            }
	            catch (Exception e) {
	                e.printStackTrace();
	            }
			}
				
		}
	}
}