package com.example.testdanamon.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testdanamon.R
import com.example.testdanamon.adapter.RvUserAdapter
import com.example.testdanamon.model.ResponseUser
import com.example.testdanamon.network.InitRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {
    lateinit var adapter: RvUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        adapter = RvUserAdapter()

        getData()
    }

    fun getData() {
        InitRetrofit().getInstance().getData().enqueue(object : Callback<List<ResponseUser>> {
            override fun onResponse(
                call: Call<List<ResponseUser>>,
                response: Response<List<ResponseUser>>
            ) {
                val data = response.body()
                if (response.isSuccessful) {
                    adapter.addData(data as ArrayList<ResponseUser>,0,10)
                    val rv = findViewById<RecyclerView>(R.id.rv_user)
                    rv.adapter = adapter
                    Log.e("UserActivitys", " " + data.size)
                }
            }

            override fun onFailure(call: Call<List<ResponseUser>>, t: Throwable) {
                Log.e("userActivitys", t.cause.toString())
            }

        })
    }
}