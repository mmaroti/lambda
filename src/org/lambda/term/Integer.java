/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public class Integer<LIT> extends Literal<LIT> {
	public final int value;

	public Integer(int value) {
		this.value = value;
	}

	@Override
	public <DATA> DATA evaluate(Executor<DATA, LIT> executor,
			Context<DATA> context) {
		return executor.integer(value);
	}
}
