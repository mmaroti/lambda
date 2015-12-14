/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.general;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class MaybeFunctor<MAYBE> extends Functor<MAYBE> {
	private final Optional<MAYBE, ?> maybe;

	public MaybeFunctor(Optional<MAYBE, ?> maybe) {
		this.maybe = maybe;
	}

	@Override
	public <ARG, RET> MAYBE fmap(final Func<ARG, RET> func, MAYBE list) {
		@SuppressWarnings("unchecked")
		Optional<MAYBE, ARG> m = (Optional<MAYBE, ARG>) maybe;
		return m.match(list, new Optional.Case<MAYBE, ARG>() {
			@Override
			public MAYBE nothing() {
				@SuppressWarnings("unchecked")
				Optional<MAYBE, RET> m = (Optional<MAYBE, RET>) maybe;
				return m.nothing();
			}

			@Override
			public MAYBE just(ARG arg) {
				@SuppressWarnings("unchecked")
				Optional<MAYBE, RET> m = (Optional<MAYBE, RET>) maybe;
				return m.just(func.apply(arg));
			}
		});
	}
}
