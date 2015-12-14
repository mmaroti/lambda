/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.javainst;

import org.haskell.typeclass.*;

public class JavaLogic extends Logic<Boolean> {
	public static final JavaLogic INSTANCE = new JavaLogic();

	public JavaLogic() {
		super(Boolean.TRUE, Boolean.FALSE);
	}

	@Override
	public Boolean not(Boolean data) {
		return !data;
	}

	@Override
	public Boolean and(Boolean data1, Boolean data2) {
		return data1 && data2;
	}

	@Override
	public Boolean or(Boolean data1, Boolean data2) {
		return data1 || data2;
	}
}
