package naturalnumbers;

/**
 * <p>Title: NaturalNumberBitMultiply</p>
 * <p>Description: TBS</p>
 * <p>Copyright (c) 2005</p>
 * <p>Company: Positronic Software</p>
 * @author Kerry Michael Soileau
 * @version 1.0
 */
import bits.BitAnder;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class NaturalNumberBitMultiply extends Problem implements IProblem
{
	private static final long serialVersionUID = -5359923444434478567L;

	public NaturalNumberBitMultiply(IBooleanVariable b, INaturalNumber X,
			INaturalNumber Y) throws Exception
	{
		BitAnder[] ba = new BitAnder[NaturalNumber.getLength()];
		for (int i = 0; i < NaturalNumber.getLength(); i++)
			ba[i] = new BitAnder(b, X.getBooleanVariable(i),
					Y.getBooleanVariable(i));
		IProblem p = new Conjunction(ba);

		this.setClauses(p.getClauses());
	}

	public NaturalNumberBitMultiply(IBooleanVariable[] b, INaturalNumber[] X,
			INaturalNumber[] Y) throws Exception
	{
		IProblem p = null;
		for (int j = 0; j < X.length; j++)
			for (int i = 0; i < NaturalNumber.getLength(); i++)
				p = new Conjunction(p, new NaturalNumberBitMultiply(b[j], X[j],
						Y[j]));

		this.setClauses(p.getClauses());
	}

	public NaturalNumberBitMultiply(IBooleanVariable[] b, INaturalNumberList X,
			INaturalNumberList Y) throws Exception
	{
		IProblem p = null;
		for (int j = 0; j < X.size(); j++)
			for (int i = 0; i < NaturalNumber.getLength(); i++)
				p = new Conjunction(p, new NaturalNumberBitMultiply(b[j],
						X.getNaturalNumber(j), Y.getNaturalNumber(j)));

		this.setClauses(p.getClauses());
	}
}