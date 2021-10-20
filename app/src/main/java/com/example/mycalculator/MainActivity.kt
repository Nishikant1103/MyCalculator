package com.example.mycalculator

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var lastDigit: Boolean = false
    var lastDecimal: Boolean = false

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun onDigit(view: View) {
        text_display.append((view as Button).text)
        lastDigit = true
        //Toast.makeText(this, "Button $inputText was clicked", Toast.LENGTH_SHORT).show()
    }

    fun onClear(view: View) {
        text_display.text = ""
        lastDigit = false
        lastDecimal = false
    }

    fun onDecimal(view: View) {
        if (lastDigit && !lastDecimal) {
            text_display.append((view as Button).text)
            lastDecimal = true
            lastDigit = false
        }
    }

    fun onOperator(view: View) {
        if (lastDigit && !isOperatorAdded(text_display.text.toString())) {
            text_display.append((view as Button).text)
            lastDigit = false
            lastDecimal = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")
        }
    }

    fun onEqual(view: View) {
        if (lastDigit) {
            var prefix = ""
            var tvValue = text_display.text.toString()
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    var splittedText = tvValue.split("-")
                    var firstNum = splittedText[0]
                    var secondNum = splittedText[1]

                    if (!prefix.isEmpty()) {
                        firstNum = prefix + firstNum
                    }
                    text_display.text = (firstNum.toDouble() - secondNum.toDouble()).toString()
                } else if (tvValue.contains("*")) {
                    val splittedText = tvValue.split("*")
                    var firstNum = splittedText[0]
                    val secondNum = splittedText[1]

                    if (!prefix.isEmpty()) {
                        firstNum = prefix + firstNum
                    }
                    text_display.text = (firstNum.toDouble() * secondNum.toDouble()).toString()
                } else if (tvValue.contains("/")) {
                    val splittedText = tvValue.split("/")
                    var firstNum = splittedText[0]
                    val secondNum = splittedText[1]

                    if (!prefix.isEmpty()) {
                        firstNum = prefix + firstNum
                    }
                    text_display.text = (firstNum.toDouble() / secondNum.toDouble()).toString()
                } else if (tvValue.contains("+")) {
                    val splittedText = tvValue.split("+")
                    var firstNum = splittedText[0]
                    val secondNum = splittedText[1]

                    if (!prefix.isEmpty()) {
                        firstNum = prefix + firstNum
                    }
                    text_display.text = (firstNum.toDouble() + secondNum.toDouble()).toString()
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }


    }
}