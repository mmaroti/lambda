/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class UnaryOp<LIT> extends Term<LIT> {
	public final LIT func;
	public final Term<LIT> arg;

	public UnaryOp(LIT func, Term<LIT> arg) {
		assert func != null && arg != null;

		this.func = func;
		this.arg = arg;
	}

	@Override
	public int getExtent() {
		return arg.getExtent();
	}

	@Override
	public int getOccurences(int index) {
		return arg.getOccurences(index);
	}

	@Override
	public Term<LIT> increment(int limit) {
		Term<LIT> a = arg.increment(limit);
		if (a == arg)
			return this;
		else
			return new UnaryOp<LIT>(func, a);
	}

	@Override
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator,
			Context<DATA> context) {
		DATA a = arg.evaluate(evaluator, context);
		return evaluator.unaryop(func, a);
	}
}
