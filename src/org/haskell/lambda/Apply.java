/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambda;

import org.haskell.data.*;

public class Apply<ARG, RET> extends Term<RET> {
	private final Term<Func<ARG, RET>> func;
	private final Term<ARG> arg;

	public Apply(Term<Func<ARG, RET>> func, Term<ARG> arg) {
		assert func != null && arg != null;

		this.func = func;
		this.arg = arg;
	}

	public Term<RET> simplify() {
		Term<Func<ARG, RET>> f = func.simplify();
		Term<ARG> a = arg.simplify();

		if (f instanceof Literal && a instanceof Literal) {
			Func<ARG, RET> g = ((Literal<Func<ARG, RET>>) f).getData();
			ARG b = ((Literal<ARG>) a).getData();
			return new Literal<RET>(g.apply(b));
		} else if (f == func && a == arg)
			return this;
		else
			return new Apply<ARG, RET>(f, a);
	}
}
