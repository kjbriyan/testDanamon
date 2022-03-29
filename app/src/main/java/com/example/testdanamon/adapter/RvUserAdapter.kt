package com.example.testdanamon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testdanamon.R
import com.example.testdanamon.model.ResponseUser

class RvUserAdapter : RecyclerView.Adapter<RvUserAdapter.ViewHolder>() {
    private val list = ArrayList<ResponseUser>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: ResponseUser) {
            val email = itemView.findViewById<TextView>(R.id.tv_email)
            val pass = itemView.findViewById<TextView>(R.id.tv_pass)
            val level = itemView.findViewById<TextView>(R.id.tv_level)

            email.text = get.title
            pass.text = get.thumbnailUrl
            level.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvUserAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvUserAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addData(items: ArrayList<ResponseUser>,i:Int,size:Int) {
        for (i in 0..size) {
            list.add(items.get(i))
        }
    }
}