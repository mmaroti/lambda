/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datalike;

public abstract class ListCase<RET, LIST, ELEM> {
	public abstract RET nill();

	public abstract RET cons(ELEM head, LIST rest);
}
