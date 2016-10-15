/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Executor<DATA> {
	public abstract DATA closure(Function function, Context<DATA> context);

	public abstract DATA integer(int value);

	public abstract DATA apply(DATA func, DATA arg);

	public abstract DATA addition(DATA left, DATA right);
}
