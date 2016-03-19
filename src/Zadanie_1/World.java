package Zadanie_1;

import java.util.*;

public class World {
	public int[] tab = new int[10];
	public int setInteger()
	{
		Random randomNumber = new Random();
		for(int i = 0; i<10;i++)
		{
			if(tab[i] == 0)
			{
				tab[i] = randomNumber.nextInt(50) + 1;
				System.out.println("Wprowadza nowa liczbe calkowita.");
				break;
			}
		}
		if(tab[9] != 0)
		{
			System.out.println("Czyœci tablicê");
			cleanArray();
			System.out.println("Wprowadza na pierwsza pozycje losowa liczbe calkowita.");
			tab[0] = randomNumber.nextInt(50) + 1;
		}
		return randomNumber.nextInt(50) + 1;
	}
	
	public void cleanArray()
	{
		for(int i = 0;i<10;i++)
		{
			tab[i] = 0;
		}
	}

}