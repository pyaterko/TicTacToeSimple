package com.owl_laugh_at_wasted_time.tictactoesimple.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.owl_laugh_at_wasted_time.tictactoesimple.R
import com.owl_laugh_at_wasted_time.tictactoesimple.databinding.ActivityMainBinding
import com.owl_laugh_at_wasted_time.tictactoesimple.logic.Game
import com.owl_laugh_at_wasted_time.tictactoesimple.logic.GameImp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
    }

    private fun start() {
        game = GameImp(6, 4, 3)
        binding.recyclerViewField.apply {
            layoutManager = GridLayoutManager(this@MainActivity, game.field.size)

            adapter = FieldRVAdapter(game.field) { position ->
                when (game.move(position)) {
                    0 -> {
                        displayAConfirmationDialog(
                            this@MainActivity,
                            "ничья, повторить?",
                            actionPB1 = {
                                start()
                            },
                            actionNB1 = {
                                finish()
                            }
                        )
                    }
                    1 -> {
                        displayAConfirmationDialog(
                            this@MainActivity,
                            "победа, повторить?",
                            actionPB1 = {
                                start()
                            },
                            actionNB1 = {
                                finish()
                            }
                        )
                    }
                    2 -> {
                        displayAConfirmationDialog(
                            this@MainActivity,
                            "проиграл, повторить?",
                            actionPB1 = {
                                start()
                            },
                            actionNB1 = {
                                finish()
                            }
                        )
                    }
                    3 -> {
                    }
                }
                adapter?.notifyDataSetChanged()
            }


        }
    }

    fun displayAConfirmationDialog(
        context: Context,
        title: String,
        actionPB1: (() -> Unit)? = null,
        actionNB1: (() -> Unit)? = null,
    ) {
        val listener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    actionPB1?.invoke()
                }
                DialogInterface.BUTTON_NEGATIVE -> {}
                DialogInterface.BUTTON_NEUTRAL -> {
                    actionNB1?.invoke()
                }
            }
        }
        val dialog = android.app.AlertDialog.Builder(context)
            .setCancelable(true)
            .setMessage(title)
            .setPositiveButton("Да", listener)
            .setNeutralButton("Нет", listener)
            .create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
        dialog?.window?.let {
            val lp = it.attributes
            it.setGravity(Gravity.BOTTOM)
            lp.y = 200
            it.setBackgroundDrawableResource(R.drawable.btn_bg)
        }
    }
}