/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda2;

public abstract class Runtime<DATA> {
	abstract DATA lift(Object object);

	abstract DATA lambda(Calculus.Closure<DATA> closure);

	abstract DATA apply(DATA function, Calculus.Thunk<DATA> argument);
}
