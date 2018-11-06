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
		// IProblem-0
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

		// IProblem-1
		partition = new IBooleanVariable[3][1];
		for (int i = 0; i < 3; i++)
		{
			partition[i] = new IBooleanVariable[1];
			for (int j = 0; j < 1; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < 3; i++)
			{
				String str = "";
				for (int j = 0; j < 1; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");

		// IProblem-2
		partition = new IBooleanVariable[1][4];
		for (int i = 0; i < 1; i++)
		{
			partition[i] = new IBooleanVariable[4];
			for (int j = 0; j < 4; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < 1; i++)
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

		// IProblem-3
		partition = new IBooleanVariable[1][0];
		for (int i = 0; i < 1; i++)
		{
			partition[i] = new IBooleanVariable[0];
			for (int j = 0; j < 0; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < 1; i++)
			{
				String str = "";
				for (int j = 0; j < 0; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");

		// IProblem-4
		partition = new IBooleanVariable[0][1];
		for (int i = 0; i < 0; i++)
		{
			partition[i] = new IBooleanVariable[1];
			for (int j = 0; j < 1; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i
						+ "-" + j);
		}
		problem = new BitArrayPartition(partition);
		System.out.println(problem);
		s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < 0; i++)
			{
				String str = "";
				for (int j = 0; j < 1; j++)
					str += partition[i][j].getValue() ? "1" : "0";
				System.out.println(str);
			}
			BooleanLiteral.reset(s);
		}
		else
			System.out.println("No solution.");
	}
}
