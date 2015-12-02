/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

import org.haskell.data.*;

public abstract class Ring<ELEM> {
	public final ELEM ZERO;

	public final ELEM UNIT;

	public final Func<ELEM, ELEM> NEGATE = new Func<ELEM, ELEM>() {
		@Override
		public ELEM apply(ELEM arg) {
			return negate(arg);
		}
	};

	public final Func<ELEM, Func<ELEM, ELEM>> PLUS = new Func<ELEM, Func<ELEM, ELEM>>() {
		@Override
		public Func<ELEM, ELEM> apply(final ELEM arg1) {
			return new Func<ELEM, ELEM>() {
				@Override
				public ELEM apply(ELEM arg2) {
					return plus(arg1, arg2);
				}
			};
		}
	};

	public final Func<ELEM, Func<ELEM, ELEM>> PROD = new Func<ELEM, Func<ELEM, ELEM>>() {
		@Override
		public Func<ELEM, ELEM> apply(final ELEM arg1) {
			return new Func<ELEM, ELEM>() {
				@Override
				public ELEM apply(ELEM arg2) {
					return prod(arg1, arg2);
				}
			};
		}
	};

	public Ring(ELEM zero, ELEM unit) {
		this.ZERO = zero;
		this.UNIT = unit;
	}

	public abstract ELEM negate(ELEM elem);

	public abstract ELEM plus(ELEM elem1, ELEM elem2);

	public abstract ELEM prod(ELEM elem1, ELEM elem2);
}
