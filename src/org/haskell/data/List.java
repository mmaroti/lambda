/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public abstract class List<DATA> {
	public abstract <RET> RET match(Case<RET, DATA> match);

	public static abstract class Case<RET, DATA> {
		public abstract RET cons(DATA data, List<DATA> rest);

		public abstract RET nill();
	}

	public static <DATA> Case<List<DATA>, DATA> ctor() {
		return new Case<List<DATA>, DATA>() {
			@Override
			public List<DATA> cons(DATA data, List<DATA> rest) {
				return new Cons<DATA>(data, rest);
			}

			@Override
			public List<DATA> nill() {
				return new Nill<DATA>();
			}
		};
	}

	public static class Cons<DATA> extends List<DATA> {
		private final DATA data;
		private final List<DATA> rest;

		public Cons(DATA data, List<DATA> rest) {
			this.data = data;
			this.rest = rest;
		}

		@Override
		public <RET> RET match(Case<RET, DATA> match) {
			return match.cons(data, rest);
		}
	}

	public static class Nill<DATA> extends List<DATA> {
		@Override
		public <RET> RET match(Case<RET, DATA> match) {
			return match.nill();
		}
	}
}
