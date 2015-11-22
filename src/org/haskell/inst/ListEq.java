/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.inst;

import org.haskell.data.*;
import org.haskell.type.*;

public class ListEq<BOOL, DATA> extends Eq<BOOL, List<DATA>> {
	private final Eq<BOOL, DATA> eq;

	public ListEq(Bool<BOOL> bool, Eq<BOOL, DATA> eq) {
		super(bool);
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
						return bool.and(eq.equ(elem1, elem2), equ(rest1, rest2));
					}

					@Override
					public BOOL nill() {
						return bool.FALSE;
					}
				});
			}

			@Override
			public BOOL nill() {
				return data2.match(new List.Case<BOOL, DATA>() {
					@Override
					public BOOL cons(DATA data, List<DATA> rest) {
						return bool.FALSE;
					}

					@Override
					public BOOL nill() {
						return bool.TRUE;
					}
				});
			}
		});
	}
}
