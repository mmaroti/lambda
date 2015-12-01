/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.lambda;

public abstract class Term<DATA> {
	public abstract Term<DATA> evaluate();
}
