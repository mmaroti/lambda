/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class Pair<LIT> extends Binary<LIT> {
	public Pair(Term<LIT> left, Term<LIT> right) {
		super(left, right);
	}

	@Override
	public Binary<LIT> create(Term<LIT> left, Term<LIT> right) {
		return new Pair<LIT>(left, right);
	}

	@Override
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator,
			Context<DATA> context) {
		DATA a = left.evaluate(evaluator, context);
		DATA b = right.evaluate(evaluator, context);
		return evaluator.pair(a, b);
	}
}
