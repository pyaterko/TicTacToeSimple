package com.owl_laugh_at_wasted_time.tictactoesimple.logic

class Counter(
    size: Int,
    distance: Int,
    private val depth: Int
) {

    private val result = Result(size, size, distance)
    private val estimation = Estimation(size, size, distance)
    private var index = 0


    fun process(array: Array<Boolean?>, symbol: Boolean): Int {
        recursion(symbol, array, -1, depth)
        return index
    }

    private fun recursion(
        symbol: Boolean,
        arrayOut: Array<Boolean?>,
        position: Int,
        depth: Int
    ): Int {
        val array = arrayOut.clone()
        if (position >= 0) {
            array[position] = symbol
        }
        if (result.process(array) == symbol) {
            return 2
        }
        val counterHelper = CounterHelper(estimation)
        counterHelper.process(array, symbol)

        var score = 0
        var maxScore = -1
        var index = 0
        var maxEst = 2000000000
        for (step in 0..1) {
            if (step == 0 || step > 0 && maxScore == 0) {
                maxEst = counterHelper.maxScore(maxEst)
                for (i in array.indices) {
                    if (array[i] == null && depth > 0 && counterHelper.okay(i)) {
                        if (!symbol) {
                            score = recursion(true, array, i, depth - 1)
                        }
                        if (symbol) {
                            score = recursion(false, array, i, depth - 1)
                        }
                        if (score > maxScore) {
                            maxScore = score
                            index = i
                        }
                    }
                }
            }
        }
        this.index = index
        return if (maxScore == -1) {
            1
        } else {
            2 - maxScore
        }
    }
}
