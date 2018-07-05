/*
 * BitStringNoter.java	1.0 05/04/01
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitNoter;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringNoter extends Problem implements IProblem
{
	private static final long serialVersionUID = 6958366653731215463L;

	public BitStringNoter(IBitString X, IBitString Y) throws Exception
	{
		IProblem[] p = new IProblem[X.size()];
		int count = 0;
		for (int i = 0; i < p.length; i++)
			p[count++] = new BitNoter(X.getBooleanVariable(i),
					Y.getBooleanVariable(i));
		this.setClauses(new Conjunction(p).getClauses());
	}
}