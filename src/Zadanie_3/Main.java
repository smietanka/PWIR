package Zadanie_3;

public class Main {

	public static void main(String[] args) {
		ThreadManager mojThreadManager = new ThreadManager(3);
		
		Thread mojWatek = new Thread(mojThreadManager);
		mojWatek.start();
	}
}
