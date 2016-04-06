package Zadanie_2;

public class BramaWejsciowa implements Runnable{
    private Muzeum muzeum;
    String nazwaWatku;
    int liczbaKlientow;
    public BramaWejsciowa(Muzeum mojeMuzeum, String nazwaWatku, int liczbaKlientow) {
        this.muzeum = mojeMuzeum;
        this.nazwaWatku = nazwaWatku;
        this.liczbaKlientow = liczbaKlientow;
    }
	@Override
	public void run() {
		while(true)
		{
			liczbaKlientow = this.muzeum.wpuscKlienta(nazwaWatku,liczbaKlientow);
			if(liczbaKlientow<=0)
				break;
		}
	}
}