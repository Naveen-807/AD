# Mobile Lab Exam Pack (11 Exercises)

This file gives **easy, copy-paste, working code** for each exercise in your index.

## Quick Index (Aligned for GitHub)

| Ex.No | Exercise | Stack |
|:-----:|---|---|
| 1 | Change background color, font style, and font size on button click | Android (Kotlin) |
| 2 | Calculator application | Android (Kotlin) |
| 3 | Generate notifications on messages | Android (Kotlin) |
| 4 | Library management system | Android (Kotlin) |
| 5 | User login screen | Cordova (HTML/CSS/JS) |
| 6 | Display current location | Cordova + Geolocation |
| 7 | Mobile app using Ionic | Ionic (Angular) |
| 8 | BMI calculator | React Native (Expo) |
| 9 | Expense manager app | Flutter (Dart) |
| 10 | Imperial to metric converter | Android (Kotlin) |
| 11 | Lost and found community app | Android (Kotlin) |

## Copy-Paste Workflow (Exam Friendly)

1. Create the project with the command shown in each exercise.
2. Replace only the files listed in that exercise.
3. Keep the package name as your project package (or change it consistently).
4. For Android exercises: **Sync Gradle** and then run.
5. For Cordova/Ionic/Expo/Flutter exercises: run the exact command shown in that section.

---

## Exercise 1: Change Background Color, Font Style, and Font Size on Button Click (Android)

### Files to Replace

1. `app/src/main/res/layout/activity_main.xml`
2. `app/src/main/java/com/example/ex1/MainActivity.kt`

### `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <Button
        android:id="@+id/btnStyle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Click Me"
        android:textAllCaps="false"
        android:textSize="18sp" />

</LinearLayout>
```

### `MainActivity.kt`

```kotlin
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
```

---

## Exercise 2: Calculator Application in Android Studio

### Files to Replace

1. `app/src/main/res/layout/activity_main.xml`
2. `app/src/main/java/com/example/ex2/MainActivity.kt`

### `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etNum1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter first number"
        android:inputType="numberDecimal|numberSigned" />

    <EditText
        android:id="@+id/etNum2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:hint="Enter second number"
        android:inputType="numberDecimal|numberSigned" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:orientation="horizontal"
        android:weightSum="4">

        <Button
            android:id="@+id/btnAdd"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="+" />

        <Button
            android:id="@+id/btnSub"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="-" />

        <Button
            android:id="@+id/btnMul"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="*" />

        <Button
            android:id="@+id/btnDiv"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="/" />
    </LinearLayout>

    <TextView
        android:id="@+id/tvResult"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Result:"
        android:textSize="20sp"
        android:textStyle="bold" />

</LinearLayout>
```

### `MainActivity.kt`

```kotlin
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
        tvResult.text = "Result: $clean"
    }
}
```

---

## Exercise 3: Android Application to Generate Notifications on Messages

### Files to Replace

1. `app/src/main/AndroidManifest.xml`
2. `app/src/main/res/layout/activity_main.xml`
3. `app/src/main/java/com/example/ex3/MainActivity.kt`

### `AndroidManifest.xml` (only add permission line inside `<manifest>`)

```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

### `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etMessage"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter message"
        android:inputType="textMultiLine" />

    <Button
        android:id="@+id/btnNotify"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:text="Send Notification" />

