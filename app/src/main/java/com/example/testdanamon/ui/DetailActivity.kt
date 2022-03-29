package com.example.testdanamon.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.testdanamon.DatabaseHelper
import com.example.testdanamon.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")
        val email = intent.getStringExtra("email")
        val pass = intent.getStringExtra("pass")
        val level = intent.getStringExtra("level")
        val et_email = findViewById<EditText>(R.id.et_email)
        val et_pass = findViewById<EditText>(R.id.et_pass)
        val sp = findViewById<Spinner>(R.id.sp_level)
        val btn = findViewById<Button>(R.id.btn_update)
        val del = findViewById<ImageView>(R.id.iv_del)

        et_email.setText(email)
        et_pass.setText(pass)
        Log.e("detailActivitys", " " + id.toString())
        if (level == "User") {
            sp.setSelection(1)
        }
        val db = DatabaseHelper(this, null)
        btn.setOnClickListener {
            db.update(
                id!!.toInt(),
                et_email.text.toString(),
                et_pass.text.toString(),
                sp.selectedItem.toString()
            )
            finish()
        }
        val builder = AlertDialog.Builder(this)

        del.setOnClickListener {

            builder.setTitle("Delete Item")
            builder.setMessage("Delete $email ?")
            builder.setPositiveButton("Yes") { dialog, which ->
                db.delete(id!!.toInt())
                finish()
            }

            builder.setNegativeButton("Cancel") { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    "Cancel", Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()
        }
    }
}