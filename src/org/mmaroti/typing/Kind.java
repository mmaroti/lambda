/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.typing;

public abstract class Kind {
	public abstract Kind apply(Kind kind);

	@Override
	public abstract String toString();

	public static class Star extends Kind {
		@Override
		public Kind apply(Kind kind) {
			throw new IllegalStateException("kind not applicable");
		}

		@Override
		public String toString() {
			return "*";
		}
	}

	public static class Fun extends Kind {
		public final Kind arg;
		public final Kind res;

		public Fun(Kind arg, Kind res) {
			this.arg = arg;
			this.res = res;
		}

		@Override
		public Kind apply(Kind kind) {
			if (kind.equals(arg))
				return res;
			else
				throw new IllegalStateException("invalid kind argument");
		}

		@Override
		public String toString() {
			return arg + "->" + res;
		}
	}

	public static Kind STAR = new Star();
	public static Kind FUN1 = new Fun(STAR, STAR);
	public static Kind FUN2 = new Fun(STAR, FUN1);
}
