/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class BooleanLogic extends Logic<Boolean> {
	public static final BooleanLogic INSTANCE = new BooleanLogic();

	public BooleanLogic() {
		super(true, false);
	}

	@Override
	public Boolean not(Boolean data) {
		return !data;
	}

	@Override
	public Boolean and(Boolean data1, Boolean data2) {
		return data1 && data2;
	}

	@Override
	public Boolean or(Boolean data1, Boolean data2) {
		return data1 || data2;
	}
}
