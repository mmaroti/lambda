/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Term {
	/**
	 * The number of unbound variables (highest index + 1)
	 */
	public abstract int getExtent();

	/**
	 * The number of occurrences of the given variable
	 */
	public abstract int getOccurences(int index);

	/**
	 * Increments the indices of the unbound variables greater than the limit
	 */
	public abstract Term increment(int limit);

	/**
	 * Compiles the term into a generic executable function.
	 */
	public abstract Function compile();

	public abstract String toString();
}
