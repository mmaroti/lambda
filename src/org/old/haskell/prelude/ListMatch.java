/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public abstract class ListMatch<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>, RETURN> {
	public abstract RETURN nill();

	public abstract RETURN cons(DATA_VAL head,
			ListValue<DATA_TYP, DATA_VAL> tail);
}
