/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.algdata;

public abstract class Bool<DATA> {
	public abstract DATA truly();

	public abstract DATA falsy();

	public abstract <RET> RET match(DATA data, Case<RET, DATA> expr);

	public abstract static class Case<RET, DATA> {
		public abstract RET truly();

		public abstract RET falsy();
	}
}
