package br.com.vaniala.starwars.ui.films.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.core.loadImage
import br.com.vaniala.starwars.databinding.ListItemGridBinding
import br.com.vaniala.starwars.domain.model.Film

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 12/04/23.
 *
 */
class FilmsViewHolder(
    private val binding: ListItemGridBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film, onItemClickListener: (film: Film) -> Unit) {
        binding.root.setOnClickListener {
            onItemClickListener(film)
        }
        binding.itemGridTitle.text = film.titleFormatted
        binding.itemGridImage.loadImage(
            url = film.image,
            progressBar = binding.itemGridProgress,
        )
    }
}
