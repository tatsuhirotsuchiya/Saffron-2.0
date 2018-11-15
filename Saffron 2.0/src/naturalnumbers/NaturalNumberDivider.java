package naturalnumbers;

import bits.IBooleanVariable;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * An extension of the Problem class which implements an divider of two
 * NaturalNumbers.
 *
 * In one way to use this class, one passes INaturalNumber X, INaturalNumber Y,
 * INaturalNumber Z to the appropriate constructor. The NaturalNumberDivider
 * object produced is a Problem, and one may manipulate it using any of the
 * methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <tt>Problem p = new NaturalNumberDivider(X,Y,Z);</tt>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <tt>X == Z * Y</tt>
 * </p>
 * .
 *
 * @author Kerry Michael Soileau <blockquote>
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 *         </pre>
 * 
 *         </blockquote>
 * @version 1.0, 06/02/08
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see Problem
 */

public class NaturalNumberDivider extends Problem implements IProblem
{
	private static final long serialVersionUID = -1349206429277593088L;

	public NaturalNumberDivider(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		this.setClauses(new NaturalNumberMultiplier(Z, Y, X).getClauses());
	}
}