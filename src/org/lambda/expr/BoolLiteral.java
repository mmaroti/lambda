/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

public class BoolLiteral extends Expression {
	public final boolean value;

	public BoolLiteral(boolean value) {
		this.value = value;
	}
}
