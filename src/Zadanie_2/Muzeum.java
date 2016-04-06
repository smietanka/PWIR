package Zadanie_2;

import java.util.*;

public class Muzeum {
    int liczbaMiejsc;
    static int zajeteMiejsca;
    Random rand = new Random();
    public Muzeum(int miejsc) {
        this.liczbaMiejsc = miejsc;
    }

    public synchronized int wpuscKlienta(String nazwaBramy, int liczbaKlientow) {
        if (this.liczbaMiejsc - zajeteMiejsca > 0) {
        	zajeteMiejsca++;
            System.out.println("Klient wszedl "+ nazwaBramy +". [" + zajeteMiejsca + "/" + this.liczbaMiejsc + "] miejsc jest zajetych. W kolejce pozostaje: " +  liczbaKlientow);
            liczbaKlientow--;
            try {
                Thread.sleep(rand.nextInt(200));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //System.out.println("Nie ma miejsc");
            
        }
        return liczbaKlientow;
    }

    public void wypuscKlienta() {
    	if(zajeteMiejsca > 0)
    	{
    		zajeteMiejsca--;
            System.out.println("Klient wyszedl. [" + zajeteMiejsca + "/" + this.liczbaMiejsc + "] miejsc jest zajetych.");
    	}
    }
}
