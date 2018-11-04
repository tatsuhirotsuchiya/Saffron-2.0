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
 * new BitFixer(booleanVariableArray[n-1], true) is satisfied and  problemArray[n-1] is satisfied
 * 
 * where n=the length of problemArray.
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

public class ConditionalDisjunction extends Problem implements IProblem
{
	private static final long serialVersionUID = -6526207407161439199L;

	public ConditionalDisjunction(IProblem[] problemArray,
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
			subProblems[i] = new Conjunction(new BitFixer(
					booleanVariableArray[i], true), problemArray[i]);

		this.setClauses(new Disjunction(subProblems).getClauses());
	}
}