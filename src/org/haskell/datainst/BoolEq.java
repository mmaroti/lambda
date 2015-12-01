/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.typeclass.*;
import org.haskell.data.*;

public class BoolEq extends Equality<Bool, Bool> {
	public static final BoolEq INSTANCE = new BoolEq();

	public BoolEq() {
		super(BoolLogic.INSTANCE);
	}

	@Override
	public Bool equ(Bool data1, final Bool data2) {
		return data1.match(new Bool.Case<Bool>() {
			@Override
			public Bool truly() {
				return data2;
			}

			@Override
			public Bool falsy() {
				return logic.not(data2);
			}
		});
	}
}
