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
 *         </pre>
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
	public BitExclusiveSelector(ArrayList<IBooleanVariable> bitArrayList)
			throws Exception
	{
		this(bitArrayList.toArray(new IBooleanVariable[0]));
	}

	public BitExclusiveSelector(IBooleanVariable x, IBooleanVariable y)
			throws Exception
	{
		this(new IBooleanVariable[]
		{ x, y });
	}

	public BitExclusiveSelector(IBooleanVariable x, IBooleanVariable y,
			IBooleanVariable z) throws Exception
	{
		this(new IBooleanVariable[]
		{ x, y, z });
	}

	public BitExclusiveSelector(IBooleanVariable[] bitArrayList)
			throws Exception
	{
		IProblem problem;
		if (bitArrayList == null || bitArrayList.length == 0)
			throw new BitExclusiveSelectorException(
					"Null or empty partition passed to constructor.");
		if (bitArrayList.length == 1)
			problem = new BitFixer(bitArrayList[0], true);
		else
		{
			int listSize = bitArrayList.length;
			BooleanLiteral.getBooleanLiteral(bitArrayList[0], false);
			problem = Problem.newProblem();
			IClause build1 = Clause.newClause();
			for (IBooleanVariable curr : bitArrayList)
				build1.add((BooleanLiteral) BooleanLiteral
						.getBooleanLiteral(curr, false));
			problem.addClause(build1);

			for (int i = 0; i < listSize; i++)
			{
				BooleanLiteral curr = (BooleanLiteral) BooleanLiteral
						.getBooleanLiteral(bitArrayList[i], true);
				for (int j = i + 1; j < listSize; j++)
				{
					IClause build2 = Clause.newClause();
					build2.add(curr);
					build2.add((BooleanLiteral) BooleanLiteral
							.getBooleanLiteral(bitArrayList[j], true));
					problem.addClause(build2);
				}
			}
		}
		this.setClauses(problem.getClauses());
	}
}