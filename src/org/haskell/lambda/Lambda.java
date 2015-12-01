/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambda;

import org.haskell.data.*;

public class Lambda<ARG, RET> extends Func<Term<ARG>, Term<RET>> {
	private final Var<ARG> var;
	private final Term<RET> term;

	private static class Var<DATA> extends Term<DATA> {
		public Term<DATA> data;

		@Override
		public Term<DATA> evaluate() {
			if (data == null)
				return this;
			else
				return data;
		}
	}

	public Lambda(Func<Term<ARG>, Term<RET>> func) {
		assert func != null;

		var = new Var<ARG>();
		term = func.apply(var);
	}

	public Term<RET> apply(Term<ARG> arg) {
		assert arg != null;
		if (var.data != null)
			throw new IllegalStateException("recursion");

		var.data = arg;
		Term<RET> ret = term.evaluate();
		var.data = null;

		return ret;
	}
}
