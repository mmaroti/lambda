/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.algdata.*;

public class JavaBool extends Bool<Boolean> {
	@Override
	public Boolean truly() {
		return Boolean.TRUE;
	}

	@Override
	public Boolean falsy() {
		return Boolean.FALSE;
	}

	@Override
	public <RET> RET match(Boolean data, Bool.Case<RET, Boolean> expr) {
		return data.booleanValue() ? expr.truly() : expr.falsy();
	}
}
