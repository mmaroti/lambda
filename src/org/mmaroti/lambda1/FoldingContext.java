/**
 *	Copyright (C) Miklos Maroti, 2014
 */

package org.mmaroti.lambda1;

import org.mmaroti.lambda1.Calculus.*;
import org.mmaroti.lambda1.LambdaCalculus.*;

public class FoldingContext extends Context {
	public Data getValue(Variable var) {
		return new Fragment(var);
	}

	public Data closure(Variable variable, Term expression) {
		return new Fragment(new Abstraction(variable, expression));
	}

	public String toString() {
		return "folding";
	}

	public Data localize(Literal literal) {
		return new Fragment(literal);
	}

	public static class Frame extends FoldingContext {
		public final FoldingContext parent;
		public final Variable variable;
		public final Fragment value;

		public Frame(FoldingContext parent, Variable variable, Fragment value) {
			this.parent = parent;
			this.variable = variable;
			this.value = value;
		}

		public Data getValue(Variable var) {
			if (var == variable)
				return value;
			else
				return parent.getValue(var);
		}

		public String toString() {
			Context c = this;
			String s = ")";

			for (;;) {
				if (c instanceof Frame) {
					Frame f = (Frame) c;
					s = " " + f.variable + "=" + f.value + s;
					c = f.parent;
				} else {
					s = c.toString() + s;
					break;
				}
			}

			return "(" + s;
		}
	}

	public static class Fragment extends Data {
		public final Term term;

		public Fragment(Term term) {
			this.term = term;
		}

		public String toString() {
			return "(frag " + term.toString() + ")";
		}

		public Data call(Data argument) {
			Term arg = ((Fragment) argument).term;

			if (term instanceof Literal && arg instanceof Literal) {
				Data func = ((Literal) term).data;
				Data data = ((Literal) arg).data;
				return new Fragment(new Literal(func.call(data)));
			} else if (term instanceof Abstraction && arg instanceof Literal) {
			}

			return new Fragment(new Application(term, arg));
		}
	}

	public static final FoldingContext INSTANCE = new FoldingContext();

	public static Term evaluate(Term term) {
		Data data = term.evaluate(INSTANCE);
		return ((Fragment) data).term;
	}
}
