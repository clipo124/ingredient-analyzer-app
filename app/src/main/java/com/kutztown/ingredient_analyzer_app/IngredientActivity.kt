package com.kutztown.ingredient_analyzer_app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kutztown.ingredient_analyzer_app.databinding.ActivityIngredientBinding


class IngredientActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityIngredientBinding
    lateinit var name: TextView
    lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityIngredientBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        name = findViewById(R.id.name)
        description = findViewById(R.id.description)
        val nameString = intent.getStringExtra("name")
        val descriptionString = intent.getStringExtra("description")
        name.text = nameString
        description.text = descriptionString

    }
}