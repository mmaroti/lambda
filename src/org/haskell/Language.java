/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell;

public class Language {
	public abstract static class Obj {
		public final Obj type;

		private Obj(Obj type) {
			this.type = type;
		}

		public String show() {
			if (type == null)
				return toString();
			else
				return toString() + " :: " + type;
		}

		public abstract Obj reduce(Var var, Obj obj);

		public Obj apply(Obj arg) {
			throw new IllegalArgumentException();
		}
	}

	public static final class Var extends Obj {
		public final String name;

		public Var(Obj type, String name) {
			super(type);
			this.name = name;
		}

		public String toString() {
			return name;
		}

		@Override
		public Obj reduce(Var var, Obj obj) {
			if (var.equals(this))
				return obj;
			else if (var.equals(type))
				return new Var(obj, name);
			else
				return this;
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof Var) {
				Var o = (Var) other;
				return name.equals(o.name);
			}
			return false;
		}
	}

	public static final class Lit extends Obj {
		public final String name;

		public Lit(Obj type, String name) {
			super(type);
			this.name = name;
		}

		public String toString() {
			return name;
		}

		@Override
		public Obj reduce(Var var, Obj obj) {
			// TODO: can this happen?
			if (var.equals(type))
				return new Lit(obj, name);
			else
				return this;
		}
	}

	public static final class App extends Obj {
		public final Obj fun;
		public final Obj arg;

		public App(Obj type, Obj fun, Obj arg) {
			super(type);
			this.fun = fun;
			this.arg = arg;
		}

		public String toString() {
			return "(" + fun + " " + arg + ")";
		}

		@Override
		public Obj reduce(Var var, Obj obj) {
			Obj t = type.reduce(var, obj);
			Obj f = fun.reduce(var, obj);
			Obj a = arg.reduce(var, obj);

			if (t == type && f == fun && a == arg)
				return this;
			else
				return new App(t, f, a);
		}
	}

	public static final class Lam extends Obj {
		public final Var var;
		public final Obj expr;

		public Lam(Var var, Obj expr) {
			super(new Dat(null, "->", var.type, expr.type));
			this.var = var;
			this.expr = expr;
		}

		public String toString() {
			return "(\\" + var + " -> " + expr + ")";
		}

		@Override
		public Obj reduce(Var var, Obj obj) {
			if (var.equals(this.var))
				return this;
			else {
				Var v = (Var) this.var.reduce(var, obj);
				return new Lam(v, expr.reduce(var, obj));
			}
		}

		@Override
		public Obj apply(Obj arg) {
			if (!arg.type.equals(var.type))
				throw new IllegalArgumentException();

			return expr.reduce(var, arg);
		}
	}

	public static class Dat extends Obj {
		public final String name;
		public final Obj[] subs;

		public Dat(Obj type, String name) {
			super(type);
			this.name = name;
			this.subs = new Obj[] {};
		}

		public Dat(Obj type, String name, Obj sub1) {
			super(type);
			this.name = name;
			this.subs = new Obj[] { sub1 };
		}

		public Dat(Obj type, String name, Obj sub1, Obj sub2) {
			super(type);
			this.name = name;
			this.subs = new Obj[] { sub1, sub2 };
		}

		public Dat(Obj type, String name, Obj[] subs) {
			super(type);
			this.name = name;
			this.subs = subs;
		}

		public String toString() {
			String s = name;
			s += "[";
			for (int i = 0; i < subs.length; ++i) {
				if (i != 0)
					s += ",";
				s += subs[i];
			}
			s += "]";
			return s;
		}

		@Override
		public Obj reduce(Var var, Obj obj) {
			Obj t = type.reduce(var, obj);

			Obj[] s = subs.clone();
			for (int i = 0; i < subs.length; i++)
				s[i] = subs[i].reduce(var, obj);

			return new Dat(t, name, s);
		}
	}

	public static Obj TYPE = new Lit(null, "*");

	private static Var A = new Var(TYPE, "a");
	private static Var B = new Var(TYPE, "b");
	public static Obj ARROW = new Lam(A, new Lam(B, new Dat(null, "->", A, B)));
}
