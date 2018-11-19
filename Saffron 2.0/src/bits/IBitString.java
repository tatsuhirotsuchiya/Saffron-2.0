/*
 * IBitString.java	1.1 05/10/27
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package bits;

import java.util.List;

public interface IBitString extends Cloneable
{
	List<IBooleanVariable> asList();

	Object clone() throws CloneNotSupportedException;

	IBooleanVariable getBooleanVariable(int i) throws Exception;

	IBooleanVariable[] getBVArray() throws Exception;

	IBooleanVariable[] getBVArray(int size) throws Exception;

	String getName();

	void setBooleanVariable(int i, IBooleanVariable ib) throws Exception;

	void setName(String s) throws Exception;

	int size();

	String toBits();

	@Override
	String toString();
}