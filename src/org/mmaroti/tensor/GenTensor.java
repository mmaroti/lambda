/**
 *	Copyright (C) Miklos Maroti, 2015
 */

package org.mmaroti.tensor;

public abstract class GenTensor<ELEM, TENSOR> {
	public abstract int[] getShape(TENSOR tensor);

	public abstract TENSOR create(int[] shape, Iterable<ELEM> elems);

	public abstract TENSOR reshape(TENSOR tensor, int[] map);

	public int getOrder(TENSOR tensor) {
		return getShape(tensor).length;
	}

	public int getDim(TENSOR tensor, int index) {
		return getShape(tensor)[index];
	}

	public FixTensor<ELEM, TENSOR> fixShape(int[] shape) {
		final GenTensor<ELEM, TENSOR> gen = this;
		return new FixTensor<ELEM, TENSOR>(shape) {
			@Override
			public TENSOR create(Iterable<ELEM> elems) {
				return gen.create(getShape(), elems);
			}

			@Override
			public TENSOR reshape(TENSOR tensor, int[] map) {
				return gen.reshape(tensor, map);
			}
		};
	}
}
