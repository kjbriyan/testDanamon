package com.example.testdanamon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.testdanamon.DatabaseHelper
import com.example.testdanamon.R

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val db = DatabaseHelper(this,null)
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_pass = findViewById<EditText>(R.id.et_pass)
        val sp = findViewById<Spinner>(R.id.sp_level)
        val btn = findViewById<Button>(R.id.btn_sign)

        btn.setOnClickListener {
            val  res = db.register(et_email.text.toString(),et_pass.text.toString(),sp.selectedItem.toString())
            Log.e("SignActivitys",""+res)
            if (res){
                finish()
            }
        }
    }
}