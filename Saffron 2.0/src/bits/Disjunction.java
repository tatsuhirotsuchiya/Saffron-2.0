package bits;

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
public class Disjunction extends Problem implements IProblem
{
	private static final long serialVersionUID = -8401293639054850015L;

	/**
	 * 
	 * @param problemArray
	 * @param booleanVariableArray
	 * @return an IProblem that is satisfied if and only if at least one of the
	 *         following IProblems is satisfied:
	 * 
	 *         <pre>
	 * new BitFixer(booleanVariableArray[0], true) is satisfied and  problemArray[0] is satisfied
	 * new BitFixer(booleanVariableArray[1], true) is satisfied and  problemArray[1] is satisfied
	 * new BitFixer(booleanVariableArray[2], true) is satisfied and  problemArray[2] is satisfied
	 * .
	 * .
	 * .
	 * new BitFixer(booleanVariableArray[n-1], true) is satisfied and  problemArray[n-] is satisfied
	 * 
	 * where n=the length of problemArray.
	 * </pre>
	 * @throws Exception
	 */
	public static IProblem inner(IProblem[] problemArray,
			IBooleanVariable[] booleanVariableArray) throws Exception
	{
		if (problemArray == null || booleanVariableArray == null)
			return null;
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			return null;
		if (numberOfProblems == 1)
			return problemArray[0];
		if (numberOfProblems != booleanVariableArray.length)
			return null;

		IProblem[] subProblems = new IProblem[numberOfProblems];
		for (int i = 0; i < numberOfProblems; i++)
			subProblems[i] = new Conjunction(new BitFixer(
					booleanVariableArray[i], true), problemArray[i]);

		return new Disjunction(subProblems);
	}

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

		Object[] clfalse = pfalse.getClauses();
		IClause[] clfalseNew = new IClause[clfalse.length];
		for (int i = 0; i < clfalse.length; i++)
			clfalseNew[i] = ((IClause) (((IClause) clfalse[i]).clone())).or(b);

		Object[] cltrue = ptrue.getClauses();
		IClause[] cltrueNew = new IClause[cltrue.length];
		for (int i = 0; i < cltrue.length; i++)
			cltrueNew[i] = ((IClause) (((IClause) cltrue[i]).clone())).orNot(b);

		return new Conjunction(new Problem(clfalseNew), new Problem(cltrueNew));
	}

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
	 * </pre>
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

	public Disjunction(IProblem p) throws Exception
	{
		this.setClauses(p.getClauses());
	}

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

	public Disjunction(IProblem p1, IProblem p2, IProblem p3) throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3 });
	}

	public Disjunction(IProblem p1, IProblem p2, IProblem p3, IProblem p4)
			throws Exception
	{
		this(new IProblem[]
		{ p1, p2, p3, p4 });
	}

	public Disjunction(IProblem[] problemArray) throws Exception
	{
		IProblem p = Disjunction.or(problemArray);
		if (p != null)
			this.setClauses(p.getClauses());
	}

	public Disjunction(IProblem[] problemArray,
			IBooleanVariable[] booleanVariableArray) throws Exception
	{
		IProblem p = Disjunction.or(problemArray, booleanVariableArray);
		if (p != null)
			this.setClauses(p.getClauses());
	}
}