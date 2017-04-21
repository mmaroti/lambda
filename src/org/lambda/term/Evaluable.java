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
	public abstract <DATA> DATA evaluate(Executor<DATA, LIT> executor, Context<DATA> context);

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
