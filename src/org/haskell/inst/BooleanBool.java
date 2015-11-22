/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.inst;

import org.haskell.type.*;

public class BooleanBool extends Bool<Boolean> {
	public static final BooleanBool INSTANCE = new BooleanBool();

	public BooleanBool() {
		super(Boolean.TRUE, Boolean.FALSE);
	}

	@Override
	public Boolean not(Boolean data) {
		return !data.booleanValue();
	}

	@Override
	public Boolean and(Boolean data1, Boolean data2) {
		return data1.booleanValue() && data2.booleanValue();
	}

	@Override
	public Boolean or(Boolean data1, Boolean data2) {
		return data1.booleanValue() || data2.booleanValue();
	}
}
