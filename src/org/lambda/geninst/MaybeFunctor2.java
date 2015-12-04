/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.geninst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class MaybeFunctor2<MAYBE> extends Functor2<MAYBE> {
	private final Maybe<MAYBE, ?> maybe;

	public MaybeFunctor2(Maybe<MAYBE, ?> maybe) {
		this.maybe = maybe;
	}

	@Override
	public <ARG, RET> MAYBE fmap(final Func<ARG, RET> func, MAYBE list) {
		Maybe<MAYBE, ARG> m = maybe.specialize();
		return m.match(list, new Maybe.Case<MAYBE, ARG>() {
			@Override
			public MAYBE nothing() {
				Maybe<MAYBE, RET> m = maybe.specialize();
				return m.nothing();
			}

			@Override
			public MAYBE just(ARG arg) {
				Maybe<MAYBE, RET> m = maybe.specialize();
				return m.just(func.apply(arg));
			}
		});
	}
}
