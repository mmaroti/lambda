/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda3;

public abstract class Calculus {
	abstract Object literal(String value);

	abstract Object apply(Object function, Object argument);

	Object apply(Object function, Object arg1, Object arg2) {
		return apply(apply(function, arg1), arg2);
	}

	abstract Object variable(String namehint);

	abstract Object lambda(Object variable, Object expression);
}
