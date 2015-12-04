/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

import org.haskell.data.*;

public abstract class Functor2<LIST> extends TypeClass {
	public abstract <ARG, RET> LIST fmap(Func<ARG, RET> func, LIST list);
}
