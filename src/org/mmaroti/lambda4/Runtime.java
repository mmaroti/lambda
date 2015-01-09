/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda4;

public interface Runtime<DATA> {
	public DATA literal(String name);

	public DATA apply(DATA function, DATA argument);

	public interface Closure<DATA> {
		public DATA evaluate(Runtime<DATA> runtime, DATA argument);
	}

	public DATA closure(String var, Closure<DATA> closure);

	public interface Conditional<DATA> {
		public DATA evaluate(boolean argument);
	}

	public DATA conditional(DATA test, Conditional<DATA> conditional);

	public <TARGET> TARGET lift(Runtime<TARGET> runtime, DATA data);
}
