/*
 * Conjunction.java	1.2 05/04/08
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */
package bits;

import java.util.ArrayList;

public class Conjunction extends Problem implements IProblem
{
	/**
	 * This and() method scrubs out null IClauses and duplicate IClauses within
	 * an IProblem. The other and() methods ultimately call this one, and thus
	 * all of the and() methods perform this scrubbing function.
	 * 
	 * @throws Exception
	 */
	private static IProblem and(IProblem[] p) throws Exception
	{
		if (p == null)
			return null;
		IProblem res = null;
		for (int i = 0; i < p.length; i++)
			if (p[i] != null)
			{
				if (res == null)
					res = Problem.newProblem();
				for (int j = 0; j < p[i].numberOfClauses(); j++)
				{
					IClause c = p[i].getClause(j);
					if (c != null && !res.contains(c))
						res.addClauseVoid(c);
				}
			}
		return res;
	}

	public Conjunction(ArrayList<IProblem> problemList) throws Exception
	{
		this(problemList.toArray(new IProblem[0]));
	}

	public Conjunction(IProblem problem) throws Exception
	{
		super.setClauses(problem.getClauses());
	}

	public Conjunction(IProblem p1, IProblem p2) throws Exception
	{
		this(new IProblem[]
		{ p1, p2 });
	}

	public Conjunction(IProblem p1, IProblem p2, IProblem p3) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3 });
	}

	public Conjunction(IProblem p1, IProblem p2, IProblem p3, IProblem p4)
			throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3, p4 });
	}

	public Conjunction(IProblem[] group) throws Exception
	{
		IProblem p = Conjunction.and(group);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	/*
	 * public Conjunction(List<IProblem> problemList) throws Exception {
	 * IProblem[] o = (problemList.toArray(new IProblem[0])); IProblem[] p = new
	 * IProblem[o.length]; for (int i = 0; i < o.length; i++) { IProblem ip =
	 * o[i]; p[i] = ip; } this.setClauses(new Conjunction(p).getClauses()); }
	 */
}