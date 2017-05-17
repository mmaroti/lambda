/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class Variable<LIT> extends Term<LIT> {
	public final int index;

	public Variable(int index) {
		assert index >= 0;
		this.index = index;
	}

	@Override
	public int getExtent() {
		return index + 1;
	}

	@Override
	public int getOccurences(int index) {
		return index == this.index ? 1 : 0;
	}

	@Override
	public Term<LIT> increment(int limit) {
		if (index < limit)
			return this;
		else
			return new Variable<LIT>(index + 1);
	}

	@Override
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator, Context<DATA> context) {
		for (int i = 0; i < index; i++)
			context = context.parent;

		return context.data;
	}
}
