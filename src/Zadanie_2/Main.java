package Zadanie_2;

public class Main {

	public static void main(String[] args) {
		
        Muzeum mojeMuzeum = new Muzeum(2, 4);
        
        // dwie bramy wejsciowe
        new Thread(new BramaWejsciowa(mojeMuzeum, "lew�")).start();
        new Thread(new BramaWejsciowa(mojeMuzeum, "praw�")).start();
        // jedna brama wyjsciowa
        new Thread(new BramaWyjsciowa(mojeMuzeum)).start();
	}
}