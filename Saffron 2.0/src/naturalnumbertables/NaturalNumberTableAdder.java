/*
 * NaturalNumberTableAdder.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package naturalnumbertables;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberAdder;

public class NaturalNumberTableAdder extends Problem implements IProblem
{
	private static final long serialVersionUID = 4811569546486346565L;

	public NaturalNumberTableAdder(INaturalNumberTable X, INaturalNumberTable Y,
			INaturalNumberTable Z) throws Exception
	{
		if (!X.isSameSizeAs(Y) || !X.isSameSizeAs(Z))
			throw new NaturalNumberTableAdderException(
					"INaturalNumberTables of differing sizes were passed to a constructor.");
		else
		{
			IProblem[] p = new IProblem[X.getNumberOfRows()
					* X.getNumberOfColumns()];
			int count = 0;
			for (int i = 0; i < X.getNumberOfRows(); i++)
				for (int j = 0; j < X.getNumberOfColumns(); j++)
					p[count++] = new NaturalNumberAdder(
							X.getNaturalNumber(i, j), Y.getNaturalNumber(i, j),
							Z.getNaturalNumber(i, j));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}