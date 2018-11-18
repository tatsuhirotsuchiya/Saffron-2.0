package bits;

/**
 * An extension of the Problem class which imposes a Boolean relation on three
 * IBooleanVariables. For example, the Problem instance p defined by
 *
 * <p><tt>Problem p=new BitOrer(x,y,z);</code></p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p><tt>z == ( x | y )</code></p>
 *
 * @author  Kerry Michael Soileau
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * @version 1.11, 04/11/24
 * Copyright 2004-2005 Positronic Software.
 */

public class BitOrer extends Problem implements IProblem
{
	public BitOrer(IBooleanVariable x, IBooleanVariable y, IBooleanVariable z)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y).orNot(z),
				Clause.newClause().orNot(y).or(z),
				Clause.newClause().orNot(x).or(z) });
	}
}
