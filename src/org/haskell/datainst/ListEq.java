/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class ListEq<BOOL, DATA> extends Equality<List<DATA>, BOOL> {
	private final Equality<DATA, BOOL> eq;

	public ListEq(Logic<BOOL> logic, Equality<DATA, BOOL> eq) {
		super(logic);
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
						return logic.FALSE;
					}
				});
			}

			@Override
			public BOOL nill() {
				return data2.match(new List.Case<BOOL, DATA>() {
					@Override
					public BOOL cons(DATA data, List<DATA> rest) {
						return logic.FALSE;
					}

					@Override
					public BOOL nill() {
						return logic.TRUE;
					}
				});
			}
		});
	}
}
