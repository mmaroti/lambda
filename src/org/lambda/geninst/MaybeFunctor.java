/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.geninst;

import org.haskell.data.Func;
import org.haskell.typeclass.*;

public class MaybeFunctor<MAYBE> extends Functor<Maybe<MAYBE, ?>, MAYBE> {

	public MaybeFunctor(Polymorf<Maybe<MAYBE, ?>> poly) {
		super(poly);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <ARG, RET> MAYBE fmap(final Func<ARG, RET> func, MAYBE list) {
		Maybe<MAYBE, ARG> maybe1 = (Maybe<MAYBE, ARG>) poly
				.specialze((Class<ARG>) null);
		final Maybe<MAYBE, RET> maybe2 = (Maybe<MAYBE, RET>) poly
				.specialze((Class<RET>) null);

		return maybe1.match(list, new Maybe.Case<MAYBE, ARG>() {
			@Override
			public MAYBE nothing() {
				return maybe2.nothing();
			}

			@Override
			public MAYBE just(ARG arg) {
				return maybe2.just(func.apply(arg));
			}
		});
	}
}
