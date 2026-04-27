package com.example.ex1

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var changed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStyle = findViewById<Button>(R.id.btnStyle)

        btnStyle.setOnClickListener {
            changed = !changed

            if (changed) {
                btnStyle.setBackgroundColor(Color.parseColor("#0F766E"))
                btnStyle.setTextColor(Color.WHITE)
                btnStyle.textSize = 24f
                btnStyle.setTypeface(null, Typeface.BOLD_ITALIC)
                btnStyle.text = "Styled"
            } else {
                btnStyle.setBackgroundColor(Color.parseColor("#D1D5DB"))
                btnStyle.setTextColor(Color.BLACK)
                btnStyle.textSize = 18f
                btnStyle.setTypeface(null, Typeface.NORMAL)
                btnStyle.text = "Click Me"
            }
        }
    }
}
