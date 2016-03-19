package Zadanie_1;

import java.util.*;

public class World {
	public static int[] tab = new int[10];
	public int setInteger()
	{
		Random randomNumber = new Random();
		for(int i = 0; i<10;i++)
		{
			if(tab[i] == 0)
			{
				tab[i] = randomNumber.nextInt(50) + 1;
				break;
			}
		}
		if(tab[9] != 0)
		{
			cleanArray();
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