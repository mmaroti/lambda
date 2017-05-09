/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public abstract class Term<LIT> extends Evaluable<LIT> {
	/**
	 * The number of occurrences of the given variable
	 */
	public abstract int getOccurences(int index);

	/**
	 * Increments the indices of the unbound variables greater than the limit
	 */
	public abstract Term<LIT> increment(int limit);
}
