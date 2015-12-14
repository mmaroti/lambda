/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class BooleanEq extends Equality<Boolean, Boolean> {
	public static final BooleanEq INSTANCE = new BooleanEq();

	public BooleanEq() {
		super(JavaLogic.INSTANCE);
	}

	@Override
	public Boolean equ(Boolean data1, Boolean data2) {
		return data1.booleanValue() == data2.booleanValue();
	}
}
