/*
 * BitStringXorer.java	1.0 05/04/03
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitXorer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringXorer extends Problem implements IProblem
{
	private static final long serialVersionUID = -469426832560121938L;

	public BitStringXorer(IBitString X, IBitString Y, IBitString Z)
			throws Exception
	{
		if (X.size() != Y.size() || X.size() != Z.size())
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
		{
			IProblem[] p = new IProblem[X.size()];
			int count = 0;
			for (int i = 0; i < X.size(); i++)
				p[count++] = new BitXorer(X.getBooleanVariable(i),
						Y.getBooleanVariable(i), Z.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}