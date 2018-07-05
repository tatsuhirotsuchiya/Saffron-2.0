package demos;

import java.util.List;

import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.EquivalenceRelation;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.TwoBitAdder;

public class TwoBitAdderDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[] bva =
		{ BooleanVariable.getBooleanVariable("X"),
				BooleanVariable.getBooleanVariable("Y"),
				BooleanVariable.getBooleanVariable("Z"),
				BooleanVariable.getBooleanVariable("C") };
		IProblem problem = new TwoBitAdder(bva[0], bva[1], bva[2], bva[3]);

		System.out.println(problem);

		EquivalenceRelation er = ((Problem) problem).buildEquivalenceRelation();
		System.out.println(er.equivalenceClasses() + "\nFinis");

		// List s=PartialSolution.solveList(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("bva[0]= " + bva[0].getValue());
			System.out.println("bva[1]= " + bva[1].getValue());
			System.out.println("bva[2]= " + bva[2].getValue());
			System.out.println("bva[3]= " + bva[3].getValue());
		}
		else
			System.out.println("No solution.");
	}
}