package ru.netology.nerwcipe3.adapter


import ru.netology.nerwcipe3.classes.Recipe


interface RecipeInteractionListener {

    fun onRemoveClicked(recipeId: Long)
    fun onEditClicked(recipe: Recipe)
    fun onToRecipe(recipe: Recipe)
    fun onLikeClicked(recipeId: Long)
}