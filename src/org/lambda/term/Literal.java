/**
 * Copyright (C) Miklos Maroti, 2016
 */

package org.lambda.term;

public abstract class Literal extends Term {
	@Override
	public int getExtent() {
		return 0;
	}

	@Override
	public int getOccurences(int index) {
		return 0;
	}

	@Override
	public Term increment(int limit) {
		return this;
	}
}
