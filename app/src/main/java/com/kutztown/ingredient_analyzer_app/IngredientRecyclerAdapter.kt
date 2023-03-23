package com.kutztown.ingredient_analyzer_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kutztown.ingredient_analyzer_app.R
import com.kutztown.ingredient_analyzer_app.data.Ingredient

class IngredientRecyclerAdapter(private val ingredientList : ArrayList<Ingredient>) :
    RecyclerView.Adapter<IngredientRecyclerAdapter.IngredientViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.ingredient_recycler_item,
            parent,
            false
        )
        return IngredientViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: IngredientViewHolder,
        position: Int
    ) {
        val currentItem: Ingredient = ingredientList[position]
        holder.ingredientName.text = currentItem.name


    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    class IngredientViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val ingredientName: TextView = itemView.findViewById(R.id.ingredientName)

    }



}