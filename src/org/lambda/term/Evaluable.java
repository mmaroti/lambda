/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Evaluable {
	/**
	 * The number of unbound variables (highest index + 1)
	 */
	public abstract int getExtent();

	/**
	 * Evaluates this function with the given executor and context
	 */
	public abstract <DATA> DATA evaluate(Executor<DATA> executor,
			Context<DATA> context);
}
