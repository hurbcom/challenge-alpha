package br.com.vaniala.starwars.ui.lastseen.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.vaniala.starwars.domain.model.LastSeen

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/04/23.
 *
 */
class LastSeenDiffCallback : DiffUtil.ItemCallback<LastSeen>() {
    override fun areItemsTheSame(oldItem: LastSeen, newItem: LastSeen): Boolean =
        oldItem.film?.title == newItem.film?.title || oldItem.character?.name == newItem.character?.name

    override fun areContentsTheSame(oldItem: LastSeen, newItem: LastSeen): Boolean =
        oldItem == newItem
}
