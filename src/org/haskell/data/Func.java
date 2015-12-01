/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public abstract class Func<ARG, RET> {
	public abstract RET apply(ARG arg);
}
