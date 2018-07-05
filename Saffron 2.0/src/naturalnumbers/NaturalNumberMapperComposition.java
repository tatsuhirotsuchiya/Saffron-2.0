package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * <p>
 * Title: NaturalNumberMappingComposition
 * </p>
 * <p>
 * Description: TBS
 * </p>
 * <p>
 * Copyright (c) 2005
 * </p>
 * <p>
 * Company: Positronic Software
 * </p>
 * 
 * @author Kerry Michael Soileau
 * @version 1.0
 */

public class NaturalNumberMapperComposition extends Problem implements IProblem
{
	private static final long serialVersionUID = 4831629236073609407L;

	public NaturalNumberMapperComposition(NaturalNumberMapper first,
			NaturalNumberMapper second, INaturalNumber X, INaturalNumber Y)
			throws Exception
	{
		IProblem prob = new Conjunction(new Problem[]
		{
				new NaturalNumberEqualizer(X, first.getDomainVariable()),
				first,
				new NaturalNumberEqualizer(first.getRangeVariable(),
						second.getDomainVariable()), second,
				new NaturalNumberEqualizer(second.getRangeVariable(), Y), });
		this.setClauses(prob.getClauses());
	}
}