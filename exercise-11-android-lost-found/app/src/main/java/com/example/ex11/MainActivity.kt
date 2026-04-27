package com.example.ex11

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Report(
    val title: String,
    val description: String,
    val type: String
)

class MainActivity : AppCompatActivity() {

    private val reports = mutableListOf<Report>()
    private lateinit var adapter: ArrayAdapter<String>
    private val displayList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val rbLost = findViewById<RadioButton>(R.id.rbLost)
        val listView = findViewById<ListView>(R.id.listView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        listView.adapter = adapter

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val type = if (rbLost.isChecked) "Lost" else "Found"

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Enter title and description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            reports.add(0, Report(title, description, type))
            etTitle.text.clear()
            etDescription.text.clear()
            refresh("All")
        }

        findViewById<Button>(R.id.btnAll).setOnClickListener { refresh("All") }
        findViewById<Button>(R.id.btnLost).setOnClickListener { refresh("Lost") }
        findViewById<Button>(R.id.btnFound).setOnClickListener { refresh("Found") }

        refresh("All")
    }

    private fun refresh(filter: String) {
        val filtered = when (filter) {
            "Lost" -> reports.filter { it.type == "Lost" }
            "Found" -> reports.filter { it.type == "Found" }
            else -> reports
        }

        displayList.clear()
        displayList.addAll(
            filtered.map {
                "[" + it.type + "] " + it.title + "\n" + it.description
            }
        )

        if (displayList.isEmpty()) {
            displayList.add("No posts yet")
        }

        adapter.notifyDataSetChanged()
    }
}
