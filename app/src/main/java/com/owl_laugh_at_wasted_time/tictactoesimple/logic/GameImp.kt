package com.owl_laugh_at_wasted_time.tictactoesimple.logic

import com.owl_laugh_at_wasted_time.tictactoesimple.entity.ArrayField
import com.owl_laugh_at_wasted_time.tictactoesimple.entity.MutableField
import java.util.*

class GameImp(
    size: Int,
    distance: Int,
    depth: Int
) : Game {
    override val winner: Boolean? = null
    override val field: MutableField = ArrayField(size)
    private val result = Result(field.size, field.size, distance)
    private val array = field.get()
    private val counter = Counter(size, distance, depth)
    private val firstMove = Random().nextBoolean()
    private var counterMove = 0

    init {
        if (firstMove) {
            moveAi()
        }
    }

    override fun move(position: Int): Int {
        if (field.getCell(position) != null) {
            return 10
        }
        counterMove++
        field.setCell(position, !firstMove)
        if (result.processEnd(array)) {
            return 1
        }
        moveAi()
        if (result.processEnd(array)) {
            return 2
        }
        if (counterMove >= field.size * field.size) {
            return 0
        }
        return 3
    }

    private fun moveAi() {
        counterMove++
        val position = counter.process(array, false)
        if (array[position] == null) {
            field.setCell(position, firstMove)
        }
    }
}