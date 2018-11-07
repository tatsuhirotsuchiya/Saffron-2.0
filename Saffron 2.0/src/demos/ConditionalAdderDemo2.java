/**
 * 
 * ConditionalAdderDemo.java	1.0 2018/11/05
 * Copyright 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos;

import java.util.List;

import naturalnumbers.ConditionalAdder;
import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberLEQer;
import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class ConditionalAdderDemo2
{
	public static void main(String[] args) throws Exception
	{
		pack();
	}

	public static void pack() throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(425);

		Item[] items = new Item[]
		{ new Item("A", 127), new Item("B", 121), new Item("C", 37),
				new Item("D", 12), new Item("E", 53), new Item("F", 26),
				new Item("G", 17), new Item("H", 32) };
		
		INaturalNumber[] items0=new INaturalNumber[items.length];
		for(int i=0;i<items.length;i++)
			items0[i] = new NaturalNumber(items[i].name);

		INaturalNumber binSize = new NaturalNumber("BinSize");

		int bins = 3;
		int bits = items.length;
		int binCapacity = 150;

		IBooleanVariable[][] partition = new IBooleanVariable[bins][bits];
		for (int i = 0; i < bins; i++)
			for (int j = 0; j < bits; j++)
				partition[i][j] = BooleanVariable
						.getBooleanVariable("partition-" + i + "-" + j);
		IProblem partitionProblem = new BitArrayPartition(partition);

		INaturalNumber[] condSum = new INaturalNumber[bins];
		IProblem[] adderProblem = new IProblem[bins];
		for (int i = 0; i < bins; i++)
		{
			condSum[i] = new NaturalNumber("NNCondSum-" + i);
			adderProblem[i] = new ConditionalAdder(items0, partition[i],
					condSum[i]);
		}
		IProblem sumProb = new Conjunction(adderProblem);

		IProblem[] binFitProblem = new IProblem[bins];
		for (int i = 0; i < bins; i++)
		{
			binFitProblem[i] = new NaturalNumberLEQer(condSum[i], binSize);
		}
		IProblem binsFit = new Conjunction(binFitProblem);

		IProblem[] fixerProblemArray = new IProblem[items.length];
		for(int i=0;i<items.length;i++)
			fixerProblemArray[i]=new NaturalNumberFixer(items0[i], items[i].size);
		
		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(items0[0], 127), new NaturalNumberFixer(items0[1], 121),
				new NaturalNumberFixer(items0[2], 37), new NaturalNumberFixer(items0[3], 12),
				new NaturalNumberFixer(items0[4], 53), new NaturalNumberFixer(items0[5], 26),
				new NaturalNumberFixer(items0[6], 17), new NaturalNumberFixer(items0[7], 32),
				new NaturalNumberFixer(binSize, binCapacity), partitionProblem,
				sumProb, binsFit });

		System.out.println(p);

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for(int i=0;i<items.length;i++)
				System.out.println(items[i].name+" = " + items0[i]);

			for (int i = 0; i < bins; i++)
			{
				String str = "";
				for (int j = 0; j < bits; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}

			for (int i = 0; i < bins; i++)
				System.out.println(condSum[i]);

			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}

class Item
{
	String name;
	int size;

	public Item(String name, int size)
	{
		this.name = name;
		this.size = size;
	}
}