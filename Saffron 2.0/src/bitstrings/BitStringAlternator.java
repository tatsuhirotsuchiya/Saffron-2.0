/*
 * BitStringAlternater.java	1.0 05/10/21
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitUnequalizer;
import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringAlternator extends Problem implements IProblem
{
	private static final long serialVersionUID = -3564851556876017721L;

	public BitStringAlternator(IBitString X) throws Exception
	{
		IProblem[] p = new IProblem[X.size()];
		for (int i = 0; i < X.size() - 1; i++)
			p[i] = new BitUnequalizer(X.getBooleanVariable(i),
					X.getBooleanVariable(i + 1));

		this.setClauses(new Conjunction(p).getClauses());
	}
}