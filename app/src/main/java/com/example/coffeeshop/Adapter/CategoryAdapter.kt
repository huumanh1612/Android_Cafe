package com.example.coffeeshop.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.Domain.CategoryModel
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ViewholderCategoryBinding


class CategoryAdapter(val items:MutableList<CategoryModel>):
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

        private  lateinit var context: Context
        private  var selectedPosition=-1
    private var lastSelectedPosition=-1

    inner class Viewholder(val binding:ViewholderCategoryBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item=items[position]
        holder.binding.titleCat.text=item.title

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.dark_brown_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
        }
        else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.darkBrown))
        }
    }

    override fun getItemCount(): Int = items.size
}