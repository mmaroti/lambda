/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.eval;

public abstract class Evaluable<LIT> {
	/**
	 * The number of unbound variables (highest index + 1)
	 */
	public abstract int getExtent();

	/**
	 * Evaluates this function with the given executor and context
	 */
	public abstract <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator, Context<DATA> context);

	@Override
	@SuppressWarnings("unchecked")
	public String toString() {
		PrinterEval<LIT> printer = (PrinterEval<LIT>) PrinterEval.INSTANCE;
		return printer.evaluate(this).value;
	}
}
