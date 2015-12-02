/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambda;

import org.haskell.data.*;

public class Apply<ARG, RET> extends Term<RET> {
	public final Term<Func<Term<ARG>, Term<RET>>> fun;
	public final Term<ARG> arg;

	public Apply(Term<Func<Term<ARG>, Term<RET>>> fun, Term<ARG> arg) {
		assert fun != null && arg != null;

		this.fun = fun;
		this.arg = arg;
	}

	@Override
	public Term<RET> evaluate() {
		Term<Func<Term<ARG>, Term<RET>>> f = fun.evaluate();
		Term<ARG> a = arg.evaluate();
		if (f instanceof Literal) {
			Func<Term<ARG>, Term<RET>> g = ((Literal<Func<Term<ARG>, Term<RET>>>) f).data;
			return g.apply(a);
		} else if (f == fun && a == arg)
			return this;
		else
			return new Apply<ARG, RET>(f, a);
	}
}
