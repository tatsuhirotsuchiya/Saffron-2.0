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

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberOrderer;
import bits.BitArrayPartition;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class Scheduler
{
	private static IProblem jobSchedulingProblem;

	public static IProblem getProblem()
	{
		return jobSchedulingProblem;
	}

	public static ArrayList<ArrayList<Task>> schedule(Task[] task,
			Processor[] proc) throws Exception
	{
		long startTimeMillis = System.currentTimeMillis();
		int numberProcs = proc.length;
		int numberTasks = task.length;
		int stagingIndex = 0;
		IProblem[] stagingArray = new IProblem[1 + 1 + numberTasks*numberTasks*numberProcs];

		// Partition Problem
		IBooleanVariable[][] partition = new IBooleanVariable[numberProcs][numberTasks];
		for (int i = 0; i < numberProcs; i++)
		{
			IBooleanVariable[] currentBin = partition[i];
			for (int j = 0; j < numberTasks; j++)
				currentBin[j] = BooleanVariable.getBooleanVariable("partition-"
						+ proc[i].getName() + "-" + task[j]);
		}
		IProblem partitionProblem = new BitArrayPartition(partition);
		stagingArray[stagingIndex++] = partitionProblem;

		// Start and Finish Times NaturalNumbers
		INaturalNumber[] start = new INaturalNumber[numberTasks];
		for (int i = 0; i < start.length; i++)
			start[i] = new NaturalNumber("Start-" + task[i].getName());
		INaturalNumber[] finish = new INaturalNumber[numberTasks];
		for (int i = 0; i < finish.length; i++)
			finish[i] = new NaturalNumber("Finish-" + task[i].getName());

		// Durations NaturalNumbers
		INaturalNumber[] duration = new INaturalNumber[numberTasks];
		for (int i = 0; i < duration.length; i++)
			duration[i] = new NaturalNumber("Duration-" + task[i].getDuration());

		// Bind Durations Problem
		IProblem[] bindDurationsProblem = new IProblem[duration.length];
		// Duration constraints
		for (int i = 0; i < duration.length; i++)
			bindDurationsProblem[i] = (IProblem) new NaturalNumberFixer(
					duration[i], task[i].getDuration());
		stagingArray[stagingIndex++] = new Conjunction(bindDurationsProblem);

		for(int i=0;i<numberTasks;i++)
		{
			for(int j=0;j<numberTasks;j++)
			{
				for(int k=0;k<numberProcs;k++)
				{
					stagingArray[stagingIndex++] =new Disjunction(
							new BitFixer(partition[k][i],false),
							new BitFixer(partition[k][j],false),
							new NaturalNumberOrderer(finish[j],start[i])
									);
				}
			}
		}

		jobSchedulingProblem = new Conjunction(stagingArray);

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tSolving SAT problem...");
		List<IBooleanLiteral> blList = jobSchedulingProblem.findModel(Problem
				.defaultSolver());

		System.out.println((System.currentTimeMillis() - startTimeMillis)
				/ 1000. + ":" + "\tReturning solution...");
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
						currentProcAssignments.add(task[j]);
				solution.add(currentProcAssignments);
			}
			BooleanLiteral.reset(blList);
			System.out.println((System.currentTimeMillis() - startTimeMillis)
					/ 1000. + ":" + "Finis");
			return solution;
		}
		else
			return null;
	}
}
