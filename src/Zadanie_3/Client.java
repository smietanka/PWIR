package Zadanie_3;

import java.util.Random;

public class Client implements Runnable {
	private int Id;
	private String Name;
	private int CurrentHealthPoints;
	private int MaxHealtPoints;
	private Status Statuses;
	private int HungryLimit;
	private volatile boolean IsAlive;
	
	private Random myRand = new Random(); // symulowanie losowosci
	
	public Client(int id)
	{
		this.Id = id;
		SetParameters();
	}
	
	public int getId()
	{
		return this.Id;
	}
	public void SetParameters()
	{
		this.Name = "Klient "+this.Id;
		this.CurrentHealthPoints = this.MaxHealtPoints = 100;
		this.HungryLimit = 40;
		this.Statuses = Status.Walking;
		this.IsAlive = true; // NARODZINY NOWEGO KLIENTA
	}
	
	@Override
	public void run() {
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// taski do zrobienia:
		// - klient chodzi
		// - klient po w jakims czasie spada mu g³ód 
		// - gdy bedzie g³odny musi iœæ do piekarni
		// - jeœli czekanie klienta bedzie na tyle d³ugie w kolejce to klient umiera
		
		
		// jesli CurrentHealthPoints spadnie do zera to przerywamy dzia³anie klienta. Inaczej mówi¹c on umiera :)
		while(true)
		{
			if(this.IsAlive)
			{
				this.CurrentHealthPoints = this.CurrentHealthPoints - myRand.nextInt(80);
				// jesli zyje to:
				if(this.Statuses != Status.Killed)
				{
					// sprawdzamy czy ma 0 zycia, jesli tak to zabijamy
					if(this.CurrentHealthPoints <= 0)
					{
						KillClient();
					}
					
					// jesli nie zyje to omijamy kazdy nastepny blok.
					if(this.IsAlive)
					{
						// jesli ma status glodny to nie trzeba mu nadawac go jeszcze raz.
						if(this.Statuses != Status.Hungry)
						{
							//sprawdzamy czy osiagnal swoj limit glodu, jesli tak to zmieniamy mu status na glodny
							if(this.CurrentHealthPoints < this.HungryLimit )
							{
								ChangeStatus(Status.Hungry);
							}	
						}	
					}
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
				// usypiamy watek na 5 sekund po czym wychodzimy z nieskonczonej petli. W ten sposob zabijamy watek 
				try 
				{
					Thread.sleep(2000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				//System.out.println(this.Name + " juz nie zyje. Status: "+this.Statuses);
				//break;
			}
		}
	}
	
	public void KillClient()
	{
		ChangeStatus(Status.Killed);
		this.IsAlive = false;
		System.out.println(this.Name + " has been killed...");
	}
	
	public void ChangeStatus(Status newStatus)
	{
		this.Statuses = newStatus;
	}
	
	public void ShowVitalFunctions()
	{
		if(this.IsAlive)
		{
			String vitalFunctions = String.format("%s:\nCzy zyje?: %s\nStatus: %s\nAktualne zycie: %d\n------------------------------------------------------------------------------------------", 
					this.Name, 
					this.IsAlive, 
					this.Statuses, 
					this.CurrentHealthPoints);
			System.out.println(vitalFunctions);	
		}
		else
		{
			System.out.println(this.Name + " nie ¿yje...");
		}
		
	}
}
