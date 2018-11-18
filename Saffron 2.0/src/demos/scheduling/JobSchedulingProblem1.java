package demos.scheduling;

import java.util.ArrayList;

public class JobSchedulingProblem1
{
	public static void main(String[] args) throws Exception
	{
		Task[] tasks = new Task[]
				{
						new Task("Task-0", 1, null),
						new Task("Task-1", 2, null),
						new Task("Task-2", 1, null),
						new Task("Task-3", 4, null),
				};
		tasks[0].setPredecessors(new Task[] {tasks[2]});
		tasks[2].setPredecessors(new Task[] {tasks[1]});

		Processor[] procs = new Processor[10];
		for (int i = 0; i < procs.length; i++)
			procs[i] = new Processor("Proc-0");

		ArrayList<ArrayList<Task>> solution = Scheduler.schedule(tasks, procs);

		System.out.println(Scheduler.getProblem());
		System.out.println(solution);
	}
}
