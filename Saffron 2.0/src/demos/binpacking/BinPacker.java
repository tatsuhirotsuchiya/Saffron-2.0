/**
 * 
 * ConditionalAdderDemo.java	1.0 2018/11/05
 * Copyright 2018 Positronic Software
 * @author Kerry Michael Soileau
 * @version 1.0
 */

package demos.binpacking;

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

public class BinPacker
{
	private static IProblem binPackingProblem;

	public static IProblem getProblem()
	{
		return binPackingProblem;
	}

	public static ArrayList<ArrayList<Item>> pack(Item[] items, Bin[] bins)
			throws Exception
	{
		int numberBins = bins.length;
		int numberItems = items.length;

		long maxBin = Integer.MIN_VALUE;
		for (int i = 0; i < numberBins; i++)
			if (bins[i].getCapacity() > maxBin)
				maxBin = bins[i].getCapacity();
		NaturalNumber.setLargestNaturalNumber(maxBin);

		System.out.println("Building partitionProblem...");
		IBooleanVariable[][] partition = new IBooleanVariable[numberBins][numberItems];
		for (int i = 0; i < numberBins; i++)
		{
			IBooleanVariable[] currentBin = partition[i];
			for (int j = 0; j < numberItems; j++)
				currentBin[j] = BooleanVariable.getBooleanVariable("partition-"
						+ i + "-" + j);
		}
		IProblem partitionProblem = new BitArrayPartition(partition);

		System.out.println("Building adderProblem...");
		INaturalNumber[] itemSizeNaturalNumberArray = new INaturalNumber[numberItems];
		for (int i = 0; i < numberItems; i++)
			itemSizeNaturalNumberArray[i] = new NaturalNumber(
					items[i].getName());
		INaturalNumber[] condSum = new INaturalNumber[numberBins];
		IProblem[] adderProblemArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
		{
			condSum[i] = new NaturalNumber("NNCondSum-" + i);
			adderProblemArray[i] = new ConditionalAdder(
					itemSizeNaturalNumberArray, partition[i], condSum[i]);
		}
		IProblem adderProblem = new Conjunction(adderProblemArray);

		System.out.println("Building binFitterProblem...");
		INaturalNumber[] binCapacityNNArray = new INaturalNumber[numberBins];
		for (int i = 0; i < numberBins; i++)
			binCapacityNNArray[i] = new NaturalNumber("BinSize-" + i);
		IProblem[] binFitProblemArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
		{
			binFitProblemArray[i] = new NaturalNumberLEQer(condSum[i],
					binCapacityNNArray[i]);
		}
		IProblem binFitterProblem = new Conjunction(binFitProblemArray);

		System.out.println("Building binCapacityFixerProblem...");
		IProblem[] binCapacityProblemArray = new IProblem[numberBins];
		for (int i = 0; i < numberBins; i++)
			binCapacityProblemArray[i] = new NaturalNumberFixer(
					binCapacityNNArray[i], bins[i].getCapacity());
		IProblem binCapacityFixerProblem = new Conjunction(
				binCapacityProblemArray);

		System.out.println("Building sizesProblem...");
		IProblem[] sizesProblemArray = new IProblem[numberItems];
		for (int i = 0; i < numberItems; i++)
			sizesProblemArray[i] = new NaturalNumberFixer(
					itemSizeNaturalNumberArray[i], items[i].getSize());
		IProblem sizesProblem = new Conjunction(sizesProblemArray);

		System.out.println("Building binPackingProblem...");
		binPackingProblem = new Conjunction(new IProblem[]
		{ sizesProblem, binCapacityFixerProblem, partitionProblem,
				adderProblem, binFitterProblem });

		System.out.println("Solving SAT problem...");
		List<IBooleanLiteral> blList = binPackingProblem.findModel(Problem
				.defaultSolver());
		
		System.out.println("Returning solution...");
		if (blList != null && blList.size() > 0)
		{
			BooleanLiteral.interpret(blList);
			ArrayList<ArrayList<Item>> solution = new ArrayList<ArrayList<Item>>();
			for (int i = 0; i < numberBins; i++)
			{
				IBooleanVariable[] currentBin = partition[i];
				ArrayList<Item> currentBinContents = new ArrayList<Item>();
				for (int j = 0; j < numberItems; j++)
					if (currentBin[j].getValue())
						currentBinContents.add(items[j]);
				solution.add(currentBinContents);
			}
			BooleanLiteral.reset(blList);
			return solution;
		}
		else
			return null;
	}
}