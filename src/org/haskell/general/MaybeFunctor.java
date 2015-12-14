/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.general;

import org.haskell.algdata.*;
import org.haskell.data.*;
import org.haskell.typeclass.*;

public class MaybeFunctor<MAYBE> extends Functor<MAYBE> {
	private final Maybe<MAYBE, ?> maybe;

	public MaybeFunctor(Maybe<MAYBE, ?> maybe) {
		this.maybe = maybe;
	}

	@Override
	public <ARG, RET> MAYBE fmap(final Func<ARG, RET> func, MAYBE list) {
		@SuppressWarnings("unchecked")
		Maybe<MAYBE, ARG> m = (Maybe<MAYBE, ARG>) maybe;
		return m.match(list, new Maybe.Case<MAYBE, ARG>() {
			@Override
			public MAYBE nothing() {
				@SuppressWarnings("unchecked")
				Maybe<MAYBE, RET> m = (Maybe<MAYBE, RET>) maybe;
				return m.nothing();
			}

			@Override
			public MAYBE just(ARG arg) {
				@SuppressWarnings("unchecked")
				Maybe<MAYBE, RET> m = (Maybe<MAYBE, RET>) maybe;
				return m.just(func.apply(arg));
			}
		});
	}
}
