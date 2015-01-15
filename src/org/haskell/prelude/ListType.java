/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public class ListType<DATA_TYP extends Type> extends Type {
	public final DATA_TYP data;

	public ListType(DATA_TYP data) {
		this.data = data;
	}
}
