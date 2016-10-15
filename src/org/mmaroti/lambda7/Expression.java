/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.lambda7;

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

	public static Expression read(Parser parser) {
		List<Expression> exprs = new ArrayList<Expression>();

		for (;;) {
			parser.spaces();
			int cp = parser.peek();
			if (cp == '(') {
				parser.next();
				exprs.add(read(parser));

				parser.spaces();
				cp = parser.peek();
				if (cp != ')')
					throw new IllegalArgumentException(
							"unclosed parenthesis at " + parser.location());
				parser.next();
			} else if (Character.isLetter(cp)) {
				String v = parser.identifier();
				exprs.add(new Variable(v));
			} else if (Character.isDigit(cp)) {
				Integer v = parser.integer();
				exprs.add(new Literal(v));
			} else if (cp == '\\') {
				parser.next();

			} else if (cp == ')' || cp == -1)
				break;
			else
				throw new IllegalArgumentException("invalid character at "
						+ parser.location());
		}

		if (exprs.isEmpty())
			throw new IllegalArgumentException("empty expression at "
					+ parser.location());

		Expression e = exprs.get(0);
		for (int i = 1; i < exprs.size(); i++)
			e = new Application(e, exprs.get(i));

		return e;
	}
}
