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

public class NaturalNumberEvener extends Problem implements IProblem
{
	private static final long serialVersionUID = 3224751661986678000L;

	public NaturalNumberEvener(INaturalNumber Z) throws Exception
	{
		INaturalNumber Two = new NaturalNumber();
		INaturalNumber K = new NaturalNumber();

		IProblem p = new Conjunction(new NaturalNumberFixer(Two, 2),
				new NaturalNumberMultiplier(Two, K, Z));

		this.setClauses(p.getClauses());
	}
}