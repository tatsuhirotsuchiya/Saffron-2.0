package demos.scheduling;

public class Processor
{
	private String name;

	public Processor(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public String toString()
	{
		return "Processor [name=" + name + "]";
	}
}
