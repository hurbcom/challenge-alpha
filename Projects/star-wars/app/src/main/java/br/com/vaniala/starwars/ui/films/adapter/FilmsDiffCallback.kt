package br.com.vaniala.starwars.ui.films.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.vaniala.starwars.domain.model.Film

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
class FilmsDiffCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean =
        oldItem.title == newItem.title && oldItem.opening_crawl == newItem.opening_crawl
}
