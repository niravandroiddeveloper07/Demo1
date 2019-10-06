package com.example.myapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.database.User
import kotlinx.android.synthetic.main.item_user_list.view.*
import java.lang.StringBuilder

class UserListAdapter(private val items : ArrayList<User>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(context)
            .load(items[position].avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.itemView.imgProfilke);

        holder.itemView.tvName.text=StringBuilder().append(items.get(position).first_name).append(" ").append(items.get(position).last_name)
        holder.itemView.tvEmail.text=items.get(position).email

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    }
}

