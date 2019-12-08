package com.example.android.dicegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var player1Dice1: ImageView
    lateinit var player1Dice2: ImageView
    lateinit var player2Dice1: ImageView
    lateinit var player2Dice2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player1Dice1 = findViewById(R.id.player1Dice1)
        player1Dice2 = findViewById(R.id.player1Dice2)
        player2Dice1 = findViewById(R.id.player2Dice1)
        player2Dice2 = findViewById(R.id.player2Dice2)

        val player1Score: TextView = findViewById(R.id.player1_score_id)
        val player2Score: TextView = findViewById(R.id.player2_score_id)

        var player1ScoreValue = 0
        var player2ScoreValue = 0

        val instructions: Button = findViewById(R.id.instructions_id)

        instructions.setOnClickListener { Toast.makeText(this, R.string.instruction, Toast.LENGTH_LONG).show() }

        var isPLayer1 = true


        val playGame: Button = findViewById(R.id.play_id)


        playGame.setOnClickListener {

            val dice1 = Random.nextInt(6) + 1
            val dice2 = Random.nextInt(6) + 1

            if(isPLayer1) {
                player1ScoreValue += rollDices(dice1, dice2, isPLayer1)
                player1Score.setText(player1ScoreValue.toString())
                playGame.setText(R.string.player2)
                isPLayer1 = false
            } else {
                player2ScoreValue += rollDices(dice1, dice2, isPLayer1)
                player2Score.setText(player2ScoreValue.toString())
                playGame.setText(R.string.player1)
                isPLayer1 = true
            }

            if(player1ScoreValue >= 30) {
                playGame.isEnabled = false
                Toast.makeText(this, R.string.player1_wins, Toast.LENGTH_LONG).show()
            } else if(player2ScoreValue >= 30) {
                playGame.isEnabled = false
                Toast.makeText(this, R.string.player2_wins, Toast.LENGTH_LONG).show()
            }


        }


        val replay: Button = findViewById(R.id.replay_id)

        replay.setOnClickListener {
            player1ScoreValue = 0
            player2ScoreValue = 0
            player1Score.setText(player1ScoreValue.toString())
            player2Score.setText(player2ScoreValue.toString())
            playGame.setText(R.string.player1)
            playGame.isEnabled = true
        }



    }




    private fun rollDices(dice1: Int, dice2: Int, isPlayer1: Boolean): Int {

        val drawableSource1 = getDrawableSource(dice1)
        val drawableSource2 = getDrawableSource(dice2)

        if(isPlayer1) {
            player1Dice1.setImageResource(drawableSource1)
            player1Dice2.setImageResource(drawableSource2)
            } else {
            player2Dice1.setImageResource(drawableSource1)
            player2Dice2.setImageResource(drawableSource2)
        }

        return dice1 + dice2
    }

    private fun getDrawableSource(choice: Int): Int = when(choice) {

       1 -> R.drawable.dice_1
       2 -> R.drawable.dice_2
       3 -> R.drawable.dice_3
       4 -> R.drawable.dice_4
       5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }


}
