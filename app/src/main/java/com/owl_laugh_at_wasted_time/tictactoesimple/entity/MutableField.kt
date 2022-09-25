package com.owl_laugh_at_wasted_time.tictactoesimple.entity

interface MutableField : Field {
    fun setCell(position: Int, value: Boolean)
}