package com.rmuhamed.sample.poketest.util

fun inBetween(min: Int, max: Int): Int = if (min < max) {
    min.rangeTo(max).random()
} else {
    -1
}
