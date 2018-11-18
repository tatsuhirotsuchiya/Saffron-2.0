package demos.scheduling;

/**
 * 
 * ConditionalAdderDemo.java	1.0 2018/11/05
 * Copyright 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class Scheduler
{
	private static IProblem jobSchedulingProblem;

	public static IProblem getProblem()
	{
		return jobSchedulingProblem;
	}

	public static ArrayList<ArrayList<Task>> schedule(Task[] tasks,
			Processor[] procs) throws Exception
	{
		long startTimeMillis = System.currentTimeMillis();
		int numberProcs = procs.length;
		int numberTasks = tasks.length;
		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[1 + 3 * numberProcs
				+ numberTasks];

		IBooleanVariable[][] partition = new IBooleanVariable[numberProcs][numberTasks];
		for (int i = 0; i < numberProcs; i++)
		{
			IBooleanVariable[] currentBin = partition[i];
			for (int j = 0; j < numberTasks; j++)
				currentBin[j] = BooleanVariable
						.getBooleanVariable("partition-" + i + "-" + j);
		}
		IProblem partitionProblem = new BitArrayPartition(partition);
		stagingArray[stagingIndex++] = partitionProblem;

		jobSchedulingProblem = new Conjunction(stagingArray);

		System.out
				.println((System.currentTimeMillis() - startTimeMillis) / 1000.
						+ ":" + "\tSolving SAT problem...");
		List<IBooleanLiteral> blList = jobSchedulingProblem
				.findModel(Problem.defaultSolver());

		System.out
				.println((System.currentTimeMillis() - startTimeMillis) / 1000.
						+ ":" + "\tReturning solution...");
		if (blList != null && blList.size() > 0)
		{
			BooleanLiteral.interpret(blList);
			ArrayList<ArrayList<Task>> solution = new ArrayList<ArrayList<Task>>();
			for (int i = 0; i < numberProcs; i++)
			{
				IBooleanVariable[] currentProc = partition[i];
				ArrayList<Task> currentProcAssignments = new ArrayList<Task>();
				for (int j = 0; j < numberTasks; j++)
					if (currentProc[j].getValue())
						currentProcAssignments.add(tasks[j]);
				solution.add(currentProcAssignments);
			}
			BooleanLiteral.reset(blList);
			System.out.println(
					(System.currentTimeMillis() - startTimeMillis) / 1000. + ":"
							+ "Finis");
			return solution;
		} else
			return null;
	}
}
