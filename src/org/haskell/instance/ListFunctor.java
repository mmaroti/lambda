/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.instance;

import org.haskell.data.*;
import org.haskell.data.List.Case;
import org.haskell.typeclass.*;

public class ListFunctor<DATA1, DATA2> extends
		Functor<DATA1, DATA2, List<DATA1>, List<DATA2>> {

	private final Case<List<DATA2>, DATA2> ctor = List.ctor();

	@Override
	public List<DATA2> fmap(final Func<DATA1, DATA2> func, List<DATA1> list) {
		return list.match(new List.Case<List<DATA2>, DATA1>() {
			@Override
			public List<DATA2> cons(DATA1 data, List<DATA1> rest) {
				return ctor.cons(func.apply(data), fmap(func, rest));
			}

			@Override
			public List<DATA2> nill() {
				return ctor.nill();
			}
		});
	}
}
