package bitstringlists;

import bits.Conjunction;
import bits.IProblem;
import bits.Problem;

public class BitStringListPopulator extends Problem implements IProblem
{
	private static final long serialVersionUID = -388391718064931108L;

	public BitStringListPopulator(IBitStringList source, IBitStringList target)
			throws Exception
	{
		if (source == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");
		if (target == null)
			throw new BitStringListException(
					"Passed a null IBitStringList to constructor.");

		IProblem res[] = new IProblem[source.size()];
		for (int i = 0; i < source.size(); i++)
			res[i] = new BitStringListMembershipper(source.getBitString(i),
					target);
		this.setClauses(new Conjunction(res).getClauses());
	}
}
