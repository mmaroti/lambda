/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class ObjectOpt<DATA> extends Optional<DATA, DATA> {
	@Override
	public DATA nothing() {
		return null;
	}

	@Override
	public DATA just(DATA data) {
		if (data == null)
			throw new IllegalArgumentException();

		return data;
	}

	@Override
	public <RET> RET match(DATA opt, Case<RET, DATA> expr) {
		if (opt == null)
			return expr.nothing();
		else
			return expr.just(opt);
	}
}
