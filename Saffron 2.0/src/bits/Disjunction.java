/**
 * Disjunction.java
 * 
 * An extension of the Problem class which creates a new IProblem object from a
 * collection of IProblem objects. Given several IProblems p_1, p_2, ..., p_n,
 * to satisfy the IProblem given by new Disjunction(new IProblem[]{p_1, p_2,
 * ..., p_n}) is to satisfy at least one of the p_1, p_2, ..., p_n.
 *
 * To use this class, one passes several IProblem objects (or an IProblem[]
 * array) to the appropriate constructor. The object produced is an IProblem,
 * and one may manipulate it using any of the methods provided by the Problem
 * class.
 *
 * For example, when the IProblem instance p defined by <blockquote>
 * 
 * <pre>
 * IProblem p = new Disjunction(new IProblem[]
 * { p_1, p_2, p_3 });
 * </pre>
 * 
 * is satisfied, the following truth equation will be satisfied: <blockquote>
 * 
 * <pre>
 * p_1 is satisfied
 * or
 * p_2 is satisfied
 * or
 * p_3 is satisfied.
 * </pre>
 * 
 * </blockquote>
 * 
 * @author Kerry Michael Soileau ksoileau2@yahoo.com
 *         http://kerrysoileau.com/index.html
 * @version 1.21, 2007/08/22
 * @see IProblem
 * @see Problem
 */

package bits;

import java.util.ArrayList;
import java.util.List;

public class Disjunction extends Problem implements IProblem
{
	/**
	 * This or() method takes two Problems <code>ptrue</code> and
	 * <code>pfalse</code> and creates a new Problem, let's call it
	 * <code>p</code>. If <code>b</code> is true, <code>p</code> is equivalent
	 * to the Problem <code>ptrue</code>. Likewise, if <code>b</code> is false,
	 * <code>p</code> is equivalent to the Problem <code>pfalse</code>.
	 * 
	 * @throws Exception
	 */
	private static IProblem or(IProblem ptrue, IProblem pfalse,
			IBooleanVariable b) throws Exception
	{
		if (pfalse == null)
			return Disjunction.or(new IProblem[]
			{ ptrue }, new IBooleanVariable[]
			{ b });
		if (ptrue == null)
			return Disjunction.or(new IProblem[]
			{ pfalse }, new IBooleanVariable[]
			{ b });

		List<IClause> clfalse = pfalse.getClauses();
		ArrayList<IClause> clfalseNew = new ArrayList<IClause>();
		for (int i = 0; i < clfalse.size(); i++)
		{
			IClause q0 = clfalse.get(i);
			IClause q1 = (IClause) q0.clone();
			IClause q2 = q1.or(b);
			clfalseNew.add(q2);
		}

		List<IClause> cltrue = ptrue.getClauses();
		ArrayList<IClause> cltrueNew = new ArrayList<IClause>();
		for (int i = 0; i < cltrue.size(); i++)
		{
			IClause q0 = cltrue.get(i);
			IClause q1 = (IClause) q0.clone();
			IClause q2 = q1.orNot(b);
			cltrueNew.add(q2);
		}

		return new Conjunction(new Problem(clfalseNew), new Problem(cltrueNew));
	}

	/**
	 *
	 * @return If problemArray={p_0,p_1,p_2,...,p_{n-1}} then returns an
	 *         IProblem satisfied if and only if at least one of the following
	 *         IProblems is satisfied:
	 * 
	 *         <pre>
	 * p_0
	 * p_1
	 * p_2
	 * .
	 * .
	 * .
	 * p_{n-1}
	 *         </pre>
	 * 
	 * @throws Exception
	 */
	private static IProblem or(IProblem[] problemArray) throws Exception
	{
		if (problemArray == null)
			return null;
		int n = problemArray.length;
		if (n == 0)
			return null;
		if (n == 1)
			return problemArray[0];
		if (n > 1)
		{
			IBooleanVariable[] b = new IBooleanVariable[n - 1];
			for (int i = 0; i < b.length; i++)
				b[i] = BooleanVariable.getBooleanVariable();
			return Disjunction.or(problemArray, b);
		}
		return null;
	}

