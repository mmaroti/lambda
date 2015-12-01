/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public abstract class Func<DATA, RET> {
	public abstract RET apply(DATA data);
}
