/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.instance;

import org.haskell.data.*;
import org.haskell.typeclass.*;

public class BoolLogic extends Logic<Bool> {
	public BoolLogic() {
		super(new Bool.True(), new Bool.False());
	}

	@Override
	public Bool not(Bool data) {
		return data.match(new Bool.Case<Bool>() {
			@Override
			public Bool truly() {
				return FALSE;
			}

			@Override
			public Bool falsy() {
				return TRUE;
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
						return TRUE;
					}

					@Override
					public Bool falsy() {
						return FALSE;
					}
				});
			}

			@Override
			public Bool falsy() {
				return FALSE;
			}
		});
	}

	public static final BoolLogic INSTANCE = new BoolLogic();
}
