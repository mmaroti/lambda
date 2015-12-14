/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.algdata.*;

public class BoolDat extends Bool<BoolDat.Data> {
	public static abstract class Data {
		public abstract <RET> RET match(Bool.Case<RET, Data> expr);
	}

	private static Data TRUE = new Data() {
		@Override
		public <RET> RET match(Bool.Case<RET, Data> expr) {
			return expr.truly();
		}
	};

	private static Data FALSE = new Data() {
		@Override
		public <RET> RET match(Bool.Case<RET, Data> expr) {
			return expr.falsy();
		}
	};

	@Override
	public Data truly() {
		return TRUE;
	}

	@Override
	public Data falsy() {
		return FALSE;
	}

	@Override
	public <RET> RET match(Data data, Bool.Case<RET, Data> expr) {
		return data.match(expr);
	}
}
