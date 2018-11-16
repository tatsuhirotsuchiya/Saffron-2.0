package bitstrings;

import bits.Conjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;

public class BitStringExchanger extends Problem implements IProblem
{
	public BitStringExchanger(IBitString xBefore, IBitString yBefore,
			IBitString xAfter, IBitString yAfter) throws Exception
	{
		IProblem p = new Conjunction(new BitStringEqualizer(xBefore, yAfter),
				new BitStringEqualizer(yBefore, xAfter));
		this.setClauses(p.getClauses());
	}
}
