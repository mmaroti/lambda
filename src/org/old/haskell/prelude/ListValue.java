/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public abstract class ListValue<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>>
		extends Value<ListType<DATA_TYP>> {

	public ListValue(ListType<DATA_TYP> type) {
		super(type);
	}

	public abstract <RETURN> RETURN caseOf(
			ListMatch<DATA_TYP, DATA_VAL, RETURN> match);
}
