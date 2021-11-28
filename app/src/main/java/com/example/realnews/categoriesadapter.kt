package com.example.realnews


import androidx.recyclerview.widget.RecyclerView
import com.example.realnews.categoriesadapter.Viewholder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.realnews.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.widget.TextView
import java.util.*

class categoriesadapter(private var modelcategory: ArrayList<modelcategory>, private var context: Context,private val listener: clickinterface) : RecyclerView.Adapter<Viewholder>(){

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(context).inflate(R.layout.categories_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Viewholder, @SuppressLint("RecyclerView") position: Int) {
        holder.categorytext.text = modelcategory[position].category
        Picasso.get().load(modelcategory[position].categoryimgurl).into(holder.category)
        holder.itemView.setOnClickListener {
            listener.oncategoryclick(position)
        }
    }

    // androidx.recyclerview.widget.RecyclerView.Adapter
    override fun getItemCount(): Int {
        return modelcategory.size
    }
    interface clickinterface{
        fun oncategoryclick(position: Int)
    }
    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category: ImageView = itemView.findViewById<View>(R.id.category) as ImageView
        var categorytext: TextView = itemView.findViewById<View>(R.id.categorytext) as TextView

    }
}