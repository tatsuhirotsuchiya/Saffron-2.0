package demos;

import java.util.List;

import bits.BitArrayPartition;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;

public class BitArrayPartitionDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable[][] partition = new IBooleanVariable[3][4];
		for (int i = 0; i < 3; i++)
		{
			partition[i] = new IBooleanVariable[4];
			for (int j = 0; j < 4; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}

		IProblem problem = new BitArrayPartition(partition);

		System.out.println(problem);

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < 3; i++)
			{
				String str = "";
				for (int j = 0; j < 4; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}
