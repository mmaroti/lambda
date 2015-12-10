/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class IntegerRing extends Ring<Integer> {
	public static final IntegerRing INSTANCE = new IntegerRing();

	public IntegerRing() {
		super(IntegerType.INSTANCE, 0, 1);
	}

	@Override
	public Integer plus(Integer elem1, Integer elem2) {
		return elem1 + elem2;
	}

	@Override
	public Integer prod(Integer elem1, Integer elem2) {
		return elem1 * elem2;
	}

	@Override
	public Integer negate(Integer elem) {
		return -elem;
	}
}
