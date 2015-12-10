/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public class Type<DATA> {
	public final Class<DATA> clas;

	public Type(Class<DATA> clas) {
		this.clas = clas;
	}
}
