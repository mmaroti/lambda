/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class ListEq<BOOL, DATA> extends Equality<BOOL, List<DATA>> {
	private final Equality<BOOL, DATA> eq;

	public ListEq(Equality<BOOL, DATA> eq) {
		super(eq.logic);
		this.eq = eq;
	}

	@Override
	public BOOL equ(List<DATA> data1, final List<DATA> data2) {
		return data1.match(new List.Case<BOOL, DATA>() {
			@Override
			public BOOL cons(final DATA elem1, final List<DATA> rest1) {
				return data2.match(new List.Case<BOOL, DATA>() {
					@Override
					public BOOL cons(DATA elem2, List<DATA> rest2) {
						return logic.and(eq.equ(elem1, elem2),
								equ(rest1, rest2));
					}

					@Override
					public BOOL nill() {
						return logic.fals;
					}
				});
			}

			@Override
			public BOOL nill() {
				return data2.match(new List.Case<BOOL, DATA>() {
					@Override
					public BOOL cons(DATA data, List<DATA> rest) {
						return logic.fals;
					}

					@Override
					public BOOL nill() {
						return logic.tru;
					}
				});
			}
		});
	}
}
