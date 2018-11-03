/*
 * BitExclusiveSelector.java 1.0 2015/03/18
 *
 * Copyright 2015 Positronic Software.
 *
 *
 */

package bits;

import java.util.ArrayList;
import java.util.List;

/**
 * An extension of the Problem class which imposes a Boolean relation on three
 * IBooleanVariables. For example, the IProblem p defined by
 *
 * <p>
 * <tt>IProblem p=new BitExclusiveSelector(x,y,z);</tt>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * <tt>Exactly one of x, y and z is true, the other two are false.</tt>
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
 * @version 1.0, 2015/03/18
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see Problem
 */
public class BitExclusiveSelector extends Problem implements IProblem
{
	private static final long serialVersionUID = 2978894161821848384L;

	public BitExclusiveSelector(ArrayList<IBooleanVariable> bitArrayList)
			throws Exception
	{
		IProblem problem;
		if (bitArrayList == null || bitArrayList.size() == 0)
			return;
		if (bitArrayList.size() == 1)
			problem = new BitFixer(bitArrayList.get(0), true);
		else
		{
			List<IBooleanVariable> restList = bitArrayList
					.subList(1, bitArrayList.size());
			ArrayList<IBooleanVariable> rest = new ArrayList<IBooleanVariable>(restList);
			problem = new ExclusiveDisjunction(
					new BitFixer(bitArrayList.get(0)),
					(IProblem) new BitExclusiveSelector(rest));
		}
		this.setClauses(problem.getClauses());
	}

	public BitExclusiveSelector(IBooleanVariable x, IBooleanVariable y)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y), Clause.newClause().orNot(x).orNot(y) });
	}

	public BitExclusiveSelector(IBooleanVariable x, IBooleanVariable y,
			IBooleanVariable z) throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(x).or(y).or(z),
				Clause.newClause().or(x).orNot(y).orNot(z),
				Clause.newClause().orNot(x).or(y).orNot(z),
				Clause.newClause().orNot(x).orNot(y) });
	}
}