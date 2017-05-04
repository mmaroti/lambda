/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class IdentityExec<DATA, LIT> {
	private final Executor<DATA, LIT> executor;

	public IdentityExec(Executor<DATA, LIT> executor) {
		this.executor = executor;
	}

	public DATA closure(Evaluable<LIT> function, Context<DATA> context) {
		return executor.closure(function, context);
	}

	public DATA integer(int value) {
		return executor.integer(value);
	}

	public DATA apply(DATA func, DATA arg) {
		return executor.apply(func, arg);
	}

	public DATA addition(DATA left, DATA right) {
		return executor.addition(left, right);
	}

	public DATA literal(LIT value) {
		return executor.literal(value);
	}
}
