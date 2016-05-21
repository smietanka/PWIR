package Zadanie_3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Zadanie_3.Methods.NameGenerator;
import Zadanie_3.Methods.TimeWatch;
import Zadanie_3.Types.Bakery;
import Zadanie_3.Types.PointOfSale;

public class Client implements Runnable {
	private int Id;
	private String Name;
	private int CurrentHealthPoints;
	private int MaxHealtPoints;
	private Status Statuses;
	private int HungryLimit;
	private volatile boolean IsAlive;
	private volatile boolean IsInQueue;
	
	private TimeWatch timeWatcher;
	private long elapsedTime;
	
	private Random myRand = new Random(); // symulowanie losowosci
	private NameGenerator myNameGenerator = new NameGenerator();
	
	private Bakery myBakery;
	
	//need for graphic
	private int speed;
	public int posX;
	public int posY;
	public int posXdestination;
	public int posYdestination;
	private volatile boolean targetIsAcquired;
	public JLabel Image;// = new JLabel();
	public GameWindow window;
	//public JLayeredPane layeredPane;
	
	public Client(int id, int healthPoints, Bakery myBakery, int hungryPercentage, GameWindow window, JLayeredPane layeredPane)
	{
		this.Id = id;
		this.Name = myNameGenerator.getName();
		this.CurrentHealthPoints = this.MaxHealtPoints = healthPoints;
		this.HungryLimit = hungryPercentage; // ile % maksymalnego zycia dla danego klienta bedzie rownoznaczne z glodem
		this.Statuses = Status.Walking;
		this.IsAlive = true; // NARODZINY NOWEGO KLIENTA
		this.IsInQueue = false;
		timeWatcher = TimeWatch.start();
		this.myBakery = myBakery;
		
		Random rand = new Random();
		
		this.speed = 10;
		
		this.posX = rand.nextInt(800);
		int modulo = this.posX%10;
		if (modulo != 0)
			this.posX -= modulo;
		
		this.posY = rand.nextInt(200)+300;
		modulo = this.posY%10;
		if (modulo != 0)
			this.posY -= modulo;
		
		this.targetIsAcquired = false;
		this.RandDestinationXY();
		this.window = window;
		this.Image = new JLabel(this.Name + "[X: " + this.posX + ", Y: " + this.posY + "]", new ImageIcon("E:/ludek.png"), JLabel.CENTER);
		this.Image.setVerticalTextPosition(JLabel.BOTTOM);
		//this.Image.setIcon(new ImageIcon("E:/ludek.png"));
		this.Image.setBounds(posX, posY, 250, 250);
		layeredPane = window.getLayeredPane();
		layeredPane.add(Image, new Integer(this.Id+1));
	}

