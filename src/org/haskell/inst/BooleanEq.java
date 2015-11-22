/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.inst;

import org.haskell.type.*;

public class BooleanEq extends Eq<Boolean, Boolean> {
	public static final BooleanEq INSTANCE = new BooleanEq();

	public BooleanEq() {
		super(BooleanBool.INSTANCE);
	}

	@Override
	public Boolean equ(Boolean data1, Boolean data2) {
		return data1.booleanValue() == data2.booleanValue();
	}
}
