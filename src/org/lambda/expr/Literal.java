/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

import java.util.*;

public class Literal extends Expression {
	public final Object value;

	public Literal(Object value) {
		assert value != null;
		this.value = value;
	}

	@Override
	public int precedence() {
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Literal) {
			Literal lit = (Literal) obj;
			return value.equals(lit.value);
		} else
			return false;
	}

	@Override
	public String toString() {
		if (value instanceof String)
			return "\"" + value + "\"";
		else
			return value.toString();
	}

	@Override
	public void collect(Set<String> vars) {
	}

	@Override
	public Expression substitute(String var, Expression term) {
		return this;
	}

	@Override
	public boolean contains(String var) {
		return false;
	}
}
