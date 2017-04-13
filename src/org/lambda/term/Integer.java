/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public class Integer extends Literal {
	public final int value;

	public Integer(int value) {
		this.value = value;
	}

	@Override
	public <DATA> DATA evaluate(Executor<DATA> executor, Context<DATA> context) {
		return executor.integer(value);
	}
}
