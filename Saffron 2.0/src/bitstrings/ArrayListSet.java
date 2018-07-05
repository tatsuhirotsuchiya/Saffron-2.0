package bitstrings;

/**
 * Description: Works just like java.util.ArrayList, except it will refuse to
 * add an element if it is equal to an element already appearing in the
 * ArrayListSet. In other words, it's an ArrayList which maintains uniqueness
 * like java.util.Set does.
 * It also provides a sort function, if all of the members are mutually 
 * Comparable.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.4
 * @version 1.3 void sort() method added March 4, 2009.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ArrayListSet<E> extends ArrayList<E> implements Set<E>
{
	private static final long serialVersionUID = -2554176229274064937L;

	// Simple demo --- may be deleted at will.
	public static void main(String[] args)
	{
		ArrayListSet<String> vectorSet1 = new ArrayListSet<String>();
		vectorSet1.add("Leda");
		vectorSet1.add("Tyndareus");
		vectorSet1.add("Helen");
		vectorSet1.add("");
		vectorSet1.add("Thyestes");
		vectorSet1.add("Tyndareus");
		vectorSet1.add("Tantalus");
		vectorSet1.add("");
		vectorSet1.add("Leda");
		vectorSet1.add("Tyndareus");
		// vectorSet1.add(new Integer(32));
		vectorSet1.add("Agamemnon");
		vectorSet1.add("Aerope");
		vectorSet1.add("Leda");
		// vectorSet1.add(new Integer(33));
		vectorSet1.add("Thyestes");
		for (int i = 0; i < vectorSet1.size(); i++)
			System.out.println(vectorSet1.get(i));
		System.out.println("..................");
		// vectorSet1.sort();
		for (int i = 0; i < vectorSet1.size(); i++)
			System.out.println(vectorSet1.get(i));
		System.out.println("..................");
	}

	/**
	 * Constructs a new, empty ArrayListSet.
	 */
	public ArrayListSet()
	{
	}

	/**
	 * Constructs a new set containing the elements in the specified collection.
	 *
	 * @param c
	 *            The elements that will comprise the new set.
	 */
	public ArrayListSet(Collection<E> c)
	{
		add(c);
	}

	/**
	 * Constructs a new set containing space for the specified number of
	 * objects.
	 *
	 * @param n
	 *            The capacity of the new set.
	 */
	public ArrayListSet(int n)
	{
		super(n);
	}

	/**
	 * Adds all of the elements in the specified collection to this set.
	 *
	 * @param c
	 *            elements to be added
	 * @return <tt>true</tt> if this set changed as a result of the call.
	 */
	public boolean add(Collection<E> c)
	{
		boolean changed = false;
		if (c.size() > 0)
		{
			Iterator<E> it = c.iterator();
			while (it.hasNext())
			{
				E nextobj = it.next();
				boolean res = this.add(nextobj);
				if (res)
					changed = true;
			}
		}
		return changed;
	}

	/**
	 * Adds the specified element to this set if it is not already present.
	 *
	 * @param o
	 *            element to be added to this set.
	 * @return <tt>true</tt> if the set did not already contain the specified
	 *         element.
	 */
	@Override
	public boolean add(E o)
	{
		for (int i = 0; i < this.size(); i++)
		{
			Object obj = this.get(i);
			if (obj.equals(o))
				return false;
		}
		super.add(o);
		return true;
	}

	/**
	 * Adds the specified element to this set if it is not already present.
	 *
	 * @param o
	 *            element to be added to this set.
	 */
	public void addElement(E o)
	{
		for (int i = 0; i < this.size(); i++)
		{
			if (this.get(i).equals(o))
				return;
		}
		super.add(o);
	}
}