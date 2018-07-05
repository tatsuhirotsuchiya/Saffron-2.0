package naturalnumberlists;

import naturalnumbers.NaturalNumberEqualizer;
import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberListExchanger extends Problem implements IProblem
{
	private static final long serialVersionUID = -4801677486414297194L;

	public NaturalNumberListExchanger(INaturalNumberList A,
			INaturalNumberList B, int m, int n) throws Exception
	{
		if (A == null)
			throw new NaturalNumberListException(
					"Passed a null INaturalNumberList to constructor.");
		if (B == null)
			throw new NaturalNumberListException(
					"Passed a null INaturalNumberList to constructor.");

		IProblem problem = null;
		for (int i = 0; i < A.size(); i++)
			if (i != m && i != n)
				problem = new Conjunction(problem, new NaturalNumberEqualizer(
						A.getNaturalNumber(i), B.getNaturalNumber(i)));
		problem = new Conjunction(problem, new NaturalNumberEqualizer(
				A.getNaturalNumber(m), B.getNaturalNumber(n)));
		problem = new Conjunction(problem, new NaturalNumberEqualizer(
				A.getNaturalNumber(n), B.getNaturalNumber(m)));

		this.setClauses(problem.getClauses());
	}
}
