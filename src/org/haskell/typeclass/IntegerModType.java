/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public class IntegerModType extends Type implements
		MonoidClass<IntegerModType, Integer> {
	public final int modulo;

	public IntegerModType(int modulo) {
		this.modulo = modulo;
	}

	@Override
	public Elem<IntegerModType, Integer> unit() {
		return new Elem<IntegerModType, Integer>(this, 0);
	}

	@Override
	public Elem<IntegerModType, Integer> prod(Elem<IntegerModType, Integer> a,
			Elem<IntegerModType, Integer> b) {
		assert a.type == this && b.type == this;
		return new Elem<IntegerModType, Integer>(this, a.value + b.value);
	}
}
