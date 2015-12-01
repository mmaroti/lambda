/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public abstract class Ring<ELEM> {
	public final ELEM ZERO;

	public final ELEM UNIT;

	public Ring(ELEM zero, ELEM unit) {
		this.ZERO = zero;
		this.UNIT = unit;
	}

	public abstract ELEM plus(ELEM elem1, ELEM elem2);

	public abstract ELEM prod(ELEM elem1, ELEM elem2);
}
