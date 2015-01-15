/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public abstract class BoolMatch<RETURN> {
	public abstract RETURN _false();

	public abstract RETURN _true();
}
