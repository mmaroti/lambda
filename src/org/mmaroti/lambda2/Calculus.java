/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda2;

public abstract class Calculus {
	public static abstract class Thunk<DATA> {
		public abstract DATA execute(Runtime<DATA> runtime);
	}

	public static abstract class Closure<DATA> {
		public abstract DATA execute(Runtime<DATA> runtime, DATA argument);
	}
}
