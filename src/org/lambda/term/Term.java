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
	 * Decrements the indices of the unbound variables greater than the limit
	 */
	public abstract Term decrement(int limit);

	/**
	 * Compiles the term into a generic executable function.
	 */
	public abstract Function compile();

	public Term rewrite() {
		Function f = compile();

		Context<Term> c = null;
		for (int i = f.extent - 1; i >= 0; i--)
			c = new Context<Term>(new Variable(i), c);

		return f.evaluate(Rewriter.INSTANCE, c);
	}

	@Override
	public String toString() {
		Function f = compile();

		Context<Printer.Data> c = null;
		for (int i = 0; i < f.extent; i++)
			c = new Context<Printer.Data>(Printer.variable(i), c);

		return f.evaluate(Printer.INSTANCE, c).value;
	}
}