	/**
	 * If
	 * 
	 * <pre>
	 * p={p_0,p_1,p_2,...,p_{n-1}}
	 * and
	 * b={x_0,x_1,x_2,...,x_{n-2}}
	 * </pre>
	 * 
	 * then
	 * 
	 * @return {r_0,r_1,r_2,...,r_{n-2}} where
	 * 
	 *         <pre>
	 * r_0=Disjunction.or(p_0,r_1,x_0)
	 * r_1=Disjunction.or(p_1,r_2,x_1) 
	 * r_2=Disjunction.or(p_2,r_3,x_2) 
	 * .
	 * .
	 * .
	 * r_{n-3}=Disjunction.or(p_{n-3},r_{n-2},x_{n-3})
	 * r_{n-2}=Disjunction.or(p_{n-2},p_{n-1},x_{n-2})
	 *         </pre>
	 * 
	 *         In effect, for the first <code>i</code> such that
	 *         <code>x_i</code> is true, <code>p_i</code> is returned. If no
	 *         such <code>i</code> exists, <code>p_{n-1}</code> is returned.
	 * @throws Exception
	 */
	private static IProblem or(IProblem[] problemArray,
			IBooleanVariable[] booleanVariableArray) throws Exception
	{
		if (problemArray == null || booleanVariableArray == null)
			return null;
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			return null;
		if (numberOfProblems == 1)
			return problemArray[0];
		if (numberOfProblems != booleanVariableArray.length + 1)
			return null;
		if (numberOfProblems > 1)
		{
			IProblem[] r = new IProblem[numberOfProblems - 1];
			r[numberOfProblems - 2] = Disjunction.or(
					problemArray[numberOfProblems - 2],
					problemArray[numberOfProblems - 1],
					booleanVariableArray[numberOfProblems - 2]);
			for (int i = numberOfProblems - 3; i >= 0; i--)
				r[i] = Disjunction.or(problemArray[i], r[i + 1],
						booleanVariableArray[i]);
			return r[0];
		}
		return null;
	}

	/**
	 *
	 * @return an IProblem equivalent to the IProblem p.
	 * @throws Exception
	 */
	public Disjunction(IProblem p) throws Exception
	{
		this(new IProblem[]
		{ p });
	}

	/**
	 *
	 * @return an IProblem satisfied if and only if at least one of the
	 *         following IProblems is satisfied:
	 * 
	 *         <pre>
	 * p_1
	 * p_2
	 *         </pre>
	 * 
	 * @throws Exception
	 */
	public Disjunction(IProblem p1, IProblem p2) throws Exception
	{
		this(new IProblem[]
		{ p1, p2 });
	}

	public Disjunction(IProblem ptrue, IProblem pfalse, IBooleanVariable b)
			throws Exception
	{
		IProblem p = Disjunction.or(ptrue, pfalse, b);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	/**
	 *
	 * @return an IProblem satisfied if and only if at least one of the
	 *         following IProblems is satisfied:
	 * 
	 *         <pre>
	 * p_1
	 * p_2
	 * p_3
	 *         </pre>
	 * 
	 * @throws Exception
	 */
	public Disjunction(IProblem p1, IProblem p2, IProblem p3) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3 });
	}

	/**
	 *
	 * @return an IProblem satisfied if and only if at least one of the
	 *         following IProblems is satisfied:
	 * 
	 *         <pre>
	 * p_1
	 * p_2
	 * p_3
	 * p_4
	 *         </pre>
	 * 
	 * @throws Exception
	 */
	public Disjunction(IProblem p1, IProblem p2, IProblem p3, IProblem p4)
			throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3, p4 });
	}

	/**
	 *
	 * @return an IProblem satisfied if and only if at least one of the
	 *         following IProblems is satisfied:
	 * 
	 *         <pre>
	 * problemArray[0]
	 * problemArray[1]
	 * problemArray[2]
	 * .
	 * .
	 * .
	 * problemArray[n-1]
	 * 
	 * where n=problemArray.length.
	 *         </pre>
	 * 
	 * @throws Exception
	 */
	public Disjunction(IProblem[] problemArray) throws Exception
	{
		if (problemArray != null && problemArray.length > 0)
		{
			IProblem p = Disjunction.or(problemArray);
			if (p != null)
				this.setClauses(p.getClauses());
		}
	}

	/**
	 * If
	 * 
	 * <pre>
	 * problemArray={p_0,p_1,p_2,...,p_{n-1}}
	 * and
	 * booleanVariableArray={x_0,x_1,x_2,...,x_{n-2}}
	 * </pre>
	 * 
	 * then
	 * 
	 * @return {r_0,r_1,r_2,...,r_{n-2}} where
	 * 
	 *         <pre>
	 * r_0=Disjunction.or(p_0,r_1,x_0)
	 * r_1=Disjunction.or(p_1,r_2,x_1) 
	 * r_2=Disjunction.or(p_2,r_3,x_2) 
	 * .
	 * .
	 * .
	 * r_{n-3}=Disjunction.or(p_{n-3},r_{n-2},x_{n-3})
	 * r_{n-2}=Disjunction.or(p_{n-2},p_{n-1},x_{n-2})
	 *         </pre>
	 * 
	 *         In effect, for the first <code>i</code> such that
	 *         <code>x_i</code> is true, <code>p_i</code> is returned. If no
	 *         such <code>i</code> exists, <code>p_{n-1}</code> is returned.
	 * @throws Exception
	 */
	public Disjunction(IProblem[] problemArray,
			IBooleanVariable[] booleanVariableArray) throws Exception
	{
		if (problemArray != null && problemArray.length > 0)
			if (booleanVariableArray != null && booleanVariableArray.length > 0)
			{
				IProblem p = Disjunction.or(problemArray, booleanVariableArray);
				if (p != null)
					this.setClauses(p.getClauses());
			}
	}
}