</LinearLayout>
```

### `MainActivity.kt`

```kotlin
package com.example.ex3

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val channelId = "message_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createChannel()
        requestNotificationPermissionIfNeeded()

        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnNotify = findViewById<Button>(R.id.btnNotify)

        btnNotify.setOnClickListener {
            val message = etMessage.text.toString().trim().ifEmpty { "New message received" }
            showNotification(message)
        }
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Message Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
        }
    }

    private fun showNotification(message: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Notification permission not granted", Toast.LENGTH_SHORT).show()
            return
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle("Message Alert")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(this).notify(System.currentTimeMillis().toInt(), notification)
    }
}
```

---

## Exercise 4: Library Management System (Android, Simple and Fast)

### Files to Replace

1. `app/src/main/res/layout/activity_main.xml`
2. `app/src/main/java/com/example/ex4/MainActivity.kt`

### `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etId"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Book ID (number)"
        android:inputType="number" />

    <EditText
        android:id="@+id/etTitle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:hint="Book Title" />

    <EditText
        android:id="@+id/etAuthor"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:hint="Author" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:orientation="horizontal"
        android:weightSum="3">

        <Button
            android:id="@+id/btnAdd"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Add" />

        <Button
            android:id="@+id/btnIssue"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Issue" />

        <Button
            android:id="@+id/btnReturn"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Return" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:orientation="horizontal"
        android:weightSum="2">

        <Button
            android:id="@+id/btnSearch"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Search" />

        <Button
            android:id="@+id/btnShowAll"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Show All" />
    </LinearLayout>

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginTop="12dp"
        android:layout_weight="1">

        <TextView
            android:id="@+id/tvOutput"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Output appears here" />
    </ScrollView>

