package com.test.endroits.infrastructure

sealed class Either<out E, out V>{
    data class Left<out E>(val error: E): Either<E, Nothing>()
    data class Right<out V>(val value: V): Either<Nothing, V>()
}

inline fun <E, V, A> Either<E, V>
        .either(e: (E) -> A, v: (V) -> A): A = when(this) {
    is Either.Left -> e(this.error)
    is Either.Right -> v(this.value)
}