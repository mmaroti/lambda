/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda3;

public class LiteralCalculus extends Calculus {
	// ------- Function

	private static abstract class Function {
		final String name;

		Function(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

		abstract Object apply(Object arg);
	}

	private static abstract class BinaryFunc extends Function {
		BinaryFunc(String name) {
			super(name);
		}

		@Override
		Object apply(Object arg) {
			return new BinaryCurry(this, arg);
		}

		abstract Object apply(Object arg0, Object arg1);
	}

	private static class BinaryCurry extends Function {
		final BinaryFunc func;
		final Object first;

		BinaryCurry(BinaryFunc func, Object first) {
			super(func.name + "/" + first.toString());

			this.func = func;
			this.first = first;
		}

		@Override
		public Object apply(Object arg) {
			return func.apply(first, arg);
		}
	}

	// ------- Boolean

	public Object TRUE = Boolean.TRUE;
	public Object FALSE = Boolean.FALSE;

	private Object NOT = new Function("NOT") {
		@Override
		public Object apply(Object arg) {
			boolean a = (Boolean) arg;
			return new Boolean(!a);
		}
	};

	public Object AND = new BinaryFunc("AND") {
		@Override
		public Object apply(Object arg0, Object arg1) {
			boolean a = (Boolean) arg0;
			boolean b = (Boolean) arg1;
			return new Boolean(a && b);
		}
	};

	public Object OR = new BinaryFunc("OR") {
		@Override
		public Object apply(Object arg0, Object arg1) {
			boolean a = (Boolean) arg0;
			boolean b = (Boolean) arg1;
			return new Boolean(a || b);
		}
	};

	// ------- Integer

	public Object NEG = new Function("NEG") {
		@Override
		public Object apply(Object arg) {
			int a = (Integer) arg;
			return new Integer(-a);
		}
	};

	public Object PLUS = new BinaryFunc("PLUS") {
		@Override
		public Object apply(Object arg0, Object arg1) {
			int a = (Integer) arg0;
			int b = (Integer) arg1;
			return new Integer(a + b);
		}
	};

	public Object TIMES = new BinaryFunc("TIMES") {
		@Override
		public Object apply(Object arg0, Object arg1) {
			int a = (Integer) arg0;
			int b = (Integer) arg1;
			return new Integer(a * b);
		}
	};

	public Object EQU = new BinaryFunc("EQU") {
		@Override
		public Object apply(Object arg0, Object arg1) {
			int a = (Integer) arg0;
			int b = (Integer) arg1;
			return new Boolean(a == b);
		}
	};

	// ------- Cond

	public Object FIRST = new BinaryFunc("FIRST") {
		@Override
		public Object apply(Object arg0, Object arg1) {
			return arg0;
		}
	};

	public Object SECOND = new BinaryFunc("SECOND") {
		@Override
		public Object apply(Object arg0, Object arg1) {
			return arg1;
		}
	};

	public Object IF = new Function("IF") {
		@Override
		public Object apply(Object arg) {
			boolean a = (Boolean) arg;
			return a ? FIRST : SECOND;
		}
	};

	// ------- Calculus

	@Override
	Object literal(String lit) {
		try {
			int i = Integer.parseInt(lit);
			if (lit.equals(Integer.toString(i)))
				return new Integer(i);
		} catch (NumberFormatException exp) {
		}

		if (lit.equals("TRUE"))
			return TRUE;
		else if (lit.equals("FALSE"))
			return FALSE;
		else if (lit.equals("NOT"))
			return NOT;
		else if (lit.equals("AND"))
			return AND;
		else if (lit.equals("OR"))
			return OR;
		else if (lit.equals("NEG"))
			return NEG;
		else if (lit.equals("PLUS"))
			return PLUS;
		else if (lit.equals("TIMES"))
			return TIMES;
		else if (lit.equals("EQU"))
			return TIMES;

		throw new IllegalArgumentException("Unrecognized literal: " + lit);
	}

	@Override
	public Object apply(Object function, Object argument) {
		Function func = (Function) function;
		return func.apply(argument);
	}

	@Override
	Object variable(String namehint) {
		throw new UnsupportedOperationException();
	}

	@Override
	Object lambda(Object variable, Object expression) {
		throw new UnsupportedOperationException();
	}
}
