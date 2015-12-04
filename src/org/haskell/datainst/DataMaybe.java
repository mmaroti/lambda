/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.typeclass.*;

public class DataMaybe<DATA> extends Maybe<DataMaybe.Data<DATA>, DATA> {
	public abstract static class Data<DATA> {
		abstract <RET> RET match(Case<RET, DATA> expr);
	}

	private static class Nothing<DATA> extends Data<DATA> {
		@Override
		<RET> RET match(Case<RET, DATA> expr) {
			return expr.nothing();
		}
	}

	private static class Just<DATA> extends Data<DATA> {
		private final DATA data;

		public Just(DATA data) {
			this.data = data;
		}

		@Override
		<RET> RET match(Case<RET, DATA> expr) {
			return expr.just(data);
		}
	}

	@Override
	public <RET> RET match(Data<DATA> opt, Case<RET, DATA> expr) {
		return opt.match(expr);
	}

	@Override
	public Data<DATA> just(DATA data) {
		return new Just<DATA>(data);
	}

	@Override
	public Data<DATA> nothing() {
		return new Nothing<DATA>();
	}
}
