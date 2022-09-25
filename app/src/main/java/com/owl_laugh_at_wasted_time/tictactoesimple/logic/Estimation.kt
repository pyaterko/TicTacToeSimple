package com.owl_laugh_at_wasted_time.tictactoesimple.logic

class Estimation(
    private val width: Int,
    private val height: Int,
    private val distance: Int
) {

    private var array: Array<Boolean?>? = null
    private val plus = intArrayOf(0, 1, 9, 81, 729, 59049)
    private val minus = intArrayOf(0, 3, 27, 243, 2187, 19683)

    fun process(array: Array<Boolean?>, symbol1: Boolean, symbol2: Boolean): Int {
        this.array = array
        var score = 0
        for (x in 0 until width) {
            for (y in 0 until height) {

                for (a in -1..1) {
                    for (b in -1..1) {
                        if (!(a == 0 && b == 0)) {

                            score += plus[score(x, y, a, b, symbol1)]
                            score -= minus[score(x, y, a, b, symbol2)]
                        }
                    }
                }

            }
        }
        return score
    }

    private fun score(x: Int, y: Int, a: Int, b: Int, symbol: Boolean): Int {
        var score = 0
        for (i in 0 until distance) {
            val index = border(x + a * i, y + b * i)
            if (index == -1 || (array!![index] != symbol && array!![index] != null)) {
                return 0
            } else {
                if (array!![index] == symbol) {
                    score++
                }
            }
        }
        return score
    }

    private fun border(x: Int, y: Int): Int {
        return if (x in 0 until width && y >= 0 && y < height) {
            y * width + x
        } else -1
    }
}
