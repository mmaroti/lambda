/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.mmaroti.lambda6;

public abstract class Function<DATA> {
	public abstract DATA evaluate(Context<DATA> context);

	@SuppressWarnings("unchecked")
	public DATA evaluate(DATA... data) {
		Context<DATA> context = null;
		for (int i = data.length - 1; i >= 0; i--)
			context = new Context<DATA>(data[i], context);

		return evaluate(context);
	}
}