</LinearLayout>
```

### `MainActivity.kt`

```kotlin
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
            val id = etId.text.toString().toIntOrNull() ?: return@setOnClickListener toast("Enter valid ID")
            val book = books[id] ?: return@setOnClickListener toast("Book not found")
            if (book.issued) {
                toast("Book already issued")
            } else {
                book.issued = true
                toast("Book issued")
                showAll(tvOutput)
            }
        }

        findViewById<Button>(R.id.btnReturn).setOnClickListener {
            val id = etId.text.toString().toIntOrNull() ?: return@setOnClickListener toast("Enter valid ID")
            val book = books[id] ?: return@setOnClickListener toast("Book not found")
            if (!book.issued) {
                toast("Book is already available")
            } else {
                book.issued = false
                toast("Book returned")
                showAll(tvOutput)
            }
        }

        findViewById<Button>(R.id.btnSearch).setOnClickListener {
            val id = etId.text.toString().toIntOrNull() ?: return@setOnClickListener toast("Enter valid ID")
            val book = books[id]
            tvOutput.text = if (book == null) {
                "Book not found"
            } else {
                "ID: ${book.id}\nTitle: ${book.title}\nAuthor: ${book.author}\nStatus: ${if (book.issued) "Issued" else "Available"}"
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

        val text = books.values.joinToString("\n\n") {
            "ID: ${it.id}\nTitle: ${it.title}\nAuthor: ${it.author}\nStatus: ${if (it.issued) "Issued" else "Available"}"
        }
        tvOutput.text = text
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
```

---

## Exercise 5: Android Application Using Cordova for User Login Screen

### Create Project

```bash
cordova create ex5-login com.example.ex5login Ex5Login
cd ex5-login
cordova platform add android
```

### Replace File

1. `www/index.html`

### `www/index.html`

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Login App</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      min-height: 100vh;
      display: grid;
      place-items: center;
      background: linear-gradient(135deg, #dbeafe, #f0fdf4);
    }
    .card {
      width: min(92vw, 360px);
      background: #fff;
      border-radius: 12px;
      padding: 20px;
      box-shadow: 0 10px 25px rgba(0,0,0,0.08);
    }
    h2 { margin: 0 0 16px; }
    input, button {
      width: 100%;
      box-sizing: border-box;
      padding: 10px;
      margin-top: 10px;
      border-radius: 8px;
      border: 1px solid #ccc;
      font-size: 16px;
    }
    button {
      border: none;
      background: #0f766e;
      color: white;
      font-weight: 700;
      cursor: pointer;
    }
    #msg { margin-top: 12px; font-weight: 600; }
  </style>
</head>
<body>
  <div class="card">
    <h2>User Login</h2>
    <input id="username" placeholder="Username" />
    <input id="password" type="password" placeholder="Password" />
    <button id="loginBtn">Login</button>
    <div id="msg"></div>
  </div>

  <script src="cordova.js"></script>
  <script>
    function setup() {
      const username = document.getElementById('username');
      const password = document.getElementById('password');
      const msg = document.getElementById('msg');

      document.getElementById('loginBtn').addEventListener('click', () => {
        const user = username.value.trim();
        const pass = password.value.trim();

        if (!user || !pass) {
          msg.textContent = 'Please enter username and password';
          msg.style.color = '#b91c1c';
          return;
        }

        if (user === 'admin' && pass === '1234') {
          msg.textContent = 'Login successful';
          msg.style.color = '#166534';
        } else {
          msg.textContent = 'Invalid credentials';
          msg.style.color = '#b91c1c';
        }
      });
    }

    document.addEventListener('deviceready', setup, false);
    document.addEventListener('DOMContentLoaded', () => {
      if (!window.cordova) setup();
    });
  </script>
</body>
</html>
```

### Run

```bash
cordova run android
```

---

## Exercise 6: Android Application Using Apache Cordova to Display Current Location

### Create Project and Install Plugin

```bash
cordova create ex6-location com.example.ex6location Ex6Location
cd ex6-location
cordova platform add android
cordova plugin add cordova-plugin-geolocation
```

### Replace File

1. `www/index.html`

### `www/index.html`

```html
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Current Location</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      min-height: 100vh;
      display: grid;
      place-items: center;
      background: #f8fafc;
    }
    .card {
      width: min(92vw, 420px);
      background: white;
      border: 1px solid #e2e8f0;
      border-radius: 12px;
      padding: 20px;
    }
    button {
      width: 100%;
      padding: 10px;
      border: none;
      border-radius: 8px;
      background: #1d4ed8;
      color: white;
      font-size: 16px;
      font-weight: 700;
      cursor: pointer;
    }
    #result { margin-top: 14px; line-height: 1.5; }
  </style>
</head>
<body>
  <div class="card">
    <h2>Get Current Location</h2>
    <button id="btnLocation">Show Location</button>
    <div id="result">Tap the button to fetch GPS location.</div>
  </div>

  <script src="cordova.js"></script>
  <script>
    function setup() {
      const result = document.getElementById('result');

      document.getElementById('btnLocation').addEventListener('click', () => {
        if (!navigator.geolocation) {
          result.textContent = 'Geolocation is not supported on this device.';
          return;
        }

        result.textContent = 'Fetching location...';

        navigator.geolocation.getCurrentPosition(
          (position) => {
            const lat = position.coords.latitude.toFixed(6);
            const lon = position.coords.longitude.toFixed(6);
            result.innerHTML = `Latitude: ${lat}<br>Longitude: ${lon}`;
          },
          (error) => {
            result.textContent = `Error: ${error.message}`;
          },
          {
            enableHighAccuracy: true,
            timeout: 10000,
            maximumAge: 0
          }
        );
      });
    }

    document.addEventListener('deviceready', setup, false);
    document.addEventListener('DOMContentLoaded', () => {
      if (!window.cordova) setup();
    });
  </script>
</body>
</html>
```

### Run

```bash
cordova run android
```

---

## Exercise 7: Mobile Application Using Ionic (Simple Task Manager)

### Create Project

```bash
npm install -g @ionic/cli
ionic start ex7-ionic blank --type=angular --no-interactive
cd ex7-ionic
```

### Files to Replace

1. `src/app/home/home.page.ts`
2. `src/app/home/home.page.html`
3. `src/app/home/home.page.scss`

### `home.page.ts`

```ts
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule, ToastController } from '@ionic/angular';

type Task = {
  id: number;
  text: string;
  done: boolean;
};

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule],
})
export class HomePage {
  newTask = '';
  tasks: Task[] = [];
  private nextId = 1;

  constructor(private toastCtrl: ToastController) {
    const saved = localStorage.getItem('ex7_tasks');
    if (saved) {
      this.tasks = JSON.parse(saved) as Task[];
      const maxId = this.tasks.reduce((m, t) => Math.max(m, t.id), 0);
      this.nextId = maxId + 1;
    }
  }

  async addTask() {
    const text = this.newTask.trim();
    if (!text) {
      const toast = await this.toastCtrl.create({
        message: 'Enter a task',
        duration: 1200,
        color: 'warning',
      });
      await toast.present();
      return;
    }

    this.tasks = [{ id: this.nextId++, text, done: false }, ...this.tasks];
    this.newTask = '';
    this.save();
  }

  toggleTask(task: Task) {
    task.done = !task.done;
    this.save();
  }

  deleteTask(id: number) {
    this.tasks = this.tasks.filter((t) => t.id !== id);
    this.save();
  }

  trackById(_: number, task: Task) {
    return task.id;
  }

  private save() {
    localStorage.setItem('ex7_tasks', JSON.stringify(this.tasks));
  }
}
```

### `home.page.html`

```html
<ion-header>
  <ion-toolbar color="primary">
    <ion-title>Task Manager</ion-title>
  </ion-toolbar>
</ion-header>

<ion-content class="ion-padding">
  <ion-item>
    <ion-input
      placeholder="Enter task"
      [(ngModel)]="newTask"
      (keyup.enter)="addTask()">
    </ion-input>
    <ion-button slot="end" (click)="addTask()">Add</ion-button>
  </ion-item>

  <ion-list>
    <ion-item *ngFor="let task of tasks; trackBy: trackById">
      <ion-checkbox slot="start" [checked]="task.done" (ionChange)="toggleTask(task)"></ion-checkbox>
      <ion-label [class.done]="task.done">{{ task.text }}</ion-label>
      <ion-button slot="end" color="danger" fill="clear" (click)="deleteTask(task.id)">Delete</ion-button>
    </ion-item>
  </ion-list>
</ion-content>
```

### `home.page.scss`

```scss
.done {
  text-decoration: line-through;
  opacity: 0.65;
}
```

### Run

```bash
ionic serve
```

---

## Exercise 8: Application for BMI Calculator Using React Native

### Create Project

```bash
npx create-expo-app ex8-bmi --template blank
cd ex8-bmi
```

### Replace File

1. `App.js`

### `App.js`

```javascript
import React, { useMemo, useState } from 'react';
import { SafeAreaView, Text, TextInput, TouchableOpacity, View, StyleSheet } from 'react-native';

export default function App() {
  const [weight, setWeight] = useState('');
  const [height, setHeight] = useState('');
  const [result, setResult] = useState(null);

  const category = useMemo(() => {
    if (result == null) return '';
    if (result < 18.5) return 'Underweight';
    if (result < 25) return 'Normal';
    if (result < 30) return 'Overweight';
    return 'Obese';
  }, [result]);

  const calculateBMI = () => {
    const w = parseFloat(weight);
    const h = parseFloat(height);

    if (!w || !h || h <= 0) {
      setResult(null);
      return;
    }

    const bmi = w / ((h / 100) * (h / 100));
    setResult(Number(bmi.toFixed(2)));
  };

  const reset = () => {
    setWeight('');
    setHeight('');
    setResult(null);
  };

  return (
    <SafeAreaView style={styles.container}>
      <Text style={styles.title}>BMI Calculator</Text>

      <TextInput
        style={styles.input}
        placeholder="Weight (kg)"
        keyboardType="numeric"
        value={weight}
        onChangeText={setWeight}
      />

      <TextInput
        style={styles.input}
        placeholder="Height (cm)"
        keyboardType="numeric"
        value={height}
        onChangeText={setHeight}
      />

      <TouchableOpacity style={styles.btn} onPress={calculateBMI}>
        <Text style={styles.btnText}>Calculate</Text>
      </TouchableOpacity>

      <TouchableOpacity style={[styles.btn, styles.btnAlt]} onPress={reset}>
        <Text style={styles.btnText}>Reset</Text>
      </TouchableOpacity>

      {result !== null && (
        <View style={styles.resultBox}>
          <Text style={styles.resultText}>BMI: {result}</Text>
          <Text style={styles.resultText}>Category: {category}</Text>
        </View>
      )}
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    padding: 20,
    backgroundColor: '#eef2ff',
  },
  title: {
    fontSize: 28,
    fontWeight: '700',
    textAlign: 'center',
    marginBottom: 20,
  },
  input: {
    borderWidth: 1,
    borderColor: '#cbd5e1',
    borderRadius: 10,
    padding: 12,
    marginBottom: 12,
    backgroundColor: '#fff',
  },
  btn: {
    backgroundColor: '#1d4ed8',
    padding: 12,
    borderRadius: 10,
    marginTop: 8,
  },
  btnAlt: {
    backgroundColor: '#475569',
  },
  btnText: {
    color: '#fff',
    textAlign: 'center',
    fontWeight: '700',
  },
  resultBox: {
    marginTop: 20,
    padding: 16,
    borderRadius: 12,
    backgroundColor: '#fff',
  },
  resultText: {
    fontSize: 18,
    marginBottom: 6,
    fontWeight: '600',
  },
});
```

### Run

```bash
npx expo start
```

---

## Exercise 9: Expense Manager App Using Flutter

### Create Project

```bash
flutter create ex9_expense_manager
cd ex9_expense_manager
```

### Replace File

1. `lib/main.dart`

### `lib/main.dart`

```dart
import 'package:flutter/material.dart';

void main() {
  runApp(const ExpenseApp());
}

class ExpenseApp extends StatelessWidget {
  const ExpenseApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Expense Manager',
      theme: ThemeData(useMaterial3: true, colorSchemeSeed: Colors.teal),
      home: const ExpenseHomePage(),
    );
  }
}

class ExpenseItem {
  final String title;
  final double amount;
  final DateTime date;

  ExpenseItem({required this.title, required this.amount, required this.date});
}

class ExpenseHomePage extends StatefulWidget {
  const ExpenseHomePage({super.key});

  @override
  State<ExpenseHomePage> createState() => _ExpenseHomePageState();
}

class _ExpenseHomePageState extends State<ExpenseHomePage> {
  final List<ExpenseItem> _expenses = [];

  double get _total => _expenses.fold(0, (sum, e) => sum + e.amount);

  void _addExpense() {
    final titleController = TextEditingController();
    final amountController = TextEditingController();

    showDialog<void>(
      context: context,
      builder: (context) {
        return AlertDialog(
          title: const Text('Add Expense'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              TextField(
                controller: titleController,
                decoration: const InputDecoration(labelText: 'Title'),
              ),
              TextField(
                controller: amountController,
                keyboardType: const TextInputType.numberWithOptions(decimal: true),
                decoration: const InputDecoration(labelText: 'Amount'),
              ),
            ],
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.pop(context),
              child: const Text('Cancel'),
            ),
            ElevatedButton(
              onPressed: () {
                final title = titleController.text.trim();
                final amount = double.tryParse(amountController.text.trim());

                if (title.isEmpty || amount == null || amount <= 0) {
                  return;
                }

                setState(() {
                  _expenses.insert(
                    0,
                    ExpenseItem(title: title, amount: amount, date: DateTime.now()),
                  );
                });

                Navigator.pop(context);
              },
              child: const Text('Add'),
            ),
          ],
        );
      },
    );
  }

  void _removeExpense(int index) {
    setState(() {
      _expenses.removeAt(index);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Expense Manager'),
        actions: [
          IconButton(onPressed: _addExpense, icon: const Icon(Icons.add)),
        ],
      ),
      body: Column(
        children: [
          Container(
            width: double.infinity,
            padding: const EdgeInsets.all(16),
            color: Colors.teal.shade50,
            child: Text(
              'Total: Rs ${_total.toStringAsFixed(2)}',
              style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
            ),
          ),
          Expanded(
            child: _expenses.isEmpty
                ? const Center(child: Text('No expenses yet. Tap + to add.'))
                : ListView.builder(
                    itemCount: _expenses.length,
                    itemBuilder: (context, index) {
                      final e = _expenses[index];
                      return Dismissible(
                        key: ValueKey('${e.title}-${e.date.microsecondsSinceEpoch}'),
                        background: Container(color: Colors.red),
                        onDismissed: (_) => _removeExpense(index),
                        child: ListTile(
                          title: Text(e.title),
                          subtitle: Text(
                            '${e.date.day}/${e.date.month}/${e.date.year}',
                          ),
                          trailing: Text(
                            'Rs ${e.amount.toStringAsFixed(2)}',
                            style: const TextStyle(fontWeight: FontWeight.bold),
                          ),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _addExpense,
        child: const Icon(Icons.add),
      ),
    );
  }
}
```

### Run

```bash
flutter run
```

---

## Exercise 10: Application to Convert Units from Imperial System to Metric System (Android)

### Files to Replace

1. `app/src/main/res/layout/activity_main.xml`
2. `app/src/main/res/values/strings.xml`
3. `app/src/main/java/com/example/ex10/MainActivity.kt`

### `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etValue"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter value"
        android:inputType="numberDecimal|numberSigned" />

    <Spinner
        android:id="@+id/spConversion"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="10dp"
        android:entries="@array/conversions" />

    <Button
        android:id="@+id/btnConvert"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="12dp"
        android:text="Convert" />

    <TextView
        android:id="@+id/tvResult"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:text="Result:"
        android:textSize="20sp"
        android:textStyle="bold" />

</LinearLayout>
```

### `strings.xml` (inside `<resources>`)

```xml
<string-array name="conversions">
    <item>Inches to Centimeters</item>
    <item>Feet to Meters</item>
    <item>Miles to Kilometers</item>
    <item>Pounds to Kilograms</item>
</string-array>
```

### `MainActivity.kt`

```kotlin
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

            val (result, unit) = when (type) {
                "Inches to Centimeters" -> input * 2.54 to "cm"
                "Feet to Meters" -> input * 0.3048 to "m"
                "Miles to Kilometers" -> input * 1.60934 to "km"
                "Pounds to Kilograms" -> input * 0.453592 to "kg"
                else -> input to ""
            }

            tvResult.text = "Result: ${"%.4f".format(result)} $unit"
        }
    }
}
```

---

## Exercise 11: Application for Lost and Found Community (Android)

### Files to Replace

1. `app/src/main/res/layout/activity_main.xml`
2. `app/src/main/java/com/example/ex11/MainActivity.kt`

### `activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/etTitle"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Item title" />

    <EditText
        android:id="@+id/etDescription"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:hint="Description" />

    <RadioGroup
        android:id="@+id/rgType"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:orientation="horizontal">

        <RadioButton
            android:id="@+id/rbLost"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:checked="true"
            android:text="Lost" />

        <RadioButton
            android:id="@+id/rbFound"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Found" />
    </RadioGroup>

    <Button
        android:id="@+id/btnAdd"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:text="Post" />

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:orientation="horizontal"
        android:weightSum="3">

        <Button
            android:id="@+id/btnAll"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="All" />

        <Button
            android:id="@+id/btnLost"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Lost" />

        <Button
            android:id="@+id/btnFound"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Found" />
    </LinearLayout>

    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginTop="10dp"
        android:layout_weight="1" />

</LinearLayout>
```

### `MainActivity.kt`

```kotlin
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
                "[${it.type}] ${it.title}\n${it.description}"
            }
        )

        if (displayList.isEmpty()) {
            displayList.add("No posts yet")
        }

        adapter.notifyDataSetChanged()
    }
}
```

---

## Quick Exam Tips

1. For Android exercises, always sync Gradle once after pasting files.
2. If package name differs, update the first line in Kotlin files.
3. For Cordova, add platform/plugin before running.
4. For Ionic/React Native/Flutter, use the exact project creation command first, then replace files.

All programs above are intentionally kept short, clean, and exam-friendly.