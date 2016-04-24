package Zadanie_3;

import Zadanie_3.Types.Settings;

public class Main {

	public static void main(String[] args) {
		Settings mySetup = new Settings();
		
		// Ustawienia
		mySetup.healthPoints = 100;
		mySetup.clientOnMap = 5;
		mySetup.timeDoughnutMake = 4; //czas w sekundach
		mySetup.hungryInPercentage = 95;
		mySetup.howManyPos = 1;
		// Koniec ustawieñ
		
		ThreadManager mojThreadManager = new ThreadManager(mySetup);
		Thread mojWatek = new Thread(mojThreadManager);
		mojWatek.start();
	}
}
