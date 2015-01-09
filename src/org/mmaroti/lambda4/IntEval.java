/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda4;

public class IntEval<DATA> implements Runtime<IntEval<DATA>.Term> {
	final Runtime<DATA> sub;

	IntEval(Runtime<DATA> runtime) {
		this.sub = runtime;
	}

	public abstract class Term {
		abstract Term apply(Term argument);

		abstract DATA lift();
	}

	public class IntVal extends Term {
		final int value;

		IntVal(int value) {
			this.value = value;
		}

		@Override
		Term apply(Term argument) {
			throw new IllegalArgumentException();
		}

		@Override
		DATA lift() {
			return sub.literal(Integer.toString(value));
		}

		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}

	public class Data extends Term {
		final DATA data;

		Data(DATA data) {
			this.data = data;
		}

		@Override
		Term apply(Term argument) {
			return new Data(sub.apply(data, null));
		}

		@Override
		DATA lift() {
			return data;
		}

		@Override
		public String toString() {
			return data.toString();
		}
	}

	@Override
	public Term literal(String name) {
		try {
			int value = Integer.parseInt(name);
			if (name.equals(Integer.toString(value)))
				return new IntVal(value);
		} catch (NumberFormatException e) {
		}

		return new Data(sub.literal(name));
	}

	@Override
	public Term apply(Term function, Term argument) {
		return function.apply(argument);
	}

	public class MyClosure implements Closure<DATA> {
		final Closure<Term> closure;

		MyClosure(Closure<Term> closure) {
			this.closure = closure;
		}

		@Override
		public DATA evaluate(Runtime<DATA> runtime, DATA argument) {
			Data a = new Data(argument);
			IntEval<DATA> r = new IntEval<DATA>(runtime);
			Term t = closure.evaluate(r, a);
			return t.lift();
		}
	}

	@Override
	public Term closure(String var, Closure<Term> closure) {
		return new Data(sub.closure(var, new MyClosure(closure)));
	}

	@Override
	public Term conditional(Term test, Conditional<Term> conditional) {
		return null;
	}

	@Override
	public <TARGET> TARGET lift(Runtime<TARGET> runtime, Term data) {
		return sub.lift(runtime, data.lift());
	}
}
