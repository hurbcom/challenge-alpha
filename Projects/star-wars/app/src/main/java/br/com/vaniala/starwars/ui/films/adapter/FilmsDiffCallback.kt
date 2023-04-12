package br.com.vaniala.starwars.ui.films.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.vaniala.starwars.domain.model.Films

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
class FilmsDiffCallback : DiffUtil.ItemCallback<Films>() {
    override fun areItemsTheSame(oldItem: Films, newItem: Films): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Films, newItem: Films): Boolean =
        oldItem == newItem
}
