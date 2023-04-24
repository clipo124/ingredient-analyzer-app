package com.kutztown.ingredient_analyzer_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView.OnQueryTextListener
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
            startActivity(intent)
        }

        ingredientList = arrayListOf(
            Ingredient(
                "Olive Oil",
                "A pale yellow to yellowish-green nondrying oil that is obtained " +
                        "from olives, is high in monounsaturated fat, and is used chiefly as a " +
                        "salad oil and in cooking.",
                true
            ),
            Ingredient("All Purpose Flour",
                "Flour made from a blend of hard or soft " +
                        "wheats suitable for all cookery except the finest cakes."
                , true
            ),
            Ingredient(
                "Butter",
                "A solid emulsion of fat globules, air, and water made by churning milk" +
                        " or cream and used as food.",
                false
            ),
            Ingredient(
                "Chicken",
                "The common domestic fowl.",
                false
            )
        )


        liveIngredientList = ArrayList()
        liveIngredientList.addAll(ingredientList)
        ingredientRecyclerView = findViewById(R.id.ingredient_recycler)
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this)
        ingredientRecyclerView.setHasFixedSize(false)
        ingredientRecyclerView.adapter = IngredientRecyclerAdapter(liveIngredientList)

        searchView = findViewById(R.id.search_ingredient)
        searchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryText: String?): Boolean {
                liveIngredientList.clear()
                val searchText = queryText!!.lowercase()

                if (searchText.isNotBlank()) {
                    ingredientList.forEach {
                        if (it.name.lowercase().contains(searchText)) {
                            liveIngredientList.add(it)
                        }
                    }
                    ingredientRecyclerView.adapter!!.notifyDataSetChanged()
                    return true
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

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