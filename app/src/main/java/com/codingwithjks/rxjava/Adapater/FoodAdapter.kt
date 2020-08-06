package com.codingwithjks.rxjava.Adapater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithjks.rxjava.Model.Food
import com.codingwithjks.rxjava.R

class FoodAdapter(private val context: Context,private var foodList:ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
       return FoodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false))
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food=foodList[position]
        holder.price.text=food.price.toString()
        holder.name.text=food.name
        Glide.with(context)
            .load(food.image)
            .into(holder.image)
    }

    fun setData(foodList: ArrayList<Food>)
    {
        this.foodList=foodList
        notifyDataSetChanged()
    }

    inner class FoodViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val name:TextView=itemView.findViewById(R.id.name)
        val price:TextView=itemView.findViewById(R.id.price)
        val image:ImageView=itemView.findViewById(R.id.image)
    }
}
