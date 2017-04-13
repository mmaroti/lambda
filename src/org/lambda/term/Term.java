/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Term extends Evaluable {
	/**
	 * The number of occurrences of the given variable
	 */
	public abstract int getOccurences(int index);

	/**
	 * Increments the indices of the unbound variables greater than the limit
	 */
	public abstract Term increment(int limit);

	/**
	 * Decrements the indices of the unbound variables greater than the limit
	 */
	public abstract Term decrement(int limit);

	public Term rewrite() {
		Context<Term> c = null;
		for (int i = getExtent() - 1; i >= 0; i--)
			c = new Context<Term>(new Variable(i), c);

		return evaluate(Rewriter.INSTANCE, c);
	}

	@Override
	public String toString() {
		Context<Printer.Data> c = null;
		for (int i = 0; i < getExtent(); i++)
			c = new Context<Printer.Data>(Printer.variable(i), c);

		return evaluate(Printer.INSTANCE, c).value;
	}
}
