package Zadanie_2;

public class Main {

	public static void main(String[] args) {
        Muzeum mojeMuzeum = new Muzeum(50);
        new Thread(new BramaWejsciowa(mojeMuzeum, "Brama lewa")).start();
        new Thread(new BramaWejsciowa(mojeMuzeum, "Brama prawa")).start();
        new Thread(new BramaWyjsciowa(mojeMuzeum)).start();
	}
}