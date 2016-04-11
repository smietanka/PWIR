package Zadanie_3;

public class Main {

	public static void main(String[] args) {
		
		new Thread(new ThreadManager(50)).start();
	}

}
