package naturalnumberlists;

import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;

public class ConditionalAdder extends naturalnumbers.ConditionalAdder
{
	private static final long serialVersionUID = 1910988458043974940L;

	public ConditionalAdder(INaturalNumberList numbersArray,
			IBitString membership, INaturalNumber conditionalSum)
			throws Exception
	{
		super(numbersArray.getNaturalNumberArray(), membership.getBVArray(),
				conditionalSum);
	}

	public ConditionalAdder(INaturalNumberList numbersArray,
			IBooleanVariable[] membership, INaturalNumber conditionalSum)
			throws Exception
	{
		super(numbersArray.getNaturalNumberArray(), membership, conditionalSum);
	}
}