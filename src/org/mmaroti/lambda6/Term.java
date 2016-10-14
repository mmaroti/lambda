/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda6;

public abstract class Term {
	/**
	 * The number of unbound variables (highest index + 1)
	 */
	public abstract int getExtent();

	/**
	 * The number of occurences of the given variable
	 */
	public abstract int getOccurences(int index);

	/**
	 * Increments the indices of the unbound variables greater than the limit
	 */
	public abstract Term increment(int limit);

	public abstract <DATA> Function<DATA> compile(Compiler<DATA> compiler);

	public abstract String toString();
}
