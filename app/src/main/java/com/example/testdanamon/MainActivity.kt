package com.example.testdanamon

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testdanamon.ui.AdminActivity
import com.example.testdanamon.ui.SignActivity
import com.example.testdanamon.ui.UserActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_email = findViewById<EditText>(R.id.et_email)
        val et_pass = findViewById<EditText>(R.id.et_pass)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val tv_sign = findViewById<TextView>(R.id.tv_signup)
        val db = DatabaseHelper(this, null)

        tv_sign.setOnClickListener {
            val i = Intent(this, SignActivity::class.java)

            startActivity(i)
        }

        btn_login.setOnClickListener {
            val res = db.login(et_email.text.toString(), et_pass.text.toString())
            res.moveToFirst()
            if (res.count == 0) {
                Toast.makeText(this, "Gagal Login", Toast.LENGTH_SHORT).show()
            } else {
                val level = res?.getString(res.getColumnIndex(DatabaseHelper.LEVEL)).toString()
                Log.e("MainActivity", "" + level)
                if (level == "Admin") {
                    val i = Intent(this, AdminActivity::class.java)
                    startActivity(i)
                } else {
                    val i = Intent(this, UserActivity::class.java)
                    startActivity(i)
                }
            }
        }
    }
}