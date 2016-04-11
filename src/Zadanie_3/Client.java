package Zadanie_3;

public class Client implements Runnable {
	private int id;
	private String Name;
	private int CurrentHealthPoints;
	private int MaxHealtPoints;
	private Status Statuses;
	private int HungryLimit;
	
	public Client(int id)
	{
		this.id = id;
		SetParameters();
	}
	
	public int getId()
	{
		return this.id;
	}
	public void SetParameters()
	{
		this.Name = "Klient "+this.id;
		this.CurrentHealthPoints = this.MaxHealtPoints = 100;
		this.HungryLimit = 40;
		this.Statuses = Status.Walking;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this.Name + "jest w statusie: "+ this.Statuses+".");
		
	}
}
