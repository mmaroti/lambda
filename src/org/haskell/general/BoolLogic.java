/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.general;

import org.haskell.algdata.*;
import org.haskell.typeclass.*;

public class BoolLogic<BOOL> extends Logic<BOOL> {
	private final Bool<BOOL> alg;

	public BoolLogic(Bool<BOOL> alg) {
		super(alg.truly(), alg.falsy());
		this.alg = alg;
	}

	@Override
	public BOOL not(BOOL data) {
		return alg.match(data, new Bool.Case<BOOL, BOOL>() {
			@Override
			public BOOL truly() {
				return FALSE;
			}

			@Override
			public BOOL falsy() {
				return TRUE;
			}
		});
	}

	@Override
	public BOOL and(BOOL data1, final BOOL data2) {
		return alg.match(data1, new Bool.Case<BOOL, BOOL>() {
			@Override
			public BOOL truly() {
				return data2;
			}

			@Override
			public BOOL falsy() {
				return FALSE;
			}
		});
	}

	@Override
	public BOOL or(BOOL data1, final BOOL data2) {
		return alg.match(data1, new Bool.Case<BOOL, BOOL>() {
			@Override
			public BOOL truly() {
				return TRUE;
			}

			@Override
			public BOOL falsy() {
				return data2;
			}
		});
	}
}
