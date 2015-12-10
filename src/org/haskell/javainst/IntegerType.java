/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class IntegerType extends Type<Integer> {
	public IntegerType() {
		super(Integer.class);
	}

	public static final IntegerType INSTANCE = new IntegerType();
}
