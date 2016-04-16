package Zadanie_3;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import Zadanie_3.Methods.NameGenerator;
import Zadanie_3.Methods.TimeWatch;

public class Client implements Runnable {
	private int Id;
	private String Name;
	private int CurrentHealthPoints;
	private int MaxHealtPoints;
	private Status Statuses;
	private int HungryLimit;
	private volatile boolean IsAlive;
	
	private TimeWatch timeWatcher;
	private long elapsedTime;
	
	private Random myRand = new Random(); // symulowanie losowosci
	private NameGenerator myNameGenerator = new NameGenerator();
	
	public Client(int id, int healthPoints)
	{
		this.Id = id;
		this.Name = myNameGenerator.getName();
		this.CurrentHealthPoints = this.MaxHealtPoints = healthPoints;
		this.HungryLimit = 80; // ile % maksymalnego zycia dla danego klienta bedzie rownoznaczne z glodem
		this.Statuses = Status.Walking;
		this.IsAlive = true; // NARODZINY NOWEGO KLIENTA
		timeWatcher = TimeWatch.start();
	}

	@Override
	public void run() {
		// taski do zrobienia:
		// - klient chodzi
		// - klient po w jakims czasie spada mu g³ód 
		// - gdy bedzie g³odny musi iœæ do piekarni
		// - jeœli czekanie klienta bedzie na tyle d³ugie w kolejce to klient umiera
		
		while(true)
		{
			if(this.IsAlive)
			{
				// Z biegiem czasu klient robi sie bardziej glodny
				HitClient();
				// Sprawdzamy status klienta i nadajemy dany status.
				ChangeStatus(CheckHealthPoints());
				
				//sprawdzamy jaki ma status. W zaleznosci od statusu robimy jakis event
				if(this.Statuses == Status.Killed)
				{
					KillClient();
				}
				
				try 
				{
					Thread.sleep(myRand.nextInt(2000));
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				//ShowVitalFunctions();
			}
			else
			{
				// tu klient juz nie zyje...
				// usypiamy watek na 5 sekund po czym wychodzimy z nieskonczonej petli. W ten sposob zabijamy watek 
				try 
				{
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				System.out.println(this.Name + " is dead...");
				break;
			}
		}
	}
	
	public Status CheckHealthPoints()
	{
		if(this.IsAlive)
		{
			// jesli zdrowie bedzie mniejsze od hungry limit (% z maxHealth) i wieksze od 0
			if((this.CurrentHealthPoints < (this.HungryLimit*this.MaxHealtPoints)/100) && (this.CurrentHealthPoints > 0))
			{
				return Status.Hungry;
			}
			if(this.CurrentHealthPoints <= 0)
			{
				return Status.Killed;
			}
		}
		return Status.Walking;
	}
	
	public void HitClient()
	{
		// jesli nasz klient jest g³odny to g³ód spada mu szybciej..
		if(this.Statuses == Status.Hungry)
		{
			this.CurrentHealthPoints = this.CurrentHealthPoints - myRand.nextInt(50);
		}
		else
		{
			this.CurrentHealthPoints = this.CurrentHealthPoints - myRand.nextInt(20);	
		}
	}
	
	public int GetId()
	{
		return this.Id;
	}
	
	public String GetName()
	{
		return this.Name;
	}
	
	public boolean GetIsAlive()
	{
		return this.IsAlive;
	}
	
	public long GetElapsedTime()
	{
		return this.elapsedTime;
	}

	
	public void KillClient()
	{
		this.IsAlive = false;
		this.elapsedTime = timeWatcher.time(TimeUnit.SECONDS);
		System.out.println(this.Name + " has been killed...");
	}
	
	public void ChangeStatus(Status newStatus)
	{
		this.Statuses = newStatus;
	}
	
	public void ShowVitalFunctions()
	{
		String vitalFunctions = String.format("%s:\nCzy zyje?: %s\nStatus: %s\nAktualne zycie: %d\n------------------------------------------------------------------------------------------", 
				this.Name, 
				this.IsAlive, 
				this.Statuses, 
				this.CurrentHealthPoints);
		System.out.println(vitalFunctions);
	}
}
