/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.old.haskell.prelude;

public abstract class MaybeMatch<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>, RETURN> {
	public abstract RETURN nothing();

	public abstract RETURN just(DATA_VAL data);
}
