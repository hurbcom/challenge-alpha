package br.com.vaniala.starwars.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.vaniala.starwars.domain.model.Category

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 10/04/23.
 *
 */
class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem == newItem
}
