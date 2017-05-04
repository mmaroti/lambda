/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class Apply<LIT> extends Binary<LIT> {
	public Apply(Term<LIT> left, Term<LIT> right) {
		super(left, right);
	}

	@Override
	public Binary<LIT> create(Term<LIT> left, Term<LIT> right) {
		return new Apply<LIT>(left, right);
	}

	@Override
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator,
			Context<DATA> context) {
		DATA a = left.evaluate(evaluator, context);
		DATA b = right.evaluate(evaluator, context);
		return evaluator.apply(a, b);
	}
}
