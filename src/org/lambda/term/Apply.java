/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Apply extends Binary {
	public Apply(Term left, Term right) {
		super(left, right);
	}

	@Override
	public Binary create(Term left, Term right) {
		return new Apply(left, right);
	}

	@Override
	public Function compile() {
		final Function f = left.compile();
		final Function g = right.compile();
		return new Function() {
			@Override
			public <DATA> DATA evaluate(Executor<DATA> executor,
					Context<DATA> context) {
				DATA a = f.evaluate(executor, context);
				DATA b = g.evaluate(executor, context);
				return executor.apply(a, b);
			}
		};
	}

	@Override
	public String toString() {
		return "(" + left + ")(" + right + ")";
	}
}
