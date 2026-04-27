package com.example.ex10

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etValue = findViewById<EditText>(R.id.etValue)
        val spConversion = findViewById<Spinner>(R.id.spConversion)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnConvert = findViewById<Button>(R.id.btnConvert)

        btnConvert.setOnClickListener {
            val input = etValue.text.toString().trim().toDoubleOrNull()
            if (input == null) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val type = spConversion.selectedItem.toString()

            val result = when (type) {
                "Inches to Centimeters" -> input * 2.54
                "Feet to Meters" -> input * 0.3048
                "Miles to Kilometers" -> input * 1.60934
                "Pounds to Kilograms" -> input * 0.453592
                else -> input
            }

            val unit = when (type) {
                "Inches to Centimeters" -> "cm"
                "Feet to Meters" -> "m"
                "Miles to Kilometers" -> "km"
                "Pounds to Kilograms" -> "kg"
                else -> ""
            }

            tvResult.text = "Result: " + "%.4f".format(result) + " " + unit
        }
    }
}
