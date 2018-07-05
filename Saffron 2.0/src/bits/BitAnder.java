/*
 * BitAnder.java	1.2 05/02/09
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on three
 * IBooleanVariables. For example, the IProblem p defined by
 *
 * <p>
 * <tt>IProblem p=new BitAnder(x,y,z);</tt>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>z == x & y </tt>
 * </p>
 *
 * @author Kerry Michael Soileau <blockquote>
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre>
 * 
 *         </blockquote>
 * @version 1.2, 05/02/09
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see Problem
 */
public class BitAnder extends Problem implements IProblem
{
	private static final long serialVersionUID = 9121514662499786580L;

	public BitAnder(IBooleanVariable x, IBooleanVariable y, IBooleanVariable z)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y).orNot(z),
				Clause.newClause().or(x).orNot(y).orNot(z),
				Clause.newClause().orNot(x).or(y).orNot(z),
				Clause.newClause().orNot(x).orNot(y).or(z) });
	}
}