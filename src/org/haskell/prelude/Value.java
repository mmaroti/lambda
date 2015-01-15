/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public abstract class Value<TYPE extends Type> {
	public final TYPE type;

	protected Value(TYPE type) {
		this.type = type;
	}
}
