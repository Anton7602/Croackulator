package com.example.croackulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import mu.KotlinLogging

val logger = KotlinLogging.logger{}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtExpression = findViewById<TextView>(R.id.main_txt_equation)
        val mainSolver = ExpressionSolver()

        fun updateOnScreenText(newText: String) {
            txtExpression.textSize= if (newText.length<12)
                48f
            else if (newText.length<18)
                36f
            else if (newText.length<28)
                24f
            else
                18f
            txtExpression.text = newText
        }

        val smallSplashPlus = findViewById<ImageView>(R.id.main_vect_ssplash_plus)
        val bigSplashPlus = findViewById<ImageView>(R.id.main_vect_bsplash_plus)
        val btnPlus = findViewById<ImageButton>(R.id.main_btn_plus)
        btnPlus.setOnClickListener{
            logger.info { "Pressed + while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashPlus, bigSplashPlus)
            updateOnScreenText(mainSolver.appendExpression('+'))
        }

        val smallSplashMinus = findViewById<ImageView>(R.id.main_vect_ssplash_minus)
        val bigSplashMinus = findViewById<ImageView>(R.id.main_vect_bsplash_minus)
        val btnMinus = findViewById<ImageButton>(R.id.main_btn_minus)
        btnMinus.setOnClickListener{
            logger.info { "Pressed - while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashMinus, bigSplashMinus)
            updateOnScreenText(mainSolver.appendExpression('-'))
        }

        val smallSplashMultiply = findViewById<ImageView>(R.id.main_vect_ssplash_multiply)
        val bigSplashMultiply = findViewById<ImageView>(R.id.main_vect_bsplash_multiply)
        val btnMultiply = findViewById<ImageButton>(R.id.main_btn_multiply)
        btnMultiply.setOnClickListener{
            logger.info { "Pressed - while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashMultiply, bigSplashMultiply)
            updateOnScreenText(mainSolver.appendExpression('*'))
        }

        val smallSplashDivide = findViewById<ImageView>(R.id.main_vect_ssplash_divide)
        val bigSplashDivide = findViewById<ImageView>(R.id.main_vect_bsplash_divide)
        val btnDivide = findViewById<ImageButton>(R.id.main_btn_divide)
        btnDivide.setOnClickListener{
            logger.info { "Pressed / while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashDivide, bigSplashDivide)
            updateOnScreenText(mainSolver.appendExpression('/'))
        }

        val smallSplashSeven = findViewById<ImageView>(R.id.main_vect_ssplash_seven)
        val bigSplashSeven = findViewById<ImageView>(R.id.main_vect_bsplash_seven)
        val btnSeven = findViewById<ImageButton>(R.id.main_btn_seven)
        btnSeven.setOnClickListener{
            logger.info { "Pressed 7 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashSeven, bigSplashSeven)
            updateOnScreenText(mainSolver.appendExpression('7'))
        }

        val smallSplashEight = findViewById<ImageView>(R.id.main_vect_ssplash_eight)
        val bigSplashEight = findViewById<ImageView>(R.id.main_vect_bsplash_eight)
        val btnEight = findViewById<ImageButton>(R.id.main_btn_eight)
        btnEight.setOnClickListener{
            logger.info { "Pressed 8 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashEight, bigSplashEight)
            updateOnScreenText(mainSolver.appendExpression('8'))
        }

        val smallSplashNine = findViewById<ImageView>(R.id.main_vect_ssplash_nine)
        val bigSplashNine = findViewById<ImageView>(R.id.main_vect_bsplash_nine)
        val btnNine = findViewById<ImageButton>(R.id.main_btn_nine)
        btnNine.setOnClickListener{
            logger.info { "Pressed 9 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashNine, bigSplashNine)
            updateOnScreenText(mainSolver.appendExpression('9'))
        }

        val smallSplashRemove = findViewById<ImageView>(R.id.main_vect_ssplash_remove)
        val bigSplashRemove = findViewById<ImageView>(R.id.main_vect_bsplash_remove)
        val btnRemove = findViewById<ImageButton>(R.id.main_btn_remove)
        btnRemove.setOnClickListener{
            logger.info { "Pressed remove while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashRemove, bigSplashRemove)
            updateOnScreenText(mainSolver.removeLast())
        }

        val smallSplashFour = findViewById<ImageView>(R.id.main_vect_ssplash_four)
        val bigSplashFour = findViewById<ImageView>(R.id.main_vect_bsplash_four)
        val btnFour = findViewById<ImageButton>(R.id.main_btn_four)
        btnFour.setOnClickListener{
            logger.info { "Pressed 4 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashFour, bigSplashFour)
            updateOnScreenText(mainSolver.appendExpression('4'))
        }

        val smallSplashFive = findViewById<ImageView>(R.id.main_vect_ssplash_five)
        val bigSplashFive = findViewById<ImageView>(R.id.main_vect_bsplash_five)
        val btnFive = findViewById<ImageButton>(R.id.main_btn_five)
        btnFive.setOnClickListener{
            logger.info { "Pressed 5 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashFive, bigSplashFive)
            updateOnScreenText(mainSolver.appendExpression('5'))
        }

        val smallSplashSix = findViewById<ImageView>(R.id.main_vect_ssplash_six)
        val bigSplashSix = findViewById<ImageView>(R.id.main_vect_bsplash_six)
        val btnSix = findViewById<ImageButton>(R.id.main_btn_six)
        btnSix.setOnClickListener{
            logger.info { "Pressed 6 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashSix, bigSplashSix)
            updateOnScreenText(mainSolver.appendExpression('6'))
        }

        val smallSplashClear = findViewById<ImageView>(R.id.main_vect_ssplash_clear)
        val bigSplashClear = findViewById<ImageView>(R.id.main_vect_bsplash_clear)
        val btnClear = findViewById<ImageButton>(R.id.main_btn_clear)
        btnClear.setOnClickListener{
            logger.info { "Pressed clear while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashClear, bigSplashClear)
            updateOnScreenText(mainSolver.clearExpression())
        }

        val smallSplashOne = findViewById<ImageView>(R.id.main_vect_ssplash_one)
        val bigSplashOne = findViewById<ImageView>(R.id.main_vect_bsplash_one)
        val btnOne = findViewById<ImageButton>(R.id.main_btn_one)
        btnOne.setOnClickListener{
            logger.info { "Pressed 1 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashOne, bigSplashOne)
            updateOnScreenText(mainSolver.appendExpression('1'))
        }

        val smallSplashTwo = findViewById<ImageView>(R.id.main_vect_ssplash_two)
        val bigSplashTwo = findViewById<ImageView>(R.id.main_vect_bsplash_two)
        val btnTwo = findViewById<ImageButton>(R.id.main_btn_two)
        btnTwo.setOnClickListener{
            logger.info { "Pressed 2 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashTwo, bigSplashTwo)
            updateOnScreenText(mainSolver.appendExpression('2'))
        }

        val smallSplashThree = findViewById<ImageView>(R.id.main_vect_ssplash_three)
        val bigSplashThree = findViewById<ImageView>(R.id.main_vect_bsplash_three)
        val btnThree = findViewById<ImageButton>(R.id.main_btn_three)
        btnThree.setOnClickListener{
            logger.info { "Pressed 3 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashThree, bigSplashThree)
            updateOnScreenText(mainSolver.appendExpression('3'))
        }

        val smallSplashComma = findViewById<ImageView>(R.id.main_vect_ssplash_comma)
        val bigSplashComma = findViewById<ImageView>(R.id.main_vect_bsplash_comma)
        val btnComma = findViewById<ImageButton>(R.id.main_btn_comma)
        btnComma.setOnClickListener{
            logger.info { "Pressed . while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashComma, bigSplashComma)
            updateOnScreenText(mainSolver.appendExpression('.'))
        }

        val smallSplashZero = findViewById<ImageView>(R.id.main_vect_ssplash_zero)
        val bigSplashZero = findViewById<ImageView>(R.id.main_vect_bsplash_zero)
        val btnZero = findViewById<ImageButton>(R.id.main_btn_zero)
        btnZero.setOnClickListener{
            logger.info { "Pressed 0 while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashZero, bigSplashZero)
            updateOnScreenText(mainSolver.appendExpression('0'))
        }

        val smallSplashBracket = findViewById<ImageView>(R.id.main_vect_ssplash_bracket)
        val bigSplashBracket = findViewById<ImageView>(R.id.main_vect_bsplash_bracket)
        val btnBracket = findViewById<ImageButton>(R.id.main_btn_bracket)
        btnBracket.setOnClickListener{
            logger.info { "Pressed ( while expression ${mainSolver.expression}" }
            playSplashAnimation(smallSplashBracket, bigSplashBracket)
            updateOnScreenText(mainSolver.appendExpression('('))
        }

        val smallSplashEquals = findViewById<ImageView>(R.id.main_vect_ssplash_equals)
        val bigSplashEquals = findViewById<ImageView>(R.id.main_vect_bsplash_equals)
        val btnEquals = findViewById<ImageButton>(R.id.main_btn_equals)
        btnEquals.setOnClickListener{
            logger.info { "Pressed = while expression ${mainSolver.expression}. Solving" }
            playSplashAnimation(smallSplashEquals, bigSplashEquals)
            try {
                updateOnScreenText(mainSolver.solveExpression())
                logger.info { "Expression solved. Answer - ${mainSolver.expression}" }
            }
            catch (e: Exception) {
                logger.info { "Exception encountered. Solution aborted" }
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playSplashAnimation(smallCircle: ImageView, bigCircle: ImageView)  {
        smallCircle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fadein))
        bigCircle.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_fadein))
    }
}