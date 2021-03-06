/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.eval;

public abstract class Evaluator<DATA, LIT> {
	public abstract DATA evaluate(Evaluable<LIT> evaluable);

	public abstract DATA closure(Context<DATA> context, DATA type, Evaluable<LIT> body);

	public abstract DATA apply(DATA func, DATA arg);

	public abstract DATA literal(LIT value);

	public abstract DATA primitive(String prim);
}
