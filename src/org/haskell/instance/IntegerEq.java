/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.instance;

import org.haskell.typeclass.*;
import org.haskell.data.*;

public class IntegerEq extends Eq<Bool, Integer> {
	public static final IntegerEq INSTANCE = new IntegerEq();

	public IntegerEq() {
		super(BoolLogic.INSTANCE);
	}

	@Override
	public Bool equ(Integer data1, Integer data2) {
		return data1.intValue() == data2.intValue() ? logic.TRUE : logic.FALSE;
	}
}
