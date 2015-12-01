/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public abstract class MaybeValue<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>>
		extends Value<MaybeType<DATA_TYP>> {

	public MaybeValue(MaybeType<DATA_TYP> type) {
		super(type);
	}

	public abstract <RETURN> RETURN caseOf(
			MaybeMatch<DATA_TYP, DATA_VAL, RETURN> match);
}
