/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

import org.haskell.data.*;

public abstract class Functor<MONO, LIST> {
	public final Polymorf<MONO> poly;

	public Functor(Polymorf<MONO> poly) {
		this.poly = poly;
	}

	public abstract <ARG, RET> LIST fmap(Func<ARG, RET> func, LIST list);
}
