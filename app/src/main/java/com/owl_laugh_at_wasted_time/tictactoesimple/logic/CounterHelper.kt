package com.owl_laugh_at_wasted_time.tictactoesimple.logic

class CounterHelper(
    private val estimation: Estimation
) {

    private var maxScore = 0
    private var arrayValue: IntArray? = null
    var value = 0

    fun process(arrayOut: Array<Boolean?>, symbol: Boolean) {
        val array = arrayOut.clone()
        arrayValue = arrayValueInit(array.size)
        for (i in array.indices) {
            if (array[i] == null) {
                array[i] = symbol
                arrayValue!![i] = estimation.process(array, symbol, !symbol)
                array[i] = null
            }
        }
    }

    fun maxScore(border: Int): Int {
        maxScore = -2000000000
        for (i in arrayValue!!.indices) {
            if ((arrayValue!![i]) in ((maxScore + 1) until border)) {
                maxScore = arrayValue!![i]
            }
        }
        return maxScore
    }

    fun okay(index: Int): Boolean {
        return arrayValue!![index] == maxScore
    }

    private fun arrayValueInit(length: Int): IntArray {
        val array = IntArray(length)
        for (i in 0 until length) {
            array[i] = -2000000000
        }
        return array
    }
}
