/*
 * BitStringBitFixer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bitstrings;

import bits.BitFixer;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringBitFixer extends Problem implements IProblem
{
	private static final long serialVersionUID = -6141437951897455708L;

	public BitStringBitFixer(IBitString b, int bit, boolean val)
			throws Exception
	{
		if ((bit < 0) || (b.size() - 1 < bit))
			this.setClauses(Problem.unsolvableProblem().getClauses());
		else
			this.setClauses(new BitFixer(b.getBooleanVariable(bit), val)
					.getClauses());
	}
}