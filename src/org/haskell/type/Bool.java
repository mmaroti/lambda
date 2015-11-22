/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.type;

public abstract class Bool<DATA> extends TypeClass {
	public final DATA TRUE;
	public final DATA FALSE;

	public Bool(DATA t, DATA f) {
		this.TRUE = t;
		this.FALSE = f;
	}

	public abstract DATA not(DATA data);

	public DATA and(DATA data1, DATA data2) {
		return not(or(not(data1), not(data2)));
	}

	public DATA or(DATA data1, DATA data2) {
		return not(and(not(data1), not(data2)));
	}
}
