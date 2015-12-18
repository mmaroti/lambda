/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

public class Application extends Expression {
	public final Expression func;
	public final Expression arg;

	public Application(Expression func, Expression arg) {
		this.func = func;
		this.arg = arg;
	}
}
