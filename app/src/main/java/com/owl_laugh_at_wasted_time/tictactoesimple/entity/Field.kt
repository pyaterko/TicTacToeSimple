package com.owl_laugh_at_wasted_time.tictactoesimple.entity

interface Field {
    val size:Int
    fun getCell(position:Int):Boolean?
    fun get():Array<Boolean?>
}