package Zadanie_3;

import Zadanie_3.Types.Settings;

public class Main {

	public static void main(String[] args) {
		Settings mySetup = new Settings();
		
		// Ustawienia
		mySetup.healthPoints = 100;
		mySetup.clientOnMap = 1;
		mySetup.timeDoughnutMake = 2; //czas w sekundach
		// Koniec ustawieñ
		
		ThreadManager mojThreadManager = new ThreadManager(mySetup);
		Thread mojWatek = new Thread(mojThreadManager);
		mojWatek.start();
	}
}
