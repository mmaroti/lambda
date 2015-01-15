/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public class MaybeType<DATA_TYP extends Type> extends Type {
	public final DATA_TYP data;

	public MaybeType(DATA_TYP data) {
		this.data = data;
	}
}
