package Zadanie_2;

import java.util.*;

public class Muzeum {
    int liczbaMiejsc;
    static int zajeteMiejsca;
    Random rand = new Random();
    public Muzeum(int miejsc) {
        this.liczbaMiejsc = miejsc;
    }

    public synchronized void wpuscKlienta(String nazwaBramy) {
        if (this.liczbaMiejsc - zajeteMiejsca > 0) {
        	zajeteMiejsca++;
            System.out.println("Klient wszedl "+ nazwaBramy +". [" + zajeteMiejsca + "/" + this.liczbaMiejsc + "] miejsc jest zajetych.");
            try {
                Thread.sleep(rand.nextInt(2000));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nie ma miejsc");
            
        }
    }

    public void wypuscKlienta() {
    	if(zajeteMiejsca > 0)
    	{
    		zajeteMiejsca--;
            System.out.println("Klient wyszedl. [" + zajeteMiejsca + "/" + this.liczbaMiejsc + "] miejsc jest zajetych.");
    	}
    }
}
