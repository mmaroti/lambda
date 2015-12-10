/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class BooleanType extends Type<Boolean> {
	public BooleanType() {
		super(Boolean.class);
	}

	public static final BooleanType INSTANCE = new BooleanType();
}
