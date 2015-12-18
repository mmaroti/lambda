/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

public class Variable extends Expression {
	public final String name;

	public Variable(String name) {
		this.name = name;
	}
}
