/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datalike;

public abstract class BoolCase<BOOL> {
	public abstract BOOL truly();

	public abstract BOOL falsy();
}
