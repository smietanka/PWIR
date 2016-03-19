package Zadanie_1;

public class ThreadClass implements Runnable 
{
	public void run() 
	{
		while(true)
		{
			try {
				World nazwa = new World();
				System.out.println(nazwa.setInteger());
				Thread.sleep(1000);
				// Spanie na 10 sekundssss

			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	public static void main(String args[])
	{
      new Thread(new ThreadClass()).start();
   }
}