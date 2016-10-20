/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Addition extends Binary {
	public Addition(Term left, Term right) {
		super(left, right);
	}

	@Override
	public Binary create(Term left, Term right) {
		return new Addition(left, right);
	}

	@Override
	public Function compile() {
		final Function f = left.compile();
		final Function g = right.compile();
		return new Function(Math.max(f.extent, g.extent)) {
			@Override
			public <DATA> DATA evaluate(Executor<DATA> executor,
					Context<DATA> context) {
				DATA a = f.evaluate(executor, context);
				DATA b = g.evaluate(executor, context);
				return executor.addition(a, b);
			}
		};
	}
}
