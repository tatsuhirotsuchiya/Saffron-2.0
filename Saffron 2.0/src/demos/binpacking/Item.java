package demos.binpacking;

public class Item
{
	private String name;
	private int size;

	public Item(String name, int size)
	{
		this.setName(name);
		this.setSize(size);
	}

	public String getName()
	{
		return name;
	}

	public int getSize()
	{
		return size;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	@Override
	public String toString()
	{
		return getName() + "(" + getSize() + ")";
	}
}