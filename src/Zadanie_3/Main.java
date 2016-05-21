package Zadanie_3;

import java.awt.EventQueue;
import Zadanie_3.Window;
import Zadanie_3.Types.Settings;

public class Main {

	public static void main(String[] args) {
				
		Settings mySetup = new Settings();
		
		// Ustawienia standardowe
		mySetup.healthPoints = 100;
		mySetup.clientOnMap = 5;
		mySetup.timeDoughnutMake = 4; //czas w sekundach
		mySetup.hungryInPercentage = 95;
		mySetup.howManyPos = 2;
		// Koniec ustawieñ
		
		/*Pierwotna wersja thread managera
		ThreadManager mojThreadManager = new ThreadManager(mySetup);
		Thread mojWatek = new Thread(mojThreadManager);
		mojWatek.start();
		*/
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Window(mySetup);
			}
		});
		
	}
}
