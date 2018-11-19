package demos.scheduling;

import java.util.List;

public class Task
{
	private long duration;
	
	private long finish;
	private String name;
	private List<Task> predecessors;
	
	private long start;
	

	public Task(String name, long duration)
	{
		this.name = name;
		this.duration = duration;
	}

	public long getDuration()
	{
		return duration;
	}

	public long getFinish()
	{
		return finish;
	}

	public String getName()
	{
		return this.name;
	}

	public List<Task> getPredecessors()
	{
		return predecessors;
	}

	public long getStart()
	{
		return start;
	}

	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	public void setFinish(long decimal)
	{
		this.finish = decimal;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPredecessors(List<Task> predecessors)
	{
		this.predecessors = predecessors;
	}

	public void setStart(long decimal)
	{
		this.start = decimal;
	}

	@Override
	public String toString()
	{
		return "\nTask [name=" + name + ", start=" + start + ", duration="
				+ duration + ", finish=" + finish + "]";
	}
}
