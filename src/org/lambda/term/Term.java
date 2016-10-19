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

	@SuppressWarnings("unchecked")
	public <DATA> DATA evaluate(Executor<DATA> executor, DATA... data) {
		return compile().evaluate(executor, data);
	}

	public Term rewrite(Term... data) {
		Term[] upvars = new Term[getExtent()];

		for (int i = data.length; i < upvars.length; i++)
			upvars[i] = new Variable(i);

		for (int i = 0; i < Math.min(upvars.length, data.length); i++)
			upvars[i] = data[i];

		return evaluate(Rewriter.INSTANCE, upvars);
	}

	public String toString() {
		Printer.Data[] upvars = new Printer.Data[getExtent()];
		for (int i = 0; i < upvars.length; i++)
			upvars[upvars.length - 1 - i] = new Printer.Data((char) ('a' + i));

		return evaluate(Printer.INSTANCE, upvars).value;
	}
}
