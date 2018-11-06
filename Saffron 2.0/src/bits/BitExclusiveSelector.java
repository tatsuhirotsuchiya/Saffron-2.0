/*
 * BitExclusiveSelector.java 1.0 2015/03/18
 *
 * Copyright 2015 Positronic Software.
 *
 *
 */

package bits;

import java.util.ArrayList;

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
			int siz = bitArrayList.size();
			BooleanLiteral.getBooleanLiteral(bitArrayList.get(0), false);
			problem = Problem.newProblem();
			IClause build1 = Clause.newClause();
			for (IBooleanVariable curr : bitArrayList)
				build1.add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(
						curr, false));
			problem.addClause(build1);

			for (int i = 0; i < siz; i++)
			{
				BooleanLiteral q = (BooleanLiteral) BooleanLiteral
						.getBooleanLiteral(bitArrayList.get(i), true);
				for (int j = i + 1; j < siz; j++)
				{
					IClause build2 = Clause.newClause();
					build2.add(q);
					build2.add((BooleanLiteral) BooleanLiteral
							.getBooleanLiteral(bitArrayList.get(j), true));
					problem.addClause(build2);
				}
			}
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