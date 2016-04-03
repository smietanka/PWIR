package Zadanie_2;

public class BramaWejsciowa implements Runnable{
    private Muzeum muzeum;
    String nazwaWatku;
    public BramaWejsciowa(Muzeum mojeMuzeum, String nazwaWatku) {
        this.muzeum = mojeMuzeum;
        this.nazwaWatku = nazwaWatku;
    }
	@Override
	public void run() {
		while(true)
		{
			this.muzeum.wpuscKlienta(nazwaWatku);
		}
	}
}