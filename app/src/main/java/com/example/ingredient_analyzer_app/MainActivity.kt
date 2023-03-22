package com.example.ingredient_analyzer_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ingredient_analyzer_app.data.Ingredient
import com.example.ingredient_analyzer_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var photoButton: Button
    private lateinit var ingredientRecyclerView: RecyclerView
    private lateinit var ingredientList: ArrayList<Ingredient>
    private lateinit var liveIngredientList: ArrayList<Ingredient>
    private lateinit var searchView: SearchView

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
        liveIngredientList = arrayListOf<Ingredient>()
        liveIngredientList.addAll(ingredientList)
        ingredientRecyclerView = findViewById(R.id.ingredient_recycler)
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this)
        ingredientRecyclerView.setHasFixedSize(false)
        ingredientRecyclerView.adapter = IngredientRecyclerAdapter(liveIngredientList)

        searchView = findViewById(R.id.search_ingredient)
        searchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                liveIngredientList.clear()
                val searchText = newText!!.lowercase()
                if (searchText.isNotBlank()) {
                    ingredientList.forEach {
                        if (it.name.lowercase().contains(searchText)) {
                            liveIngredientList.add(it)
                        }
                    }
                    ingredientRecyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    liveIngredientList.clear()
                    liveIngredientList.addAll(ingredientList)
                    ingredientRecyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

    }

    override fun onResume() {
        super.onResume()
        searchView.setQuery("", false)
        binding.root.requestFocus()
    }

}