/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public abstract class BoolValue extends Value<BoolType> {
	public BoolValue(BoolType type) {
		super(type);
	}

	public abstract <RETURN> RETURN caseOf(BoolMatch<RETURN> match);
}
