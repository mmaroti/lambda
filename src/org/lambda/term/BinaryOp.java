/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class BinaryOp<LIT> extends Term<LIT> {
	public final LIT func;
	public final Term<LIT> arg1;
	public final Term<LIT> arg2;

	public BinaryOp(LIT func, Term<LIT> arg1, Term<LIT> arg2) {
		assert func != null && arg1 != null && arg2 != null;

		this.func = func;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	@Override
	public int getExtent() {
		return Math.max(arg1.getExtent(), arg2.getExtent());
	}

	@Override
	public int getOccurences(int index) {
		return arg1.getOccurences(index) + arg2.getOccurences(index);
	}

	@Override
	public Term<LIT> increment(int limit) {
		Term<LIT> a1 = arg1.increment(limit);
		Term<LIT> a2 = arg2.increment(limit);
		if (a1 == arg1 && a2 == arg2)
			return this;
		else
			return new BinaryOp<LIT>(func, a1, a2);
	}

	@Override
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator,
			Context<DATA> context) {
		DATA a1 = arg1.evaluate(evaluator, context);
		DATA a2 = arg2.evaluate(evaluator, context);
		return evaluator.binaryop(func, a1, a2);
	}
}
