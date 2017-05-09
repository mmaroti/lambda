/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.eval;

public abstract class Evaluator<DATA, LIT> {
	public abstract DATA evaluate(Evaluable<LIT> evaluable);

	public abstract DATA closure(Evaluable<LIT> function, Context<DATA> context);

	public abstract DATA apply(DATA func, DATA arg);

	public abstract DATA pair(DATA left, DATA right);

	public abstract DATA literal(LIT value);

	public abstract DATA operator(LIT func, DATA[] args);
}
