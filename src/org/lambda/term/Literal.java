/**
 * Copyright (C) Miklos Maroti, 2016-2017
 */

package org.lambda.term;

public abstract class Literal<LIT> extends Term<LIT> {
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
}
