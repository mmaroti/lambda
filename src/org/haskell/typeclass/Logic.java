/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public abstract class Logic<BOOL> {
	public final BOOL TRUE;
	public final BOOL FALSE;

	public Logic(BOOL t, BOOL f) {
		this.TRUE = t;
		this.FALSE = f;
	}

	public abstract BOOL not(BOOL bool);

	public BOOL and(BOOL bool1, BOOL bool2) {
		return not(or(not(bool1), not(bool2)));
	}

	public BOOL or(BOOL bool1, BOOL bool2) {
		return not(and(not(bool1), not(bool2)));
	}
}
