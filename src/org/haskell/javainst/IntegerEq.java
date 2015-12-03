/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class IntegerEq extends Equality<Boolean, Integer> {
	public static final IntegerEq INSTANCE = new IntegerEq();

	public IntegerEq() {
		super(BooleanLogic.INSTANCE);
	}

	@Override
	public Boolean equ(Integer data1, Integer data2) {
		return data1.intValue() == data2.intValue();
	}
}
