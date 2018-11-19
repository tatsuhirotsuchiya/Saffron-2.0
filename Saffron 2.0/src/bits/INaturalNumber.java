package bits;

/*
 * INaturalNumber.java	1.1 2009/05/21
 * Copyright 2009 Positronic Software.
 */
public interface INaturalNumber extends IBitString
{
	final int DEFAULTLENGTH = 5;
	
	long toDecimal();
}