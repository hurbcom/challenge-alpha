package br.com.vaniala.starwars.ui.lastseen.adapter

import androidx.recyclerview.widget.RecyclerView
import br.com.vaniala.starwars.core.loadImage
import br.com.vaniala.starwars.databinding.ListItemLastSeenBinding
import br.com.vaniala.starwars.domain.model.LastSeen

class LastSeenViewHolder(
    private val binding: ListItemLastSeenBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lastSeen: LastSeen, onItemClickListener: (lastSeen: LastSeen) -> Unit) {
        binding.root.setOnClickListener {
            onItemClickListener(lastSeen)
        }
        lastSeen.film?.let {
            binding.itemLastSeemTitle.text = lastSeen.film!!.title
            binding.itemLastSeemImage.loadImage(
                url = lastSeen.urlFilm,
                progressBar = binding.itemLastSeemProgress,
                isCircle = true,
            )
        }
        lastSeen.character?.let {
            binding.itemLastSeemTitle.text = lastSeen.character!!.name
            binding.itemLastSeemImage.loadImage(
                url = lastSeen.character?.imagePeople,
                progressBar = binding.itemLastSeemProgress,
                isCircle = true,
            )
        }
    }
}
