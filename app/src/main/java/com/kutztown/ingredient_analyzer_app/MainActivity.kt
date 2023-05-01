package com.kutztown.ingredient_analyzer_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kutztown.ingredient_analyzer_app.data.Ingredient
import com.kutztown.ingredient_analyzer_app.databinding.ActivityMainBinding
import java.io.File

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
            startActivityForResult(intent, 0)
        }

        ingredientList = arrayListOf<Ingredient>(
            Ingredient(
                "Sugar",
                "",
                true
            ),
            Ingredient("Enriched Bleached Flour",
                ""
                , true
            ),
            Ingredient(
                "Canola Oil",
                "",
                true
            ),
            Ingredient(
                "Soybean Oil",
                "",
                true
            ),
            Ingredient(
                "Salt",
                "",
                true
            ),
            Ingredient("Artificial Flavor",
                ""
                , true
            ),
            Ingredient(
                "Sodium Bicarbonate",
                "",
                true
            ),
            Ingredient(
                "Cocoa",
                "",
                true
            ),
            Ingredient(
                "Sugar",
                "",
                true
            ),
            Ingredient("Semi-Sweet Chocolate Chips",
                ""
                , false
            ),
            Ingredient(
                "Bittersweet Chocolate Chips",
                "",
                false
            ),
            Ingredient(
                "Milk Chocolate Chips",
                "",
                false
            ),
        )

        val sortedList = ingredientList.sortedWith(compareBy { it.name })
        liveIngredientList = ArrayList()
        liveIngredientList.addAll(sortedList)
        ingredientRecyclerView = findViewById(R.id.ingredient_recycler)
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this)
        ingredientRecyclerView.setHasFixedSize(false)
        ingredientRecyclerView.adapter = IngredientRecyclerAdapter(liveIngredientList)
        ingredientRecyclerView.visibility = INVISIBLE

        searchView = findViewById(R.id.search_ingredient)
        searchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryText: String?): Boolean {

                val searchText: String = queryText!!.lowercase()

                if (searchText.isNotBlank()) {
                    liveIngredientList.clear()
                    ingredientRecyclerView.adapter!!.notifyDataSetChanged()
                    ingredientList.forEach {
                        if (it.name.lowercase().contains(searchText)) {
                            liveIngredientList.add(it)
                            ingredientRecyclerView.adapter!!.notifyDataSetChanged()
                        }
                    }
                    ingredientRecyclerView.adapter!!.notifyDataSetChanged()
                    Log.d("submit", "Submitted");
                    return true
                }
                Log.d("submit", "nothing submitted");
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        searchView.setOnCloseListener {
            Log.d("Close", "Closed")
            liveIngredientList.clear()
            sortedList.forEach {
                    liveIngredientList.add(it)
            }
            ingredientRecyclerView.adapter!!.notifyDataSetChanged()
            searchView.setQuery("", false)
            searchView.clearFocus()
            true
        }

    }
    override fun onResume() {
        super.onResume()
        searchView.setQuery("", false)
        binding.root.requestFocus()
    }

    // this called after child activity finishes.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // Get the result from intent
                ingredientRecyclerView.visibility = VISIBLE
            }
        }
    }


}