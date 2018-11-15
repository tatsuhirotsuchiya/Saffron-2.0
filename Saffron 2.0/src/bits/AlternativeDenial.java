/*
 * AlternativeDenial.java	1.0 07/08/20
 *
 * Copyright 2007 Positronic Software.
 *
 *
 */

package bits;

import java.util.List;

/**
 * An extension of the Problem class which expresses the alternative denial
 * (also called the NAND or Sheffer stroke) of several given IProblems. More
 * specifically, the IProblem p defined by
 *
 * <p>
 * <tt>IProblem p=new AlternativeDenial(first,second,third);</tt>
 * </p>
 *
 * is satisfied by an ICertificate c if and only if at least one of
 * <tt>first</tt>, <tt>second</tt> or <tt>third</tt> is not satisfied by
 * <tt>c</tt>.
 * 
 * It is the logical opposite of a Conjunction.
 *
 * @author Kerry Michael Soileau ksoileau2@yahoo.com
 *         http://kerrysoileau.com/index.html
 * @version 1.0, 07/08/20
 * @see BooleanLiteralException
 * @see IClause
 * @see IProblem
 * @see Problem
 */
public class AlternativeDenial extends Problem implements IProblem
{
	public AlternativeDenial(IProblem first, IProblem second) throws Exception
	{
		this(new IProblem[]
		{ first, second });
	}

	public AlternativeDenial(IProblem first, IProblem second, IProblem third)
			throws Exception
	{
		this(new IProblem[]
		{ first, second, third });
	}

	public AlternativeDenial(IProblem[] array) throws Exception
	{
		this.setClauses(new ProblemDenier(new Conjunction(array)).getClauses());
	}

	public AlternativeDenial(List<IProblem> list) throws Exception
	{
		if (list == null || list.size() == 0)
			throw new AlternativeDenialException(
					"Null IProblem was passed to constructor.");
		else
			this.setClauses(new AlternativeDenial(list.toArray(new IProblem[0]))
					.getClauses());
	}
}