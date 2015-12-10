/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.typeclass;

import org.haskell.data.*;

public abstract class Logic<BOOL> {
	public final Type<BOOL> type;
	public final BOOL tru;
	public final BOOL fals;

	public final Func<BOOL, BOOL> NOT = new Func<BOOL, BOOL>() {
		@Override
		public BOOL apply(BOOL arg) {
			return not(arg);
		}
	};

	public final Func<BOOL, Func<BOOL, BOOL>> AND = new Func<BOOL, Func<BOOL, BOOL>>() {
		@Override
		public Func<BOOL, BOOL> apply(final BOOL arg1) {
			return new Func<BOOL, BOOL>() {
				@Override
				public BOOL apply(BOOL arg2) {
					return and(arg1, arg2);
				}
			};
		}
	};

	public final Func<BOOL, Func<BOOL, BOOL>> OR = new Func<BOOL, Func<BOOL, BOOL>>() {
		@Override
		public Func<BOOL, BOOL> apply(final BOOL arg1) {
			return new Func<BOOL, BOOL>() {
				@Override
				public BOOL apply(BOOL arg2) {
					return or(arg1, arg2);
				}
			};
		}
	};

	public Logic(Type<BOOL> type, BOOL tru, BOOL fals) {
		this.type = type;
		this.tru = tru;
		this.fals = fals;
	}

	public abstract BOOL not(BOOL bool);

	public BOOL and(BOOL bool1, BOOL bool2) {
		return not(or(not(bool1), not(bool2)));
	}

	public BOOL or(BOOL bool1, BOOL bool2) {
		return not(and(not(bool1), not(bool2)));
	}
}
