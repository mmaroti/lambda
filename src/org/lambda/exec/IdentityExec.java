/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.exec;

public class IdentityExec<DATA, LIT> extends Executor<DATA, LIT> {
	private final Executor<DATA, LIT> executor;

	public IdentityExec(Executor<DATA, LIT> executor) {
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
	public DATA integer(int value) {
		return executor.integer(value);
	}

	@Override
	public DATA apply(DATA func, DATA arg) {
		return executor.apply(func, arg);
	}

	@Override
	public DATA addition(DATA left, DATA right) {
		return executor.addition(left, right);
	}

	@Override
	public DATA literal(LIT value) {
		return executor.literal(value);
	}
}
