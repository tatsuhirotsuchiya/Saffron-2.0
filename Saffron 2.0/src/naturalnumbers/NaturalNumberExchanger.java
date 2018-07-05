package naturalnumbers;

/**
 * <p>Title: NaturalNumberExchanger</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2009</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberExchanger extends Problem implements IProblem
{
	private static final long serialVersionUID = 1911777771045625266L;

	public NaturalNumberExchanger(INaturalNumber xBefore,
			INaturalNumber yBefore, INaturalNumber xAfter, INaturalNumber yAfter)
			throws Exception
	{
		IProblem p = new Conjunction(
				new NaturalNumberEqualizer(xBefore, yAfter),
				new NaturalNumberEqualizer(yBefore, xAfter));

		this.setClauses(p.getClauses());
	}
}