/**
 * 
 * @param problemArray
 * @param booleanVariableArray
 * @return an IProblem that is satisfied if and only if all of the
 *         following IProblems are satisfied:
 * 
 *         <pre>
 * new BitFixer(booleanVariableArray[0], true) and problemArray[0] are both satisfied, 
 * or new BitFixer(booleanVariableArray[0], false) is satisfied
 * and
 * new BitFixer(booleanVariableArray[1], true) and problemArray[1] are both satisfied, 
 * or new BitFixer(booleanVariableArray[1], false) is satisfied
 * .
 * .
 * .
 * new BitFixer(booleanVariableArray[n-1], true) and problemArray[n-1] are both satisfied, 
 * or new BitFixer(booleanVariableArray[n-1], false) is satisfied
 * 
 * where n=the length of problemArray. Roughly speaking, the bits corresponding to booleanVariableArray
 * determine which members of problemArray go into the Conjunction.
 * </pre>
 * @throws Exception
 *
 * 
 * @author Kerry Michael Soileau ksoileau2@yahoo.com
 *         http://kerrysoileau.com/index.html
 * @version 1.0, 2018/11/04
 * @see Disjunction
 */

package bits;

public class ConditionalConjunction extends Problem implements IProblem
{
	private static final long serialVersionUID = -5938385940059737551L;

	public ConditionalConjunction(IProblem[] problemArray,
			IBooleanVariable[] booleanVariableArray) throws Exception
	{
		if (problemArray == null || booleanVariableArray == null)
			return;
		int numberOfProblems = problemArray.length;
		if (numberOfProblems == 0)
			return;
		if (numberOfProblems == 1)
			this.setClauses(problemArray[0].getClauses());
		if (numberOfProblems != booleanVariableArray.length)
			return;

		IProblem[] subProblems = new IProblem[numberOfProblems];
		for (int i = 0; i < numberOfProblems; i++)
			subProblems[i] = new Disjunction(problemArray[i],
					Problem.trivialProblem(), booleanVariableArray[i]);

		this.setClauses(new Conjunction(subProblems).getClauses());
	}
}