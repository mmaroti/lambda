/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public class BoolFalse extends BoolValue {
	protected BoolFalse(BoolType type) {
		super(type);
	}

	@Override
	public <RETURN> RETURN caseOf(BoolMatch<RETURN> match) {
		return match._false();
	}
}
