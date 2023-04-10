package br.com.vaniala.starwars.ui.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.vaniala.starwars.databinding.ListItemCategoryBinding
import br.com.vaniala.starwars.domain.model.Category
/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryListAdapter : ListAdapter<Category, CategoryViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    override fun getItemCount(): Int = currentList.size
}
