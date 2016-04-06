package Zadanie_2;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Random losowaLiczba = new Random();
        Muzeum mojeMuzeum = new Muzeum(50);
        new Thread(new BramaWejsciowa(mojeMuzeum, "Brama lewa",losowaLiczba.nextInt(50))).start();
        new Thread(new BramaWejsciowa(mojeMuzeum, "Brama prawa",losowaLiczba.nextInt(50))).start();
        new Thread(new BramaWyjsciowa(mojeMuzeum)).start();
	}
}