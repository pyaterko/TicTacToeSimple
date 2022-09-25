package com.owl_laugh_at_wasted_time.tictactoesimple.logic

class Result(
    val width: Int,
    val height: Int,
    val distance: Int,
) {

    private var array: Array<Boolean?>?=null

    fun process(array: Array<Boolean?>): Boolean? {
        this.array = array
        var i = 0
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (array[border(x, y)] != null) {
                    i++
                }
                for (a in -1..1) {
                    for (b in -1..1) {
                        if (!(a == 0 && b == 0)) {
                            if (check(array, x, y, a, b, true)) {
                                return true
                            }
                            if (check(array, x, y, a, b, false)) {
                                return false
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    fun processEnd(array: Array<Boolean?>): Boolean {
        this.array = array
        for (x in 0 until width) {
            for (y in 0 until height) {
                if (array[border(x, y)] != null) {

                }
                for (a in -1..1) {
                    for (b in -1..1) {
                        if (!(a == 0 && b == 0)) {
                            if (check(array, x, y, a, b, true)) {
                                return true
                            }
                            if (check(array, x, y, a, b, false)) {
                                return true
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    private fun border(x: Int, y: Int): Int {
        return if (x >= 0 && x < width && y >= 0 && y < height) {
            y * width + x
        } else -1
    }

    private fun check(
        array: Array<Boolean?>,
        x: Int,
        y: Int,
        a: Int,
        b: Int,
        symbol: Boolean
    ): Boolean {
        for (i in 0 until distance) {
            val index = border(x + a * i, y + b * i)
            if (index == -1) {
                return false
            } else {
                if (array[index] != symbol) {
                    return false
                }
            }
        }
        return true
    }
}

