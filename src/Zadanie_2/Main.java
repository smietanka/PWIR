package Zadanie_2;

public class Main {

	public static void main(String[] args) {
		
        Muzeum mojeMuzeum = new Muzeum(3, 40);
        
        // dwie bramy wejsciowe
        new Thread(new BramaWejsciowa(mojeMuzeum, "Brama lewa")).start();
        new Thread(new BramaWejsciowa(mojeMuzeum, "Brama prawa")).start();
        // jedna brama wyjsciowa
        new Thread(new BramaWyjsciowa(mojeMuzeum)).start();
	}
}