/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class ListFunctor<FUNC, DATA1, DATA2> extends
		Functor<FUNC, DATA1, DATA2, List<DATA1>, List<DATA2>> {

	public ListFunctor(Callable<FUNC, DATA1, DATA2> callable) {
		super(callable);
	}

	private final List.Case<List<DATA2>, DATA2> ctor = List.ctor();

	@Override
	public List<DATA2> fmap(final FUNC func, List<DATA1> list) {
		return list.match(new List.Case<List<DATA2>, DATA1>() {
			@Override
			public List<DATA2> cons(DATA1 data, List<DATA1> rest) {
				return ctor.cons(callable.call(func, data), fmap(func, rest));
			}

			@Override
			public List<DATA2> nill() {
				return ctor.nill();
			}
		});
	}
}
