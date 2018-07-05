package naturalnumbers;

/**
 * <p>Title: TBS</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

//Satisfied when X is STRICTLY LESS THAN Y.
public class NaturalNumberComparer extends Problem implements IProblem
{
	private static final long serialVersionUID = -3818517171139276188L;

	public NaturalNumberComparer(INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		INaturalNumber Z = new NaturalNumber();
		this.setClauses(new Conjunction(new NaturalNumberAdder(X, Z, Y),
				new NaturalNumberPositiver(Z)).getClauses());
	}
}