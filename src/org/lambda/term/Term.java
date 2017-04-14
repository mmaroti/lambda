/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public abstract class Term<LIT> extends Evaluable<LIT> {
	/**
	 * The number of occurrences of the given variable
	 */
	public abstract int getOccurences(int index);

	/**
	 * Increments the indices of the unbound variables greater than the limit
	 */
	public abstract Term<LIT> increment(int limit);

	/**
	 * Decrements the indices of the unbound variables greater than the limit
	 */
	public abstract Term<LIT> decrement(int limit);

	public Term<LIT> rewrite() {
		Context<Term<LIT>> c = null;
		for (int i = getExtent() - 1; i >= 0; i--)
			c = new Context<Term<LIT>>(new Variable<LIT>(i), c);

		return evaluate(new Rewriter<LIT>(), c);
	}

	public Term<LIT> write() {
		Context<Term<LIT>> c = null;
		for (int i = getExtent() - 1; i >= 0; i--)
			c = new Context<Term<LIT>>(new Variable<LIT>(i), c);

		return evaluate(new Writer<LIT>(), c);
	}

	@Override
	public String toString() {
		Context<Printer.Data> c = null;
		for (int i = 0; i < getExtent(); i++)
			c = new Context<Printer.Data>(Printer.variable(i), c);

		return evaluate(new Printer<LIT>(), c).value;
	}
}
