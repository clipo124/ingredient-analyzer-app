package com.example.ingredient_analyzer_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ingredient_analyzer_app.data.Ingredient
import com.example.ingredient_analyzer_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoButton: Button
    private lateinit var ingredientRecyclerView: RecyclerView
    private lateinit var ingredientList: ArrayList<Ingredient>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        photoButton = binding.cameraButton
        photoButton.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        ingredientList = arrayListOf(
            Ingredient("Olive Oil"),
            Ingredient("All Purpose Flour"),
            Ingredient("Butter"),
            Ingredient("Chicken"),
            Ingredient("Sugar"),
            Ingredient("Salt"),
            Ingredient("Egg"),
            Ingredient("Rice"),
            Ingredient("Vegetable Oil"),
            Ingredient("Pork"),
            Ingredient("Beef"),
            Ingredient("Cheese"),
            Ingredient("Garlic"),
            Ingredient("Orange"),
            Ingredient("Turkey")
        )

        ingredientRecyclerView = findViewById(R.id.ingredient_recycler)
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this)
        ingredientRecyclerView.setHasFixedSize(false)
        ingredientRecyclerView.adapter = IngredientRecyclerAdapter(ingredientList)

    }
}