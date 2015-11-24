/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.instance;

import org.haskell.typeclass.*;
import org.haskell.data.*;

public class BoolEq extends Eq<Bool, Boolean> {
	public static final BoolEq INSTANCE = new BoolEq();

	public BoolEq() {
		super(BoolLogic.INSTANCE);
	}

	@Override
	public Bool equ(Boolean data1, Boolean data2) {
		return data1.booleanValue() == data2.booleanValue() ? logic.TRUE
				: logic.FALSE;
	}
}
