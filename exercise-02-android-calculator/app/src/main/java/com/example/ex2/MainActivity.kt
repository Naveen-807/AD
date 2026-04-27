package com.example.ex2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        tvResult = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btnAdd).setOnClickListener { calculate('+') }
        findViewById<Button>(R.id.btnSub).setOnClickListener { calculate('-') }
        findViewById<Button>(R.id.btnMul).setOnClickListener { calculate('*') }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { calculate('/') }
    }

    private fun readNumber(editText: EditText): Double? {
        return editText.text.toString().trim().toDoubleOrNull()
    }

    private fun calculate(op: Char) {
        val a = readNumber(etNum1)
        val b = readNumber(etNum2)

        if (a == null || b == null) {
            Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_SHORT).show()
            return
        }

        if (op == '/' && b == 0.0) {
            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            else -> 0.0
        }

        val clean = if (result % 1.0 == 0.0) result.roundToInt().toString() else "%.4f".format(result)
        tvResult.text = "Result: " + clean
    }
}
