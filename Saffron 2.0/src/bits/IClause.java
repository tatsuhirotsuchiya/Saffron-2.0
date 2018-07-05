/*
 * IBooleanLiteral.java
 *
 * Copyright 2004-2005 Positronic Software.
 *
 */
package bits;

import java.util.List;

/**
 * The <code>IClause</code> interface must be implemented by any class
 * definition of <code>Clause</code> contempleted as an alternative to the
 * <code>Clause</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 * @version 1.1, 05/07/22
 */
public interface IClause extends Comparable<Object>
{
	boolean add(BooleanLiteral b) throws Exception;

	// public boolean add(BooleanLiteral b) throws Exception;
	public void addLiteral(IBooleanLiteral bl) throws Exception;

	Object clone();

	// public Object clone();
	public int compareTo(IClause o) throws Exception;

	@Override
	public int compareTo(Object o);

	boolean contains(IBooleanLiteral bl) throws Exception;

	// public boolean contains(IBooleanLiteral bl) throws Exception;
	// public IBooleanLiteral differsSinglyFrom(IClause c) throws Exception;
	// public boolean dominates(IClause clause) throws Exception;
	// public Object duplicate() throws Exception;
	public boolean equals(IClause o) throws Exception;

	public boolean evaluate();

	// IBooleanLiteral differsSinglyFrom(IClause c) throws Exception;
	// boolean dominates(IClause clause) throws Exception;
	IBooleanVariable[] getBooleanVariables();

	void getBooleanVariables(List<IBooleanVariable> hs) throws Exception;

	IBooleanLiteral getLiteralAt(int n) throws Exception;

	boolean isEmpty();

	boolean isMemberOf(List<IClause> h) throws Exception;

	boolean isSatisfied();

	// boolean isSingleton();
	// IClause minus(IClause o) throws Exception;
	IClause nor(IBooleanVariable bv) throws Exception;

	IClause or(IBooleanVariable bv) throws Exception;

	IClause orNot(IBooleanVariable bv) throws Exception;

	boolean remove(IBooleanLiteral b);

	IBooleanLiteral removeClause(int i);

	IClause resolve(IBooleanLiteral ib) throws Exception;

	IClause resolve(IBooleanVariable b, boolean value) throws Exception;

	int size();

	// IClause substitute(Map<IBooleanLiteral,IBooleanLiteral> h) throws
	// Exception;
	IBooleanLiteral[] toArray();

	// public IBooleanVariable[] getBooleanVariables();

	// public void getBooleanVariables(List<IBooleanVariable> hs) throws
	// Exception;
	// public IBooleanLiteral getLiteralAt(int n) throws ClauseException;
	// public boolean isEmpty();
	// public boolean isMemberOf(List<IClause> h) throws Exception;
	// public boolean isSatisfied();
	// public boolean isSensitiveTo(IBooleanVariable bv) throws ClauseException;
	// public boolean isSingleton();

	// public Iterator<IBooleanLiteral> iterator();

	// public IClause minus(IClause o) throws Exception;
	// public IClause nor(IBooleanVariable bv) throws Exception;
	// public IClause or(IBooleanVariable bv) throws Exception;

	// public IClause orNot(IBooleanVariable bv) throws Exception;
	// public boolean remove(IBooleanLiteral b);

	// public IBooleanLiteral removeClause(int i);

	// public IClause resolve(IBooleanLiteral ib) throws Exception;
	// public IClause resolve(IBooleanVariable b, boolean value) throws
	// Exception;

	// public int size();

	// public IClause substitute(Map<IBooleanLiteral,IBooleanLiteral> h) throws
	// Exception;

	// public IClause substitute(Map<IBooleanLiteral,IBooleanLiteral> h, IClause
	// c) throws Exception;

	// public IProblem ThreeSATProblem() throws Exception;

	// public IBooleanLiteral[] toArray();

	// public String toCode() throws ClauseException;

	public IBooleanLiteral[] toSortedArray();

	@Override
	public String toString();

}