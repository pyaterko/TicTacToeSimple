package com.owl_laugh_at_wasted_time.tictactoesimple.logic

import com.owl_laugh_at_wasted_time.tictactoesimple.entity.Field

interface Game {
    val winner:Boolean?
    val field: Field
    fun move(position:Int):Int

}

