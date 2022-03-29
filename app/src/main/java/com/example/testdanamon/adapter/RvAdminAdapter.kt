package com.example.testdanamon.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testdanamon.R
import com.example.testdanamon.model.UserData
import com.example.testdanamon.ui.DetailActivity

class RvAdminAdapter : RecyclerView.Adapter<RvAdminAdapter.ViewHolder>() {
    private val list = ArrayList<UserData>()

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind (get: UserData){
            val email = itemView.findViewById<TextView>(R.id.tv_email)
            val pass  = itemView.findViewById<TextView>(R.id.tv_pass)
            val level = itemView.findViewById<TextView>(R.id.tv_level)
            val ll = itemView.findViewById<LinearLayout>(R.id.ll)

            email.text = get.email
            pass.text = get.pass
            level.text = get.level

            ll.setOnClickListener {
                val i = Intent(itemView.context,DetailActivity::class.java)
                i.putExtra("id",get.id.toString())
                i.putExtra("email",get.email)
                i.putExtra("pass",get.pass)
                i.putExtra("level",get.level)
                itemView.context.startActivity(i)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdminAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvAdminAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun addData(items : ArrayList<UserData>){
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

}