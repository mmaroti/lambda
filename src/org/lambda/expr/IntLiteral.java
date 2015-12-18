/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.lambda.expr;

public class IntLiteral extends Expression {
	public final int value;

	public IntLiteral(int value) {
		this.value = value;
	}
}
