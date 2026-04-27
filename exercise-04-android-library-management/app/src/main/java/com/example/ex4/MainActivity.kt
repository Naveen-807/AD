package com.example.ex4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    var issued: Boolean = false
)

class MainActivity : AppCompatActivity() {

    private val books = mutableMapOf<Int, Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etId = findViewById<EditText>(R.id.etId)
        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etAuthor = findViewById<EditText>(R.id.etAuthor)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)

        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val id = etId.text.toString().toIntOrNull()
            val title = etTitle.text.toString().trim()
            val author = etAuthor.text.toString().trim()

            if (id == null || title.isEmpty() || author.isEmpty()) {
                toast("Enter valid ID, title, and author")
                return@setOnClickListener
            }
            if (books.containsKey(id)) {
                toast("Book ID already exists")
                return@setOnClickListener
            }

            books[id] = Book(id, title, author)
            toast("Book added")
            showAll(tvOutput)
        }

        findViewById<Button>(R.id.btnIssue).setOnClickListener {
            val id = etId.text.toString().toIntOrNull() ?: run { toast("Enter valid ID"); return@setOnClickListener }
            val book = books[id] ?: run { toast("Book not found"); return@setOnClickListener }
            if (book.issued) {
                toast("Book already issued")
            } else {
                book.issued = true
                toast("Book issued")
                showAll(tvOutput)
            }
        }

        findViewById<Button>(R.id.btnReturn).setOnClickListener {
            val id = etId.text.toString().toIntOrNull() ?: run { toast("Enter valid ID"); return@setOnClickListener }
            val book = books[id] ?: run { toast("Book not found"); return@setOnClickListener }
            if (!book.issued) {
                toast("Book is already available")
            } else {
                book.issued = false
                toast("Book returned")
                showAll(tvOutput)
            }
        }

        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            val id = etId.text.toString().toIntOrNull() ?: run { toast("Enter valid ID"); return@setOnClickListener }
            val book = books[id]
            tvOutput.text = if (book == null) {
                "Book not found"
            } else {
                "ID: " + book.id + "\nTitle: " + book.title + "\nAuthor: " + book.author + "\nStatus: " + (if (book.issued) "Issued" else "Available")
            }
        }

        findViewById<Button>(R.id.btnShowAll).setOnClickListener {
            showAll(tvOutput)
        }
    }

    private fun showAll(tvOutput: TextView) {
        if (books.isEmpty()) {
            tvOutput.text = "No books added"
            return
        }

        val parts = books.values.map {
            "ID: " + it.id + "\nTitle: " + it.title + "\nAuthor: " + it.author + "\nStatus: " + (if (it.issued) "Issued" else "Available")
        }
        tvOutput.text = parts.joinToString("\n\n")
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
