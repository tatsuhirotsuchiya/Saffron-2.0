/*
 * BitEqualityIndicator.java	1.2 05/04/13
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on three
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p=new BitEqualityIndicator(x,y,z);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>z == (x==y)</code>
 * </p>
 *
 * @author Kerry Michael Soileau ksoileau2@yahoo.com
 *         http://kerrysoileau.com/index.html
 * @version 1.2, 05/04/13
 * @see IBooleanVariable
 * @see IClause
 * @see Problem
 */
public class BitEqualityIndicator extends BitXnorer implements IProblem
{
	public BitEqualityIndicator(IBooleanVariable x, IBooleanVariable y,
			IBooleanVariable z) throws Exception
	{
		super(x, y, z);
	}
}
