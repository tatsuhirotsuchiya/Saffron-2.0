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
		NaturalNumber.setLargestNaturalNumber(425);

		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber D = new NaturalNumber("D");
		INaturalNumber E = new NaturalNumber("E");
		INaturalNumber F = new NaturalNumber("F");
		INaturalNumber G = new NaturalNumber("G");
		INaturalNumber H = new NaturalNumber("H");

		INaturalNumber binSize = new NaturalNumber("BinSize");

		INaturalNumber[] items = new INaturalNumber[]
		{ A, B, C, D, E, F, G, H };

		int bins = 3;
		int bits = items.length;
		int binCapacity=150;

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
			adderProblem[i] = new ConditionalAdder(items, partition[i],
					condSum[i]);
		}
		IProblem sumProb = new Conjunction(adderProblem);

		IProblem[] binFitProblem = new IProblem[bins];
		for (int i = 0; i < bins; i++)
		{
			binFitProblem[i] = new NaturalNumberLEQer(condSum[i], binSize);
		}
		IProblem binsFit = new Conjunction(binFitProblem);

		IProblem p = new Conjunction(new IProblem[]
		{ new NaturalNumberFixer(A, 127), new NaturalNumberFixer(B, 121),
				new NaturalNumberFixer(C, 37), new NaturalNumberFixer(D, 12),
				new NaturalNumberFixer(E, 53), new NaturalNumberFixer(F, 26),
				new NaturalNumberFixer(G, 17), new NaturalNumberFixer(H, 32),
				new NaturalNumberFixer(binSize, binCapacity), partitionProblem,
				sumProb, binsFit });

		System.out.println(p);

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("A = " + A);
			System.out.println("B = " + B);
			System.out.println("C = " + C);
			System.out.println("D = " + D);
			System.out.println("E = " + E);
			System.out.println("F = " + F);
			System.out.println("G = " + G);
			System.out.println("H = " + H);

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