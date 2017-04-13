/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambda;

public class Literal<DATA> extends Term<DATA> {
	private final DATA data;

	public DATA getData() {
		return data;
	}

	public Literal(DATA data) {
		this.data = data;
	}

	@Override
	public Term<DATA> simplify() {
		return this;
	}
}
