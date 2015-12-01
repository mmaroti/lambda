/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public class MaybeJust<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>>
		extends MaybeValue<DATA_TYP, DATA_VAL> {
	public final DATA_VAL data;

	public MaybeJust(DATA_VAL data, MaybeType<DATA_TYP> type) {
		super(type);
		this.data = data;
	}

	@Override
	public <RETURN> RETURN caseOf(MaybeMatch<DATA_TYP, DATA_VAL, RETURN> match) {
		return match.just(data);
	}
}
