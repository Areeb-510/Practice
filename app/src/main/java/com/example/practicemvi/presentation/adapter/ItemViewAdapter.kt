package com.example.practicemvi.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicemvi.R
import com.example.practicemvi.data.model.Item

class ItemViewAdapter : RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder>() {

    private var items: List<Item> = listOf()

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.tv_image)
        val name : TextView = itemView.findViewById(R.id.tv_heading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        Glide.with(holder.itemView.context).load(item.imageUrl).into(holder.image)
    }

}