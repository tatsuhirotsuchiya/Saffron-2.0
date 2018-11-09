package naturalnumbers;

import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class ConditionalAdder extends Problem implements IProblem
{
	private static final long serialVersionUID = 766604207120847889L;

	public ConditionalAdder(INaturalNumber[] numbers,
			IBooleanVariable[] membership, INaturalNumber conditionalSum)
			throws Exception
	{
		if (numbers.length == 0 || membership.length == 0)
			throw (new ConditionalAdderException(
					"numbers or bits array of zero length was passed to constructor."));
		if (numbers.length != membership.length)
			throw (new ConditionalAdderException(
					"numbers or bits array of different lengths were passed to constructor."));
		if (conditionalSum == null)
			throw (new ConditionalAdderException(
					"A null conditionalSum variable was passed to constructor."));
		if (conditionalSum.size() == 0)
			throw (new ConditionalAdderException(
					"A conditionalSum variable of size zero was passed to constructor."));

		INaturalNumber[] subAnswer = new INaturalNumber[numbers.length];
		INaturalNumber[] subTotal = new INaturalNumber[numbers.length];
		subTotal[0] = new NaturalNumber();
		subAnswer[0] = new NaturalNumber();
		IProblem[] problemArray = new IProblem[numbers.length];
		problemArray[0]=new Conjunction(new NaturalNumberBitMultiply(membership[0],
				numbers[0], subAnswer[0]), new NaturalNumberEqualizer(
				subTotal[0], subAnswer[0]));
		for (int i = 1; i < numbers.length; i++)
		{
			//System.out.println("\t\tConditionalAdder:"+i);
			subAnswer[i] = new NaturalNumber();
			//System.out.println("h1");
			subTotal[i] = new NaturalNumber();
			//System.out.println("h2");
			//System.out.println(membership[i]);
			//System.out.println(numbers[i]);
			//System.out.println(subAnswer[i]);
			//System.out.println(subTotal[i - 1]);
			//System.out.println(subTotal[i]);
			//System.out.println(problemArray[i]);
			problemArray[i]=new Conjunction(new NaturalNumberBitMultiply(
					membership[i], numbers[i], subAnswer[i]),
					new NaturalNumberAdder(subTotal[i - 1], subAnswer[i],
							subTotal[i]));
			//System.out.println("h3");
		}
		IProblem problem = new Conjunction(new Conjunction(problemArray), new NaturalNumberEqualizer(
				subTotal[numbers.length - 1], conditionalSum));
		this.setClauses(problem.getClauses());
	}

	public ConditionalAdder(INaturalNumberList numbersArray,
			IBitString membership, INaturalNumber conditionalSum)
			throws Exception
	{
		this(numbersArray.getNaturalNumberArray(), membership.getBVArray(),
				conditionalSum);
	}

	public ConditionalAdder(INaturalNumberList numbersArray,
			IBooleanVariable[] membership, INaturalNumber conditionalSum)
			throws Exception
	{
		this(numbersArray.getNaturalNumberArray(), membership, conditionalSum);
	}
}