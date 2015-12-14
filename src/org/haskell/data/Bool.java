/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.data;

public abstract class Bool {
	public abstract <RET> RET match(Case<RET> match);

	public static abstract class Case<RET> {
		public abstract RET truly();

		public abstract RET falsy();
	}

	public static class True extends Bool {
		@Override
		public <RET> RET match(Case<RET> match) {
			return match.truly();
		}
	}

	public static class False extends Bool {
		@Override
		public <RET> RET match(Case<RET> match) {
			return match.falsy();
		}
	}
}
