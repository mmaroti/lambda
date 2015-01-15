/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public class ListNill<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>>
		extends ListValue<DATA_TYP, DATA_VAL> {

	protected ListNill(ListType<DATA_TYP> type) {
		super(type);
	}

	@Override
	public <RETURN> RETURN caseOf(ListMatch<DATA_TYP, DATA_VAL, RETURN> match) {
		return match.nill();
	}
}
