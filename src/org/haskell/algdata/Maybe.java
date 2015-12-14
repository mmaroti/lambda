/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.algdata;

public abstract class Maybe<OPT, DATA> {
	public abstract OPT nothing();

	public abstract OPT just(DATA data);

	public abstract <RET> RET match(OPT opt, Case<RET, DATA> expr);

	public abstract static class Case<RET, DATA> {
		public abstract RET nothing();

		public abstract RET just(DATA data);
	}
}
