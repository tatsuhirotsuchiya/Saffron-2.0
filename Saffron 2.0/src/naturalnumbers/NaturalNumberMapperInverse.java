package naturalnumbers;

import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

/**
 * <p>
 * Title: NaturalNumberMappingInverse
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

public class NaturalNumberMapperInverse extends Problem implements IProblem
{
	private static final long serialVersionUID = -2890737715683794908L;

	public NaturalNumberMapperInverse(NaturalNumberMapper f, INaturalNumber X,
			INaturalNumber Y) throws Exception
	{
		IProblem prob = new Conjunction(new Problem[]
		{ new NaturalNumberEqualizer(Y, f.getDomainVariable()), f,
				new NaturalNumberEqualizer(f.getRangeVariable(), X) });
		this.setClauses(prob.getClauses());
	}
}