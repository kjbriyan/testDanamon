package com.example.testdanamon.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testdanamon.DatabaseHelper
import com.example.testdanamon.R
import com.example.testdanamon.adapter.RvAdminAdapter

class AdminActivity : AppCompatActivity() {
    lateinit var adapter: RvAdminAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        adapter = RvAdminAdapter()
        getData()
    }

    fun getData() {
        val db = DatabaseHelper(this, null)
        val rv = findViewById<RecyclerView>(R.id.rv_admin)
        val data = db.showUser()
        Log.e("AdminActivitys"," "+data)
        adapter.addData(data)
        rv.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
        getData()
    }
}