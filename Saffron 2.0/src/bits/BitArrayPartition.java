package bits;

import java.util.ArrayList;

/**
 * TBS
 *
 * @author Kerry Michael Soileau <blockquote>
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre>
 * 
 *         </blockquote>
 * @version 1.0, 2018/11/05
 *
 */
public class BitArrayPartition extends Problem implements IProblem
{
	private static final long serialVersionUID = 4725109314250454123L;

	public BitArrayPartition(IBooleanVariable[][] partition) throws Exception
	{
		int partitions = partition.length;
		int bits = partition[0].length;

		@SuppressWarnings("unchecked")
		ArrayList<IBooleanVariable>[] bitArrayList = new ArrayList[bits];
		IProblem[] p = new IProblem[bits];

		for (int j = 0; j < bits; j++)
		{
			bitArrayList[j] = new ArrayList<IBooleanVariable>();
			for (int i = 0; i < partitions; i++)
				bitArrayList[j].add(partition[i][j]);
			p[j] = new BitExclusiveSelector(bitArrayList[j]);
		}

		IProblem problem = new Conjunction(p);
		this.setClauses(problem.getClauses());
	}
}