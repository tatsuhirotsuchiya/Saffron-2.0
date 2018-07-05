/*
 * ClauseException.java	1.0 05/10/20
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */
package bits;

/**
 * @author Kerry Michael Soileau <blockquote>
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre>
 * 
 *         </blockquote>
 * @version 1.0, 05/10/20
 * @see Exception
 * @see String
 */
public class ClauseException extends Exception
{
	private static final long serialVersionUID = -7467170742487281442L;

	public ClauseException(String s)
	{
		super(s);
	}
}