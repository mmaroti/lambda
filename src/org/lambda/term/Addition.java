/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.exec.*;

public class Addition<LIT> extends Binary<LIT> {
	public Addition(Term<LIT> left, Term<LIT> right) {
		super(left, right);
	}

	@Override
	public Binary<LIT> create(Term<LIT> left, Term<LIT> right) {
		return new Addition<LIT>(left, right);
	}

	@Override
	public <DATA> DATA evaluate(Executor<DATA, LIT> executor,
			Context<DATA> context) {
		DATA a = left.evaluate(executor, context);
		DATA b = right.evaluate(executor, context);
		return executor.addition(a, b);
	}
}
