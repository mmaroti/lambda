/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class MaybeFunctor extends Functor<Maybe<?>> {
	@Override
	public <ARG, RET> Maybe<RET> fmap(final Func<ARG, RET> func, Maybe<?> data) {
		@SuppressWarnings("unchecked")
		Maybe<ARG> d = (Maybe<ARG>) data;

		return d.match(new Maybe.Case<Maybe<RET>, ARG>() {
			@Override
			public Maybe<RET> nothing() {
				return new Maybe.Nothing<RET>();
			}

			@Override
			public Maybe<RET> just(ARG elem) {
				return new Maybe.Just<RET>(func.apply(elem));
			}
		});
	}
}
