package br.com.vaniala.starwars.ui.home.recycler

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.BuildConfig
import br.com.vaniala.starwars.databinding.ListItemCategoryBinding
import br.com.vaniala.starwars.domain.model.Category
import coil.load
import java.util.Locale

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryViewHolder(private val binding: ListItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category) {
        binding.itemCategoryName.text = category.name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
        }
        binding.itemCategoryImage.load(getUrlImage(category.name))
    }

    fun getUrlImage(typeCategory: String): String {
        if (typeCategory.isEmpty()) {
            return ""
        }
        var type = typeCategory
        if (type == "people") {
            type = "character"
        }
        return "${BuildConfig.BASE_URL_IMAGES}categories/$type.jpg"
    }
}
