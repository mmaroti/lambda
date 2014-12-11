/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda2;

public abstract class Runtime<DATA> {
	abstract DATA compile(Closure<DATA> script);

	abstract DATA apply(DATA function, DATA argument);
}
