/*
 * BitShefferStroker.java 1.0 2015/03/18
 *
 * Copyright 2015 Positronic Software.
 *
 *
 */

package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on two
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p=new BitShefferStroker(x,y);</tt>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>!x | !y</tt>
 * </p>
 *
 *
 * @author Kerry Michael Soileau ksoileau2@yahoo.com
 *         http://kerrysoileau.com/index.html
 * @version 1.0, 2015/03/18
 * @see IBooleanVariable
 * @see IClause
 * @see Problem
 */
public class BitShefferStroker extends Problem implements IProblem
{
	public BitShefferStroker(IBooleanVariable x, IBooleanVariable y)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().orNot(x).orNot(y) });
	}
}