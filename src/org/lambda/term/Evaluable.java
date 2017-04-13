/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public abstract class Evaluable<LIT> {
	/**
	 * The number of unbound variables (highest index + 1)
	 */
	public abstract int getExtent();

	/**
	 * Evaluates this function with the given executor and context
	 */
	public abstract <DATA> DATA evaluate(Executor<DATA, LIT> executor,
			Context<DATA> context);
}
