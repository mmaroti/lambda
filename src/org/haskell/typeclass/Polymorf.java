/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public abstract class Polymorf<TYPE extends TypeClass> {
	public abstract <DATA> TYPE specialze(Class<DATA> type);
}
