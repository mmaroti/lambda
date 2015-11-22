/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.inst;

import org.haskell.type.*;

public class IntegerEq extends Eq<Boolean, Integer> {
	public static final IntegerEq INSTANCE = new IntegerEq();

	public IntegerEq() {
		super(BooleanBool.INSTANCE);
	}

	@Override
	public Boolean equ(Integer data1, Integer data2) {
		return data1.intValue() == data2.intValue();
	}
}
