/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

import org.haskell.data.*;

public abstract class Functor<TYPE extends TypeClass, LIST> extends TypeClass {
	public final Polymorf<TYPE> poly;

	public Functor(Polymorf<TYPE> poly) {
		this.poly = poly;
	}

	public abstract <ARG, RET> LIST fmap(Func<ARG, RET> func, LIST list);
}
