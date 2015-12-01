/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public class MaybeNothing<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>>
		extends MaybeValue<DATA_TYP, DATA_VAL> {
	protected MaybeNothing(MaybeType<DATA_TYP> type) {
		super(type);
	}

	@Override
	public <RETURN> RETURN caseOf(MaybeMatch<DATA_TYP, DATA_VAL, RETURN> match) {
		return match.nothing();
	}
}
