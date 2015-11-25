/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

import org.haskell.data.*;

public abstract class Functor<DATA1, DATA2, LIST1, LIST2> extends TypeClass {
	public abstract LIST2 fmap(Func<DATA1, DATA2> func, LIST1 list);
}
