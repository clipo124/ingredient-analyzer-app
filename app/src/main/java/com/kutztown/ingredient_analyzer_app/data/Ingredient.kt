package com.kutztown.ingredient_analyzer_app.data

data class Ingredient(
    val name: String? = null,
    val description: String? = null,
    val tag: Map<String, Boolean>? = null
)
