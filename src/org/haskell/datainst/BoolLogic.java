/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.datainst;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class BoolLogic extends Logic<Bool> {
	public BoolLogic() {
		super(Bool.TYPE, new Bool.True(), new Bool.False());
	}

	@Override
	public Bool not(Bool data) {
		return data.match(new Bool.Case<Bool>() {
			@Override
			public Bool truly() {
				return fals;
			}

			@Override
			public Bool falsy() {
				return tru;
			}
		});
	}

	@Override
	public Bool and(Bool data1, final Bool data2) {
		return data1.match(new Bool.Case<Bool>() {
			@Override
			public Bool truly() {
				return data2.match(new Bool.Case<Bool>() {
					@Override
					public Bool truly() {
						return tru;
					}

					@Override
					public Bool falsy() {
						return fals;
					}
				});
			}

			@Override
			public Bool falsy() {
				return fals;
			}
		});
	}

	public static final BoolLogic INSTANCE = new BoolLogic();
}
