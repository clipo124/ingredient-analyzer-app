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
                "A sweet crystallizable material that consists wholly or essentially of sucrose, is colorless or white when pure tending to brown when less refined, is obtained commercially from sugarcane or sugar beet and less extensively from sorghum, maples, and palms, and is important as a source of dietary carbohydrate and as a sweetener and preservative of other foods.",
                true
            ),
            Ingredient("Enriched Bleached Flour",
                "Enriched flour is flour with specific nutrients returned to it that have been lost while being prepared. These restored nutrients include iron and B vitamins (folic acid, riboflavin, niacin, and thiamine)."
                , true
            ),
            Ingredient(
                "Canola Oil",
                "An edible vegetable oil obtained from the seeds of canola that is high in monounsaturated fatty acids.",
                true
            ),
            Ingredient(
                "Soybean Oil",
                "A pale yellow drying or semidrying oil that is obtained from soybeans and is used chiefly as a food, in paints, varnishes, linoleum, printing ink, and soap, and as a source of phospholipids, fatty acids, and sterols.",
                true
            ),
            Ingredient(
                "Salt",
                "A crystalline compound NaCl that consists of sodium chloride, is abundant in nature, and is used especially to season or preserve food or in industry.",
                true
            ),
            Ingredient("Artificial Flavor",
                "An artificial flavor is any non-natural substance that is used to create flavors in foods, beverages, or medications."
                , true
            ),
            Ingredient(
                "Sodium Bicarbonate",
                "A white crystalline weakly alkaline salt NaHCO3 used especially in baking powders and fire extinguishers and in medicine as an antacid.",
                true
            ),
            Ingredient(
                "Cocoa",
                "Powdered ground roasted cacao beans from which a portion of the fat has been removed.",
                true
            ),
            Ingredient("Semi-Sweet Chocolate Chips",
                "Semi-Sweet Chocolate Chips are a form of dark chocolate that may contain milk."
                , null
            ),
            Ingredient(
                "Bittersweet Chocolate Chips",
                "Bittersweet Chocolate Chips may contain milk fat.",
                null
            ),
            Ingredient(
                "Milk Chocolate Chips",
                "Milk chocolate is a form of solid chocolate containing cocoa, sugar and milk.",
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