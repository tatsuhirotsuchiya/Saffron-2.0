package demos;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bittables.BitTable;
import bittables.BitTableOrer;
import bittables.IBitTable;

public class BitTableOrerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitTable X = new BitTable("X", new IBooleanVariable[3][4]);
		ArrayList<IProblem> pfix = new ArrayList<IProblem>();
		for (int row = 0; row < X.numberRows(); row++)
			for (int column = 0; column < X.numberColumns(); column++)
			{
				boolean value;
				if (Math.random() < .5)
					value = true;
				else
					value = false;
				X.setBooleanVariable(row, column,
						BooleanVariable.getBooleanVariable(
								X.getName() + "_" + row + "_" + column));
				pfix.add(
						new BitFixer(X.getBooleanVariable(row, column), value));
			}
		IBitTable Y = new BitTable("Y", new IBooleanVariable[3][4]);
		for (int row = 0; row < Y.numberRows(); row++)
			for (int column = 0; column < Y.numberColumns(); column++)
			{
				boolean value;
				if (Math.random() < .5)
					value = true;
				else
					value = false;
				Y.setBooleanVariable(row, column,
						BooleanVariable.getBooleanVariable(
								Y.getName() + "_" + row + "_" + column));
				pfix.add(
						new BitFixer(Y.getBooleanVariable(row, column), value));
			}
		IBitTable Z = new BitTable("Z", new IBooleanVariable[3][4]);
		for (int row = 0; row < X.numberRows(); row++)
			for (int column = 0; column < X.numberColumns(); column++)
				Z.setBooleanVariable(row, column,
						BooleanVariable.getBooleanVariable(
								Z.getName() + "_" + row + "_" + column));

		IProblem fix = new Conjunction(pfix);
		IProblem bta = new BitTableOrer(X, Y, Z);
		IProblem problem = new Conjunction(fix, bta);
		System.out.println(problem);
		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("X= \n" + X);
			System.out.println("Y= \n" + Y);
			System.out.println("Z= \n" + Z);
		} else
			System.out.println("No solution.");
	}
}