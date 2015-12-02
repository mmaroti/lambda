/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class ListFunctor2 extends Functor2<List<?>> {
	@Override
	public <ARG, RET> List<RET> fmap(final Func<ARG, RET> func, List<?> list) {
		@SuppressWarnings("unchecked")
		List<ARG> list2 = (List<ARG>) list;

		return list2.match(new List.Case<List<RET>, ARG>() {
			@Override
			public List<RET> cons(ARG data, List<ARG> rest) {
				return new List.Cons<RET>(func.apply(data), fmap(func, rest));
			}

			@Override
			public List<RET> nill() {
				return new List.Nill<RET>();
			}
		});
	}
}
