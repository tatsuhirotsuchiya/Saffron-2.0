package demos.scheduling;

import java.util.Arrays;

public class Task
{
	private String name;
	private long duration;
	private Task[] predecessors;

	public Task(String name, long duration, Task[] predecessors)
	{
		this.name = name;
		this.duration = duration;
		this.predecessors = predecessors;
	}

	public long getDuration()
	{
		return duration;
	}

	public String getName()
	{
		return this.name;
	}

	public Task[] getPredecessors()
	{
		return predecessors;
	}

	public void setDuration(long duration)
	{
		this.duration = duration;
	}

	public void setPredecessors(Task[] predecessors)
	{
		this.predecessors = predecessors;
	}

	public String toString()
	{
		return "\nTask [name=" + name + ", duration=" + duration
				+ ", predecessors=" + Arrays.toString(predecessors) + "]";
	}
}
