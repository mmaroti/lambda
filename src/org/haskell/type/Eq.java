/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.type;

public class Eq<BOOL, DATA> extends TypeClass {
	public final Bool<BOOL> bool;

	public Eq(Bool<BOOL> bool) {
		this.bool = bool;
	}

	public BOOL equ(DATA data1, DATA data2) {
		return bool.not(neq(data1, data2));
	}

	public BOOL neq(DATA data1, DATA data2) {
		return bool.not(equ(data1, data2));
	}
}
