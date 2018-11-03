package demos;

import java.util.List;

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberFixer;
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

/**
 * <pre>
 * DisjunctionDemo1
 * Copyright (c) 2005 Positronic Software
 * </pre>
 * 
 * @author Kerry Michael Soileau
 * @version 2.0
 */

public class DisjunctionDemo1
{
	public static void main(String[] args) throws Exception
	{
		List<IBooleanLiteral> s;

		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		INaturalNumber A = new NaturalNumber("A");
		INaturalNumber B = new NaturalNumber("B");
		INaturalNumber C = new NaturalNumber("C");
		INaturalNumber D = new NaturalNumber("D");

		IProblem p1 = new NaturalNumberFixer(A, 3);
		IProblem p2 = new NaturalNumberFixer(B, 1);
		IProblem p3 = new NaturalNumberFixer(C, 11);
		IProblem p4 = new NaturalNumberFixer(D, 5);

		IProblem p5 = new Disjunction(p1);
		IProblem p6 = new Disjunction(p1, p2);
		IProblem p7 = new Disjunction(p1, p2, Y);
		IProblem p8 = new Disjunction(p1, p2, p3);
		IProblem p9 = new Disjunction(p1, p2, p3, p4);
		IProblem p10 = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 });
		IProblem p11 = new Conjunction(new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z }), new BitFixer(X, false), new BitFixer(Y, false),
				new BitFixer(Z, false));
		/*
		 * System.out.println(p1); s = p1.findModel(Problem.defaultSolver()); if
		 * (s != null && s.size() > 0) { BooleanLiteral.interpret(s);
		 * System.out.println("X= " + X.getValue()); System.out.println("Y= " +
		 * Y.getValue()); System.out.println("Z= " + Z.getValue()); } else
		 * System.out.println("No solution.");
		 * 
		 * System.out.println(p10); s = p10.findModel(Problem.defaultSolver());
		 * if (s != null && s.size() > 0) { BooleanLiteral.interpret(s);
		 * System.out.println("X= " + X.getValue()); System.out.println("Y= " +
		 * Y.getValue()); System.out.println("Z= " + Z.getValue()); } else
		 * System.out.println("No solution.");
		 */
		System.out.println(p11);
		s = p11.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
			System.out.println("A= " + A);
			System.out.println("B= " + B);
			System.out.println("C= " + C);
			System.out.println("D= " + D);
		}
		else
			System.out.println("No solution.");
	}
}