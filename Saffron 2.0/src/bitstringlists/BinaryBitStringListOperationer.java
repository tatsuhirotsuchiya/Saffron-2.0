package bitstringlists;

import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;

public class BinaryBitStringListOperationer extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		BinaryBitStringListOperationer o = new BinaryBitStringListOperationer();
		o.addRule(new BitString("a", "0"), new BitString("b", "0"),
				new BitString("c", "0"));
		o.addRule(new BitString("a", "0"), new BitString("b", "1"),
				new BitString("c", "1"));
		o.addRule(new BitString("a", "1"), new BitString("b", "0"),
				new BitString("c", "1"));
		o.addRule(new BitString("a", "1"), new BitString("b", "1"),
				new BitString("c", "0"));
		System.out.println(o);
	}

	public void addRule(IBitString a, IBitString b, IBitString c)
			throws Exception
	{
		IProblem newRule = new Conjunction(new BitStringFixer(a),
				new BitStringFixer(b), new BitStringFixer(c));
		if (this.isEmpty())
			this.setClauses(newRule.getClauses());
		else
			this.setClauses(new Disjunction(this, newRule).getClauses());
	}
}
