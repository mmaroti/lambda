/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public class ListCons<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>>
		extends ListValue<DATA_TYP, DATA_VAL> {

	public final DATA_VAL head;
	public final ListValue<DATA_TYP, DATA_VAL> tail;

	public ListCons(DATA_VAL head, ListValue<DATA_TYP, DATA_VAL> tail) {
		super(tail.type);
		this.head = head;
		this.tail = tail;
	}

	@Override
	public <RETURN> RETURN caseOf(ListMatch<DATA_TYP, DATA_VAL, RETURN> match) {
		return match.cons(head, tail);
	}
}
