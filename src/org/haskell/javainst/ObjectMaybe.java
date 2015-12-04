/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class ObjectMaybe extends Maybe<Object, Object> {
	@Override
	public Object nothing() {
		return null;
	}

	@Override
	public Object just(Object data) {
		if (data == null)
			throw new IllegalArgumentException();

		return data;
	}

	@Override
	public <RET> RET match(Object opt, Case<RET, Object> expr) {
		if (opt == null)
			return expr.nothing();
		else
			return expr.just(opt);
	}
}
