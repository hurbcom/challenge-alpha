package br.com.vaniala.starwars.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.databinding.ListItemCategoryBinding
import br.com.vaniala.starwars.domain.model.Category
import coil.load

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryViewHolder(private val binding: ListItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: Category, onItemClickListener: (category: Category) -> Unit) {
        binding.root.setOnClickListener {
            onItemClickListener(category)
        }

        binding.itemCategoryName.text = category.nameFormatted
        binding.itemCategoryImage.load(category.image)
    }
}
