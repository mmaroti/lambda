/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.eval;

public class IdentityEval<DATA, LIT> extends Evaluator<DATA, LIT> {
	private final Evaluator<DATA, LIT> executor;

	public IdentityEval(Evaluator<DATA, LIT> executor) {
		this.executor = executor;
	}

	@Override
	public DATA evaluate(Evaluable<LIT> evaluable) {
		return executor.evaluate(evaluable);
	}

	@Override
	public DATA closure(Evaluable<LIT> function, Context<DATA> context) {
		return executor.closure(function, context);
	}

	@Override
	public DATA apply(DATA func, DATA arg) {
		return executor.apply(func, arg);
	}

	@Override
	public DATA literal(LIT value) {
		return executor.literal(value);
	}
}
