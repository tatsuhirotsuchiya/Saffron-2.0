package demos.binpacking;

public class Bin
{
	private int capacity;
	private String name;

	public Bin(String name, int capacity)
	{
		super();
		this.capacity = capacity;
		this.name = name;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public String getName()
	{
		return name;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}