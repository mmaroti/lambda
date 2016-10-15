/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Function {
	public abstract <DATA> DATA evaluate(Executor<DATA> executor,
			Context<DATA> context);

	@SuppressWarnings("unchecked")
	public <DATA> DATA evaluate(Executor<DATA> executor, DATA... data) {
		Context<DATA> context = null;
		for (int i = 0; i < data.length; i++)
			context = new Context<DATA>(data[i], context);

		return evaluate(executor, context);
	}
}
