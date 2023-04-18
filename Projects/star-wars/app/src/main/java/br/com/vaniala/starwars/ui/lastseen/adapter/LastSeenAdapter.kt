package br.com.vaniala.starwars.ui.lastseen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.com.vaniala.starwars.databinding.ListItemLastSeenBinding
import br.com.vaniala.starwars.domain.model.LastSeen

class LastSeenAdapter(
    var onItemClickListener: (lastSeen: LastSeen) -> Unit = {},
) : ListAdapter<LastSeen, LastSeenViewHolder>(LastSeenDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastSeenViewHolder {
        val binding = ListItemLastSeenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return LastSeenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LastSeenViewHolder, position: Int) {
        val film = getItem(position)
        holder.bind(film, onItemClickListener)
    }

    override fun getItemCount(): Int = currentList.size
}
