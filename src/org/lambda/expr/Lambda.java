/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

public class Lambda extends Expression {
	public final String name;
	public final Expression body;

	public Lambda(String name, Expression body) {
		this.name = name;
		this.body = body;
	}
}
