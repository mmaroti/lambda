/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datalike;

public abstract class ListLike<LIST, ELEM> extends ListCase<LIST, LIST, ELEM> {
	public abstract <RET> RET match(LIST list, ListCase<RET, LIST, ELEM> cas);
}
