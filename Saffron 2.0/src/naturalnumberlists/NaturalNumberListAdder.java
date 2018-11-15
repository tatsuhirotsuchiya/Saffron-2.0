package naturalnumberlists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;
import naturalnumbers.NaturalNumberAdder;

/**
 * This performs a "vector" addition of two like-sized INaturalNumberLists. This
 * means that sum.getNaturalNumber(i)=addend1.getNaturalNumber(i)+addend2.
 * getNaturalNumber(i) for each i=0,1,2,...,(sum.size()-1)
 * 
 * @author Kerry Michael Soileau
 */
public class NaturalNumberListAdder extends Problem implements IProblem
{
	private static final long serialVersionUID = 1L;

	public NaturalNumberListAdder(INaturalNumberList addend1,
			INaturalNumberList addend2, INaturalNumberList sum)
			throws NaturalNumberListException, Exception
	{
		if (addend1.size() != addend2.size() || addend1.size() != sum.size())
			throw new NaturalNumberListAdderException(
					"Size mismatch of INaturalNumberList parameters passed to constructor.");
		IProblem[] p = new Problem[addend1.size()];
		for (int i = 0; i < p.length; i++)
			p[i] = new NaturalNumberAdder(addend1.getNaturalNumber(i),
					addend2.getNaturalNumber(i), sum.getNaturalNumber(i));
		this.setClauses(new Conjunction(p).getClauses());
	}
}
