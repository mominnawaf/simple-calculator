package com.example.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun digitClick(view: View){
        tvInput.append((view as Button).text)
        lastNumeric= true

    }
    fun clear(view: View){
        tvInput.text=null
        lastNumeric=false
        lastDot=false
    }
    fun onDecimal(view: View){
        if(lastNumeric && !lastDot){
            tvInput.append(".")
            lastNumeric=false
            lastDot= true
        }
    }
    fun onOperator(view :View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString()) ){
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot= false
        }
    }
    fun  onEqual(view :View){
        if (lastNumeric){
            var value = tvInput.text.toString()
            var prefix= false
            try {
                if (value.startsWith('-')){
                    prefix = true
                    value = value.substring(1)
                }
                if (value.contains('-')){
                   var newvar= value.split('-')
                    var first = newvar[0]
                    var second = newvar[1]
                    if(prefix){
                        first = "-$first"
                    }
                    tvInput.text= (first.toDouble() - second.toDouble()).toString()
                }
                if(value.contains('+')){
                    var newvar= value.split('+')
                    var first = newvar[0]
                    var second = newvar[1]
                    if(prefix){
                        first = "-$first"
                    }
                    tvInput.text= (first.toDouble() + second.toDouble()).toString()

                }
                if(value.contains('/')){
                    var newvar= value.split('/')
                    var first = newvar[0]
                    var second = newvar[1]
                    if(prefix){
                        first = "-$first"
                    }
                    tvInput.text= (first.toDouble() / second.toDouble()).toString()

                }
                if(value.contains('X')){
                    var newvar= value.split('X')
                    var first = newvar[0]
                    var second = newvar[1]
                    if(prefix){
                        first = "-$first"
                    }
                    tvInput.text= (first.toDouble() * second.toDouble()).toString()

                }

            }
            catch (e: ArithmeticException){
                Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun isOperatorAdded(value: String):Boolean{
        return if (value.startsWith('-')){
            false
        } else{
            value.contains('+') || value.contains('*') || value.contains('/') || value.contains('-')
        }
    }
}