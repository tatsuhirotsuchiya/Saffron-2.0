package demos;

import bits.BooleanVariable;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.ThreeBitAdder;

public class ThreeBitAdderDemo2
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable w = BooleanVariable.getBooleanVariable("w");
		IBooleanVariable x = BooleanVariable.getBooleanVariable("x");
		IBooleanVariable y = BooleanVariable.getBooleanVariable("y");
		IBooleanVariable z = BooleanVariable.getBooleanVariable("z");
		IBooleanVariable c = BooleanVariable.getBooleanVariable("c");

		IProblem threeBitAdder1 = new ThreeBitAdder(w, x, y, z, c);
		threeBitAdder1.sort();
		System.out.println(threeBitAdder1);
		
		IProblem compressed = ((Problem) threeBitAdder1).compress();
		System.out.println(compressed);
	}
}