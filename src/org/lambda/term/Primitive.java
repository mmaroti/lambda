/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.eval.*;

public class Primitive<LIT> extends Term<LIT> {
	public final String prim;

	public Primitive(String prim) {
		this.prim = prim;
	}

	@Override
	public int getExtent() {
		return 0;
	}

	@Override
	public int getOccurences(int index) {
		return 0;
	}

	@Override
	public Term<LIT> increment(int limit) {
		return this;
	}

	@Override
	public <DATA> DATA evaluate(Evaluator<DATA, LIT> evaluator, Context<DATA> context) {
		return evaluator.primitive(prim);
	}
}
