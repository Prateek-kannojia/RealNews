package com.example.realnews

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.example.realnews.itemadapter.viewholder
import com.squareup.picasso.Picasso

class itemadapter(private val context: Context,private val listener: clickinterface):RecyclerView.Adapter<viewholder>() {
    private val modellist:ArrayList<model> = ArrayList()

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val items= modellist[position]
        holder.title.text = items.title
        holder.source.text = items.name
        Picasso.get().load(items.image).into(holder.newsimage)
        holder.itemView.setOnClickListener {
            listener.onnewsclick(position)
        }
    }
    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun getItemCount(): Int {
        return modellist.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatednews:ArrayList<model>){
        modellist.clear()
        modellist.addAll(updatednews)
        notifyDataSetChanged()
    }
    interface clickinterface{
        fun onnewsclick(position: Int)
    }
    inner class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsimage: ImageView = itemView.findViewById(R.id.newsimage)
        var source: TextView = itemView.findViewById(R.id.author)
        var title: TextView = itemView.findViewById(R.id.title)
    }
}