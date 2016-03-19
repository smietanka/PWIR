package Zadanie_1;

public class SecondThreadClass implements Runnable
{
	public World myThread;
	public SecondThreadClass(World name)
	{
		myThread = name;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				System.out.println(myThread.setInteger());
				Thread.sleep(3000);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
