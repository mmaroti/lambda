{-# LANGUAGE MultiParamTypeClasses, FlexibleInstances, UndecidableInstances #-}

module Lambda where

import Prelude ()
import qualified Prelude

-- TYPE CLASSES

class Logic a where
	true :: a
	false :: a
	not :: a -> a
	and :: a -> a -> a
	and x y = not (or (not x) (not y))
	or :: a -> a -> a
	or x y = not (and (not x) (not y))

class Logic b => Equality b a where
	equ :: a -> a -> b
	equ x y = not (neq x y)
	neq :: a -> a -> b
	neq x y = not (equ x y)

class Ring a where
	zero :: a
	unit :: a
	negate :: a -> a
	plus :: a -> a -> a
	prod :: a -> a -> a

class ListCase t where
	lcase :: t a -> forall b. (a -> t a -> b) -> b -> b

class Functor f where
	fmap :: (a -> b) -> f a -> f b

-- GENERIC INSTANCES

instance Logic a => Ring a where
	zero = false
	unit = true
	negate = Prelude.id
	plus = \x y -> or (and x (not y)) (and (not x) y)
	prod = and

-- HASKELL INSTANCES

instance Logic Prelude.Bool where
	true = Prelude.True
	false = Prelude.False
	not = Prelude.not
	and = (Prelude.&&)
	or = (Prelude.||)

instance Ring Prelude.Int where
	zero = 0
	unit = 1
	negate = (Prelude.negate)
	plus = (Prelude.+)
	prod = (Prelude.*)

instance Prelude.Eq a => Equality Prelude.Bool a where
	equ = (Prelude.==)
	neq = (Prelude./=)

-- DATA DECLARATIONS

data Bool
	= False
	| True
	deriving Prelude.Show

data List a
	= Cons a (List a)
	| Nill
	deriving Prelude.Show

-- DATA INSTANCES

instance Logic Bool where
	true = True
	false = False
	not = \x -> case x of
		False -> True
		True -> False
	and = \x -> case x of
		False -> \_ -> False
		True -> \y -> case y of
			False -> False
			True -> True
	or = \x -> case x of
		False -> \y -> case y of
			False -> False
			True -> True
		True -> \_ -> True

instance Equality b a => Equality b (List a) where
	equ x y = case x of
		Cons x1 xs -> case y of
			Cons y1 ys -> and (equ x1 y1) (equ xs ys)
			Nill -> false
		Nill -> case y of
			Cons _ _ -> false
			Nill -> true

instance Functor List where
	fmap f x = case x of
		Cons x1 xs -> Cons (f x1) (fmap f xs)
		Nill -> Nill
