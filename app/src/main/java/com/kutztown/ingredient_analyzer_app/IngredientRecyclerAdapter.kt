package com.kutztown.ingredient_analyzer_app

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.kutztown.ingredient_analyzer_app.R
import com.kutztown.ingredient_analyzer_app.data.Ingredient

class IngredientRecyclerAdapter(private val ingredientList : ArrayList<Ingredient>) :
    RecyclerView.Adapter<IngredientRecyclerAdapter.IngredientViewHolder>() {
    lateinit var currentItem: Ingredient
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
        var currentItem: Ingredient = ingredientList[position]
        holder.ingredientName.text = currentItem.name
        when (currentItem.isVegan) {
            true -> {
                holder.isVegan.text = "Vegan"
                holder.card.setCardBackgroundColor(Color.parseColor("#A5F0C5"))

            }
            false -> {
                holder.isVegan.text = "Not Vegan"
                holder.card.setCardBackgroundColor(Color.parseColor("#FB6962"))
            }
            null -> {
                holder.isVegan.text = "Maybe Vegan"
                holder.card.setCardBackgroundColor(Color.parseColor("#FCFC99"))
            }
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, IngredientActivity::class.java)
            intent.putExtra("name", currentItem.name)
            intent.putExtra("description", currentItem.description)
            startActivity(holder.itemView.context, intent, null)
            //Toast.makeText(holder.itemView.context, currentItem.description, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    class IngredientViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val ingredientName: TextView = itemView.findViewById(R.id.ingredientName)
        val isVegan: TextView = itemView.findViewById(R.id.isVegan)
        val card: MaterialCardView = itemView.findViewById(R.id.ingredientCard)

    }



}