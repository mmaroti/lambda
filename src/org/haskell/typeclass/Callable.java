/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public abstract class Callable<FUNC, ARG, RET> extends TypeClass {
	public abstract RET call(FUNC func, ARG arg);
}
