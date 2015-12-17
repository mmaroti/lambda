/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.typing;

public abstract class Type {
	public abstract Kind getKind();

	public static class Var extends Type {
		public final String id;
		public final Kind kind;

		public Var(String id, Kind kind) {
			this.id = id;
			this.kind = kind;
		}

		@Override
		public Kind getKind() {
			return kind;
		}
	}

	public static class Con extends Type {
		public final String id;
		public final Kind kind;

		public Con(String id, Kind kind) {
			this.id = id;
			this.kind = kind;
		}

		@Override
		public Kind getKind() {
			return kind;
		}
	}

	public static class Apl extends Type {
		public final Type fun;
		public final Type arg;

		public Apl(Type fun, Type arg) {
			this.fun = fun;
			this.arg = arg;
		}

		@Override
		public Kind getKind() {
			return fun.getKind().apply(arg.getKind());
		}
	}

	public static Type UNIT = new Con("()", Kind.STAR);
	public static Type CHAR = new Con("Char", Kind.STAR);
	public static Type INT = new Con("Int", Kind.STAR);

	public static Type LIST = new Con("List", Kind.FUN1);
	public static Type ARROW = new Con("->", Kind.FUN2);
	public static Type PAIR = new Con("(,)", Kind.FUN2);

	public static Type STRING = new Apl(LIST, CHAR);

	public static Type arrow(Type t1, Type t2) {
		return new Apl(new Apl(ARROW, t1), t2);
	}

	public static Type pair(Type t1, Type t2) {
		return new Apl(new Apl(PAIR, t1), t2);
	}
}
