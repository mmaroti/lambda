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
	public <DATA> DATA evaluate(Executor<DATA> executor, Context<DATA> context) {
		DATA a = left.evaluate(executor, context);
		DATA b = right.evaluate(executor, context);
		return executor.apply(a, b);
	}
}
