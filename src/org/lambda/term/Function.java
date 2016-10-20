/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Function {
	public final int extent;

	public Function(int extent) {
		assert extent >= 0;
		this.extent = extent;
	}

	public abstract <DATA> DATA evaluate(Executor<DATA> executor,
			Context<DATA> context);
}
