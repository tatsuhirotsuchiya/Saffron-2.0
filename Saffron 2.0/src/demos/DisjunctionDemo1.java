package demos;

import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Disjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

/**
 * <p>
 * Title: DisjunctionDemo1
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class DisjunctionDemo1
{
	public static void main(String[] args) throws Exception
	{
		List<IBooleanLiteral> s;

		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IBooleanVariable Y = BooleanVariable.getBooleanVariable("Y");
		IBooleanVariable Z = BooleanVariable.getBooleanVariable("Z");

		IProblem p1 = new BitFixer(X, false);
		IProblem p2 = new BitFixer(X, true);
		IProblem p3 = new BitFixer(Y, false);
		IProblem p4 = new BitFixer(Y, true);

		IProblem p5 = new Disjunction(p1);
		IProblem p6 = new Disjunction(p1, p2);
		IProblem p7 = new Disjunction(p1, p2, Y);
		IProblem p8 = new Disjunction(p1, p2, p3);
		IProblem p9 = new Disjunction(p1, p2, p3, p4);
		IProblem p10 = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 });
		IProblem p11 = new Disjunction(new IProblem[]
		{ p1, p2, p3, p4 }, new IBooleanVariable[]
		{ X, Y, Z });

		System.out.println(p1);
		s = p1.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
		}
		else
			System.out.println("No solution.");

		System.out.println(p10);
		s = p10.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
		}
		else
			System.out.println("No solution.");

		System.out.println(p11);
		s = p11.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= " + X.getValue());
			System.out.println("Y= " + Y.getValue());
			System.out.println("Z= " + Z.getValue());
		}
		else
			System.out.println("No solution.");
	}
}