/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

import org.lambda.exec.*;

public class Literal<LIT> extends Term<LIT> {
	public final LIT value;

	public Literal(LIT value) {
		this.value = value;
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
	public Term<LIT> decrement(int limit) {
		return this;
	}

	@Override
	public <DATA> DATA evaluate(Executor<DATA, LIT> executor,
			Context<DATA> context) {
		return executor.literal(value);
	}
}
