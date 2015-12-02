/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public abstract class Term<DATA> {
	public abstract <RET> RET match(Case<RET, DATA> expr);

	public static abstract class Case<RET, DATA> {
		public abstract RET variable(String name);

		public abstract RET literal(DATA data);

		public abstract <ARG> RET apply(Term<Func<ARG, DATA>> func,
				Term<ARG> arg);
	}

	public static class Literal<DATA> extends Term<DATA> {
		private final DATA data;

		public Literal(DATA data) {
			this.data = data;
		}

		@Override
		public <RET> RET match(Case<RET, DATA> expr) {
			return expr.literal(data);
		}
	};

	public static class Variable<DATA> extends Term<DATA> {
		private final String name;

		public Variable(String name) {
			this.name = name;
		}

		@Override
		public <RET> RET match(org.haskell.data.Term.Case<RET, DATA> expr) {
			return expr.variable(name);
		}
	}

	public static class Apply<ARG, DATA> extends Term<DATA> {
		private final Term<Func<ARG, DATA>> func;
		private final Term<ARG> arg;

		public Apply(Term<Func<ARG, DATA>> func, Term<ARG> arg) {
			this.func = func;
			this.arg = arg;
		}

		@Override
		public <RET> RET match(Case<RET, DATA> expr) {
			return expr.apply(func, arg);
		}
	};
}
