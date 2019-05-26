package com.rmuhamed.sample.poketest.util

fun randomizeGet(min: Int, max: Int): Int = if (min < max) {
    min.rangeTo(max).shuffled().first()
} else {
    -1
}
