/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambdax;

public abstract class Term<DATA> {
	public abstract Term<DATA> simplify();
}