	@Override
	public void run() {
		while(true)
		{
			if(this.IsAlive)
			{
				if(!this.Statuses.equals(Status.Buying))
				{
					// Z biegiem czasu klient robi sie bardziej glodny
					HitClient();	
				}
				
				Move();
				
				// Sprawdzamy status klienta i nadajemy dany status.
				ChangeStatus(CheckHealthPoints());
				
				// jesli klient jest glodny to trzeba go wyslac do cukierni 
				if(this.Statuses == Status.Hungry)
				{
					// jesli ma status InQueue to nie rob nic.
					if(this.Statuses != Status.InQueue)
					{
						if(!this.IsInQueue)
						{
							//przekazuje do kolejki kase a nie clienta
							GoToQueue(myBakery.WhereIsLessClients());	
						}
					}
				}
				
				//sprawdzamy jaki ma status. W zaleznosci od statusu robimy jakis event
				if(this.Statuses == Status.Killed)
				{
					KillClient();
				}
				
				try 
				{
					Thread.sleep(myRand.nextInt(1000));
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
				// usypiamy watek na 1 sekunde po czym wychodzimy z nieskonczonej petli. W ten sposob zabijamy watek 
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
		if(this.IsAlive && !this.IsInQueue)
		{
			if(this.CurrentHealthPoints <= 0)
			{
				return Status.Killed;
			}
			
			if((!this.Statuses.equals(Status.InQueue)) || (this.Statuses.equals(Status.Hungry)))
			{
				// jesli zdrowie bedzie mniejsze od hungry limit (% z maxHealth) i wieksze od 0
				if((this.CurrentHealthPoints < (this.HungryLimit*this.MaxHealtPoints)/100) && (this.CurrentHealthPoints > 0))
				//if((this.CurrentHealthPoints < this.HungryLimit) && (this.CurrentHealthPoints > 0))
				{
					return Status.Hungry;
				}
			}
			else
			{
				return Status.InQueue;
			}
		}
		return this.Statuses;
	}
	
	public void HitClient()
	{
		// jesli nasz klient jest g³odny to g³ód spada mu szybciej..
		if(this.Statuses == Status.Hungry)
		{
			this.CurrentHealthPoints = this.CurrentHealthPoints - myRand.nextInt(4);
		}
		else
		{
			this.CurrentHealthPoints = this.CurrentHealthPoints - myRand.nextInt(2);	
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
	
	public void ChangeIsInQueue(boolean toChange)
	{
		this.IsInQueue = toChange;
	}
	
	public void SleepClient(int timeSleep)
	{
		try {
			Thread.sleep(timeSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void KillClient()
	{
		this.IsAlive = false;
		this.elapsedTime = timeWatcher.time(TimeUnit.SECONDS);
		//System.out.println(this.Name + " has been killed...");
	}

	public void GoToQueue(PointOfSale pos)
	{
		this.IsInQueue = true;
		this.Statuses = Status.InQueue;
		pos.MoveToQueue(pos, this);
		pos.PutClientToQueue(this);
		System.out.println(this.Name + " idzie do kolejki " + pos.Name);
	}
	
	public void ChangeStatus(Status newStatus)
	{
		this.Statuses = newStatus;
	}
	
	public void ShowVitalFunctions()
	{
		String vitalFunctions = String.format("%s:\nCzy zyje?: %s\nAktualne zycie: %d\n------------------------------------------------------------------------------------------", 
				this.Name,  
				this.Statuses, 
				this.CurrentHealthPoints);
		System.out.println(vitalFunctions);
	}
	
	public void EatDoughnut()
	{
		this.CurrentHealthPoints = this.MaxHealtPoints;
		this.Statuses = Status.Walking;
	}
	
	public void RandDestinationXY()
	{
		Random Rand = new Random();
		this.targetIsAcquired = false;
		
		this.posXdestination = Rand.nextInt(600);
		int modulo = this.posXdestination%10;
		if (modulo != 0)
			this.posXdestination -= modulo;
			
		this.posYdestination = Rand.nextInt(250)+300;
		int modulo2 = this.posYdestination%10;
		if (modulo2 != 0)
			this.posYdestination -= modulo2;
		
	}
	
	public void CheckDestinationXY()
	{
		if(this.posX == this.posXdestination && this.posY == this.posYdestination)
		{
			this.targetIsAcquired = true;
			this.RandDestinationXY();
		}
	}
	
	public void MoveUp()
	{
		this.posY -= this.speed;
	}
	
	public void MoveDown()
	{
		this.posY += this.speed;
	}
	
	public void MoveLeft()
	{
		this.posX -= this.speed;
	}
	
	public void MoveRight()
	{
		this.posX += this.speed;
	}
	
	public void Move()
	{
		CheckDestinationXY();
		if(this.posX<this.posXdestination)
		{
			MoveRight();
		}
		else
		{
			MoveLeft();
		}
		if(this.posY>this.posYdestination)
		{
			MoveUp();
		}
		else
		{
			MoveDown();
		}
		this.Image.setText(this.Name + "[X: " + this.posX + ", Y: " + this.posY + "]["+this.posXdestination+","+this.posYdestination+"]");
		this.Image.setLocation(this.posX, this.posY);
		
		
	}
}
