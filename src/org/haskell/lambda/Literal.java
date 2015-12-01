/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambda;

public class Literal<DATA> extends Term<DATA> {
	public final DATA data;

	public Literal(DATA data) {
		assert data != null;

		this.data = data;
	}

	@Override
	public Term<DATA> evaluate() {
		return this;
	}
}
