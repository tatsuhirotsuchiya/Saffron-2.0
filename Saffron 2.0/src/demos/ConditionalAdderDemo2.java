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

class Bin
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

public class ConditionalAdderDemo2
{
	public static void main(String[] args) throws Exception
	{
		Item[] items = new Item[]
		{ new Item("A", 127), new Item("B", 121), new Item("C", 37),
				new Item("D", 12), new Item("E", 53), new Item("F", 26),
				new Item("G", 17), new Item("H", 32) };

		Bin[] bins = new Bin[]
		{ new Bin("Bin0", 150), new Bin("Bin1", 150), new Bin("Bin2", 150) };

		pack(items, bins);
	}

	public static void pack(Item[] items, Bin[] bins) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(425);

		int numberBins = bins.length;
		int numberItems = items.length;

		IBooleanVariable[][] partition = new IBooleanVariable[numberBins][numberItems];
		for (int i = 0; i < numberBins; i++)
			for (int j = 0; j < numberItems; j++)
				partition[i][j] = BooleanVariable
						.getBooleanVariable("partition-" + i + "-" + j);
		IProblem partitionProblem = new BitArrayPartition(partition);

		INaturalNumber[] naturalNumberArray = new INaturalNumber[numberItems];
		for (int i = 0; i < numberItems; i++)
			naturalNumberArray[i] = new NaturalNumber(items[i].name);
		INaturalNumber[] condSum = new INaturalNumber[numberBins];
		IProblem[] adderProblem = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
		{
			condSum[i] = new NaturalNumber("NNCondSum-" + i);
			adderProblem[i] = new ConditionalAdder(naturalNumberArray,
					partition[i], condSum[i]);
		}
		IProblem sumProb = new Conjunction(adderProblem);

		INaturalNumber[] binCapacityNNArray = new INaturalNumber[numberBins];
		for (int i = 0; i < numberBins; i++)
			binCapacityNNArray[i] = new NaturalNumber("BinSize-" + i);
		IProblem[] binFitProblem = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
		{
			binFitProblem[i] = new NaturalNumberLEQer(condSum[i],
					binCapacityNNArray[i]);
		}
		IProblem binsFit = new Conjunction(binFitProblem);

		IProblem[] fixerProblemArray = new IProblem[numberItems];
		for (int i = 0; i < numberItems; i++)
			fixerProblemArray[i] = new NaturalNumberFixer(
					naturalNumberArray[i], items[i].size);

		IProblem[] binCapacityArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
			binCapacityArray[i] = new NaturalNumberFixer(binCapacityNNArray[i],
					bins[i].getCapacity());
		IProblem binCapacityFixer = new Conjunction(binCapacityArray);

		IProblem[] sizesArray = new IProblem[numberItems];
		for (int i = 0; i < numberItems; i++)
			sizesArray[i] = new NaturalNumberFixer(naturalNumberArray[i],
					items[i].size);
		IProblem sizesProblem = new Conjunction(sizesArray);

		IProblem p = new Conjunction(new IProblem[]
		{ sizesProblem, binCapacityFixer, partitionProblem, sumProb, binsFit });

		System.out.println(p);

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < numberItems; i++)
				System.out.println(items[i].name + " = "
						+ naturalNumberArray[i]);

			for (int i = 0; i < numberBins; i++)
			{
				String str = "";
				for (int j = 0; j < numberItems; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}

			for (int i = 0; i < numberBins; i++)
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