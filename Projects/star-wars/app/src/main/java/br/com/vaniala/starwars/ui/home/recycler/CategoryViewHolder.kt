package br.com.vaniala.starwars.ui.home.recycler

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.databinding.ListItemCategoryBinding
import br.com.vaniala.starwars.domain.model.Category

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryViewHolder(val binding: ListItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: Category) {
        binding.itemCategoryName.text = category.url
    }
}
