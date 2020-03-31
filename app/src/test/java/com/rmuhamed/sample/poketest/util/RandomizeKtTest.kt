package com.rmuhamed.sample.poketest.util

import org.junit.Assert
import org.junit.Test

class RandomizeKtTest {
    @Test
    fun test_RandomizeGet_InvalidRange() {
        Assert.assertEquals(-1, inBetween(10, 1))
    }

    @Test
    fun test_RandomizeGet_ValeRange() {
        val value = inBetween(1, 10)
        Assert.assertTrue((value >= 1) && (value <= 10))
    }
}