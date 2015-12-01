/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public abstract class Functor<FUNC, DATA1, DATA2, LIST1, LIST2> {
	public final Callable<FUNC, DATA1, DATA2> callable;

	public Functor(Callable<FUNC, DATA1, DATA2> callable) {
		this.callable = callable;
	}

	public abstract LIST2 fmap(FUNC func, LIST1 list);
}
