package Zadanie_2;

public class Muzeum {
    int liczbaMiejsc;
    int liczbaKlientow;
    static int zajeteMiejsca;
    
    /**
     * Tworzy muzeum o dwoch parametrach.
     * @param miejsc Ilosc miejsc w muzeum
     * @param liczbaKlientow Liczba klientow ktorzy chca wejsc do muzeum.
     */
    public Muzeum(int miejsc, int liczbaKlientow) {
        this.liczbaMiejsc = miejsc;
        this.liczbaKlientow = liczbaKlientow;
    }

    public synchronized void wpuscKlienta(String nazwaBramy) {
        
    	synchronized(this)
    	{
    		if (this.liczbaMiejsc - zajeteMiejsca > 0) 
    		{
    			if(liczbaKlientow == 0) return;
	    		zajeteMiejsca++;
	    		liczbaKlientow--;
	            System.out.println("Klient wszedl "+ nazwaBramy +". [" + zajeteMiejsca + "/" + this.liczbaMiejsc + "] miejsc jest zajetych. W kolejce pozostaje: " +  liczbaKlientow);
    		}
    	}
    }

    public void wypuscKlienta() {
    	
		synchronized(this)
		{
			if(zajeteMiejsca > 0)
	    	{
				zajeteMiejsca--;
				System.out.println("Klient wyszedl. [" + zajeteMiejsca + "/" + this.liczbaMiejsc + "] miejsc jest zajetych.");
	    	}
    	}
    }
}
