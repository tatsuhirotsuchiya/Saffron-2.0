package bits;

/**
 * An extension of the IProblem class which performs an add with carry of three
 * BooleanVariables.
 *
 * To use this class, one passes BooleanVariables w, x, y, z and c to the
 * constructor. The ThreeBitAdder object produced is a IProblem, and one may
 * manipulate it using any of the methods provided by the IProblem class.
 *
 * For example, when the IProblem instance p defined by
 *
 * IProblem p=new ThreeBitAdder(w,x,y,z,c);
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * z == w + x + y (without carry) c == (carry bit)
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.11
 * @since 2004/11/24
 */
public class ThreeBitAdder extends Problem implements IProblem
{
	public ThreeBitAdder(IBooleanVariable w, IBooleanVariable x,
			IBooleanVariable y, IBooleanVariable z, IBooleanVariable c)
			throws Exception
	{
		this.setClauses(new IClause[]
		{ Clause.newClause().or(w).or(x).orNot(c),
				Clause.newClause().or(w).or(y).orNot(c),
				Clause.newClause().or(w).orNot(z).orNot(c),
				Clause.newClause().or(x).or(y).orNot(c),
				Clause.newClause().or(x).orNot(z).orNot(c),
				Clause.newClause().or(y).orNot(z).orNot(c),
				Clause.newClause().orNot(w).orNot(x).orNot(y).or(z),
				Clause.newClause().or(w).or(x).or(y).orNot(z),
				Clause.newClause().or(w).or(x).orNot(y).or(z),
				Clause.newClause().or(w).orNot(x).or(y).or(z),
				Clause.newClause().or(w).orNot(y).or(z).or(c),
				Clause.newClause().or(w).orNot(x).orNot(y).orNot(z),
				Clause.newClause().orNot(w).or(x).or(y).or(z),
				Clause.newClause().or(x).orNot(y).or(z).or(c),
				Clause.newClause().orNot(w).or(x).orNot(y).orNot(z),
				Clause.newClause().orNot(x).or(y).or(z).or(c),
				Clause.newClause().orNot(w).orNot(x).or(y).orNot(z),
				Clause.newClause().orNot(x).orNot(y).orNot(z).or(c) });
	}
}