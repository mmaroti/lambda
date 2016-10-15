/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lambda7;

import java.util.*;

public class Application extends Expression {
	public final Expression func;
	public final Expression arg;

	public Application(Expression func, Expression arg) {
		this.func = func;
		this.arg = arg;
	}

	@Override
	public int precedence() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Application) {
			Application app = (Application) obj;
			return func.equals(app.func) && arg.equals(app.arg);
		} else
			return false;
	}

	@Override
	public String toString() {
		String ret = "";

		boolean par = func.precedence() > precedence();
		if (par)
			ret += "(";
		ret += func;
		if (par)
			ret += ")";

		ret += " ";

		par = arg.precedence() >= precedence();
		if (par)
			ret += "(";
		ret += arg;
		if (par)
			ret += ")";

		return ret;
	}

	@Override
	public void collect(Set<String> vars) {
		func.collect(vars);
		arg.collect(vars);
	}

	@Override
	public Expression substitute(String var, Expression term) {
		Expression f = func.substitute(var, term);
		Expression a = arg.substitute(var, term);

		if (f == func && a == arg)
			return this;
		else
			return new Application(f, a);
	}

	@Override
	public boolean contains(String var) {
		return func.contains(var) || arg.contains(var);
	}

	@Override
	public Expression simplify() {
		Expression f = func.simplify();
		Expression a = arg.simplify();

		if (f instanceof Lambda) {
			Lambda lam = (Lambda) f;
			return lam.body.substitute(lam.name, a).simplify();
		} else if (f == func && a == arg)
			return this;
		else
			return new Application(f, a);
	}
}
