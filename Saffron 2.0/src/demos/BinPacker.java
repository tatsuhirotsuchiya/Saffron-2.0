/**
 * 
 * ConditionalAdderDemo.java	1.0 2018/11/05
 * Copyright 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos;

import java.util.ArrayList;
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

public class BinPacker
{
	public static void main(String[] args) throws Exception
	{
		Item[] items = new Item[]
		{ new Item("A", 127), new Item("B", 121), new Item("C", 37),
				new Item("D", 12), new Item("E", 53), new Item("F", 26),
				new Item("G", 17), new Item("H", 32) };

		Bin[] bins = new Bin[]
		{ new Bin("Bin0", 150), new Bin("Bin1", 100), new Bin("Bin2", 185) };

		ArrayList<ArrayList<Item>> solution = pack(items, bins);

		System.out.println(solution);
	}

	public static ArrayList<ArrayList<Item>> pack(Item[] items, Bin[] bins)
			throws Exception
	{
		int numberBins = bins.length;
		int numberItems = items.length;

		int maxBin = Integer.MIN_VALUE;
		for (int i = 0; i < numberBins; i++)
			if (bins[i].getCapacity() > maxBin)
				maxBin = bins[i].getCapacity();
		NaturalNumber.setLargestNaturalNumber(maxBin);

		IBooleanVariable[][] partition = new IBooleanVariable[numberBins][numberItems];
		for (int i = 0; i < numberBins; i++)
			for (int j = 0; j < numberItems; j++)
				partition[i][j] = BooleanVariable
						.getBooleanVariable("partition-" + i + "-" + j);
		IProblem partitionProblem = new BitArrayPartition(partition);

		INaturalNumber[] naturalNumberArray = new INaturalNumber[numberItems];
		for (int i = 0; i < numberItems; i++)
			naturalNumberArray[i] = new NaturalNumber(items[i].getName());
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
					naturalNumberArray[i], items[i].getSize());

		IProblem[] binCapacityArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
			binCapacityArray[i] = new NaturalNumberFixer(binCapacityNNArray[i],
					bins[i].getCapacity());
		IProblem binCapacityFixer = new Conjunction(binCapacityArray);

		IProblem[] sizesArray = new IProblem[numberItems];
		for (int i = 0; i < numberItems; i++)
			sizesArray[i] = new NaturalNumberFixer(naturalNumberArray[i],
					items[i].getSize());
		IProblem sizesProblem = new Conjunction(sizesArray);

		IProblem p = new Conjunction(new IProblem[]
		{ sizesProblem, binCapacityFixer, partitionProblem, sumProb, binsFit });

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			ArrayList<ArrayList<Item>> solution = new ArrayList<ArrayList<Item>>();
			for (int i = 0; i < numberBins; i++)
			{
				ArrayList<Item> curr = new ArrayList<Item>();
				for (int j = 0; j < numberItems; j++)
					if (partition[i][j].getValue())
						curr.add(items[j]);
				solution.add(curr);
			}
			BooleanLiteral.reset(s);
			return solution;
		}
		else
			return null;
	}
}

class Item
{
	private String name;
	private int size;

	public Item(String name, int size)
	{
		this.setName(name);
		this.setSize(size);
	}

	public String toString()
	{
		return getName() + "(" + getSize() + ")";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}
}