/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

import java.util.*;

public abstract class Expression {
	public abstract int precedence();

	protected abstract void collect(Set<String> vars);

	public Set<String> variables() {
		HashSet<String> vars = new HashSet<String>();
		collect(vars);
		return vars;
	}

	public abstract boolean contains(String var);

	public Expression simplify() {
		return this;
	}

	public abstract Expression substitute(String var, Expression term);

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();
}
