/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class ConstantFolding<DATA, LIT> extends Executor<DATA, LIT> {
	public interface Config<LIT> {
		public boolean isUnaryOp(LIT lit);
	}

	private final Executor<DATA, LIT> executor;
	private final Config<DATA> config;

	public ConstantFolding(Executor<DATA, LIT> executor, Config<DATA> config) {
		this.executor = executor;
		this.config = config;
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
	public DATA addition(DATA func, DATA arg) {
		return executor.addition(func, arg);
	}

	@Override
	public DATA literal(LIT value) {
		return executor.literal(value);
	}
}
