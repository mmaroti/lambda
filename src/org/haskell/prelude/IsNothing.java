/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.haskell.prelude;

public class IsNothing<DATA_TYP extends Type, DATA_VAL extends Value<DATA_TYP>>
		extends
		FuncValue<MaybeType<DATA_TYP>, MaybeValue<DATA_TYP, DATA_VAL>, BoolType, BoolValue> {

	public IsNothing(FuncType<MaybeType<DATA_TYP>, BoolType> type) {
		super(type);
	}

	@Override
	public BoolValue apply(MaybeValue<DATA_TYP, DATA_VAL> arg) {
		return arg.caseOf(new MaybeMatch<DATA_TYP, DATA_VAL, BoolValue>() {
			@Override
			public BoolValue nothing() {
				return new BoolTrue(type.ret);
			}

			@Override
			public BoolValue just(DATA_VAL data) {
				return new BoolFalse(type.ret);
			}
		});
	}
}
