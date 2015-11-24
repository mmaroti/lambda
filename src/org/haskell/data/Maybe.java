/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public abstract class Maybe<DATA> {
	public abstract <RET> RET match(Case<RET, DATA> match);

	public static abstract class Case<RET, DATA> {
		public abstract RET nothing();

		public abstract RET just(DATA elem);
	};

	public static class Nothing<DATA> extends Maybe<DATA> {
		@Override
		public <RET> RET match(Maybe.Case<RET, DATA> match) {
			return match.nothing();
		}
	}

	public static class Just<DATA> extends Maybe<DATA> {
		private final DATA data;

		public Just(DATA data) {
			this.data = data;
		}

		@Override
		public <RET> RET match(Maybe.Case<RET, DATA> match) {
			return match.just(data);
		}
	}
}
