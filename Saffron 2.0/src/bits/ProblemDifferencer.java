/*
 * ProblemDifferencer.java	1.0 05/10/18
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package bits;

import bits.exceptions.BooleanLiteralException;
import bits.exceptions.ProblemDifferencerException;

/**
 * An extension of the Problem class which expresses the logical difference of
 * two given IProblems. More specifically, the IProblem p defined by
 *
 * <p>
 * <tt>IProblem p=new ProblemDifferencer(first,second);</tt>
 * </p>
 *
 * is satisfied if and only if the IProblem first is satisfied and the IProblem
 * second is not satisfied.
 *
 * @author Kerry Michael Soileau ksoileau2@yahoo.com
 *         http://kerrysoileau.com/index.html
 * @version 1.0, 05/10/18
 * @see BooleanLiteralException
 * @see IClause
 * @see IProblem
 * @see Problem
 */
public class ProblemDifferencer extends Problem implements IProblem
{
	public ProblemDifferencer(IProblem first, IProblem second) throws Exception
	{
		if (first == null || second == null)
			throw new ProblemDifferencerException(
					"A null IProblem was passed to constructor.");

		IProblem denial = new ProblemDenier(second);
		IProblem res = new Conjunction(first, denial);
		this.setClauses(res.getClauses());
	}
}