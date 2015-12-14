/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public abstract class Ring<ELEM> {
	public final ELEM zero;
	public final ELEM unit;

	public Ring(ELEM zero, ELEM unit) {
		this.zero = zero;
		this.unit = unit;
	}

	public abstract ELEM negate(ELEM elem);

	public abstract ELEM plus(ELEM elem1, ELEM elem2);

	public abstract ELEM prod(ELEM elem1, ELEM elem2);
}
