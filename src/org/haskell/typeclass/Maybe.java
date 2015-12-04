/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

public abstract class Maybe<MAYBE, DATA> extends TypeClass {
	@SuppressWarnings("unchecked")
	public <DATA2> Maybe<MAYBE, DATA2> specialize() {
		return (Maybe<MAYBE, DATA2>) this;
	}

	public abstract MAYBE nothing();

	public abstract MAYBE just(DATA data);

	public abstract <RET> RET match(MAYBE opt, Case<RET, DATA> expr);

	public abstract static class Case<RET, DATA> {
		public abstract RET nothing();

		public abstract RET just(DATA data);
	}
}
