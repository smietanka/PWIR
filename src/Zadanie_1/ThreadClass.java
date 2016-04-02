package Zadanie_1;

public class ThreadClass implements Runnable 
{
	public void run() 
	{
		World firstThread = new World();
		World secondThread = new World();
		while(true)
		{
			try {
				
				System.out.println(firstThread.setInteger());
				System.out.println(secondThread.setInteger());
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	public static void main(String args[])
	{
		World initialize = new World();
		new Thread(new ThreadClass()).start();
		new Thread(new SecondThreadClass(initialize)).start();
   }
}