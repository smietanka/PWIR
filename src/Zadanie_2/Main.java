package Zadanie_2;

public class Main {

	public static void main(String[] args) {
		
        Muzeum mojeMuzeum = new Muzeum(50, 20);
        
        // dwie bramy wejsciowe
        new Thread(new BramaWejsciowa(mojeMuzeum, "lew�")).start();
        new Thread(new BramaWejsciowa(mojeMuzeum, "praw�")).start();
        //new Thread(new BramaWejsciowa(mojeMuzeum, "�rodkow�")).start();
        //new Thread(new BramaWejsciowa(mojeMuzeum, "ukryt�")).start();
        
        // jedna brama wyjsciowa
        new Thread(new BramaWyjsciowa(mojeMuzeum)).start();
        //new Thread(new BramaWyjsciowa(mojeMuzeum)).start();
        //new Thread(new BramaWyjsciowa(mojeMuzeum)).start();
	}
}