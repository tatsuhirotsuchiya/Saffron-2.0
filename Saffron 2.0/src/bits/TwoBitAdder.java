package bits;

/**
 * An extension of the IProblem class which performs an add with carry of two
 * BooleanVariables.
 *
 * To use this class, one passes BooleanVariables x, y, z and c to the
 * constructor. The TwoBitAdder object produced is a IProblem, and one may
 * manipulate it using any of the methods provided by the IProblem class.
 *
 * For example, when the IProblem instance p defined by
 *
 * IProblem p=new TwoBitAdder(x,y,z,c);
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * z == x + y (without carry) c == (carry bit)
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.11
 * @since 2004/11/24
 */
public class TwoBitAdder extends Problem implements IProblem
{
	public TwoBitAdder(IBooleanVariable x, IBooleanVariable y,
			IBooleanVariable z, IBooleanVariable c) throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().orNot(c).or(x), Clause.newClause().orNot(c).or(y),
				Clause.newClause().orNot(c).orNot(z),
				Clause.newClause().or(x).or(y).orNot(z),
				Clause.newClause().or(x).orNot(y).or(z),
				Clause.newClause().orNot(x).or(y).or(z),
				Clause.newClause().or(c).orNot(y).or(z),
				Clause.newClause().orNot(x).orNot(y).orNot(z) });
	}
}