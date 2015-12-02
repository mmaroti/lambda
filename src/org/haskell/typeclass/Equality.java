/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public class Equality<DATA, BOOL> {
	public final Logic<BOOL> logic;

	public Equality(Logic<BOOL> logic) {
		this.logic = logic;
	}

	public BOOL equ(DATA data1, DATA data2) {
		return logic.not(neq(data1, data2));
	}

	public BOOL neq(DATA data1, DATA data2) {
		return logic.not(equ(data1, data2));
	}
}
