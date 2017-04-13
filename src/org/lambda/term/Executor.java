/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public abstract class Executor<DATA, LIT> {
	public abstract DATA closure(Evaluable<LIT> function, Context<DATA> context);

	public abstract DATA integer(int value);

	public abstract DATA apply(DATA func, DATA arg);

	public abstract DATA addition(DATA left, DATA right);

	public abstract DATA literal(LIT value);
}
