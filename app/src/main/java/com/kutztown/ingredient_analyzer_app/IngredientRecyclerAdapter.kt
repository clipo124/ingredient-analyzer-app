package com.kutztown.ingredient_analyzer_app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
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

        when (currentItem.tag?.get("vegan")) {
            true -> {
                holder.ingredientTag.setBackgroundColor(Color.parseColor("#79DE79"))
            }
            false -> {
                holder.ingredientTag.setBackgroundColor(Color.parseColor("#FB6962"))
            }
            else -> {
                holder.ingredientTag.setBackgroundColor(Color.parseColor("#FCFC99"))
            }
        }


    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    class IngredientViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val ingredientName: TextView = itemView.findViewById(R.id.ingredientName)
        val ingredientTag: TextView = itemView.findViewById(R.id.ingredientTag)

    }



}