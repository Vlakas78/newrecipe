package ru.netology.nerwcipe3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.netology.nerwcipe3.R
import ru.netology.nerwcipe3.adapter.ViewStagesAdapter
import ru.netology.nerwcipe3.classes.Categories
import ru.netology.nerwcipe3.classes.Recipe
import ru.netology.nerwcipe3.databinding.RecipeViewFragmentBinding
import ru.netology.nerwcipe3.viewModel.RecipeViewModel


class RecipeViewFragment : Fragment(R.layout.recipe_view_fragment) {

    private val viewModel by activityViewModels<RecipeViewModel>()

    private lateinit var binding: RecipeViewFragmentBinding
    private var currentRecipe: Recipe? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecipeViewFragmentBinding.inflate(
        layoutInflater, container, false
    ).also {
        binding = it

        val adapter = ViewStagesAdapter()
        val stageRecyclerView = binding.stageRecyclerView
        stageRecyclerView.adapter = adapter

        with(binding) {
            titleEditText.addTextChangedListener {
                if (titleEditText.text.isNullOrBlank()) {
                    titleTextView.visibility = View.GONE
                    titleEditText.textSize = 32F
                } else {
                    titleTextView.visibility = View.VISIBLE
                    titleEditText.textSize = 20F
                }
            }
        }

        viewModel.getCurrentRecipe().observe(viewLifecycleOwner) { recipe ->
            if (recipe != null) {
                currentRecipe = recipe
                bind(recipe)
                adapter.submitList(recipe.stages)
            }
        }
    }.root

    private fun bind(recipe: Recipe?) {
        if (recipe != null) {
            binding.titleEditText.text = recipe.title
            binding.categoryTextViewContent.text = getCategoriesText(recipe.categories)
        }
    }

    private fun getCategoriesText(list: List<Int>): String {
        var text =
            if (list.contains(Categories.European.id)) getString(R.string.category_european) + "\n" else ""
        text += if (list.contains(Categories.Asian.id)) getString(R.string.category_asian) + "\n" else ""
        text += if (list.contains(Categories.PanAsian.id)) getString(R.string.category_pan_asian) + "\n" else ""
        text += if (list.contains(Categories.Eastern.id)) getString(R.string.category_eastern) + "\n" else ""
        text += if (list.contains(Categories.American.id)) getString(R.string.category_american) + "\n" else ""
        text += if (list.contains(Categories.Russian.id)) getString(R.string.category_russian) + "\n" else ""
        text += if (list.contains(Categories.Mediterranean.id)) getString(R.string.category_mediterranean) + "\n" else ""
        text = text.removeSuffix("\n")
        return text
    }
}