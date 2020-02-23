package app.recrutamento.android.challengealpha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import app.recrutamento.android.challengealpha.adapters.general.AdapterItensInterface
import app.recrutamento.android.challengealpha.databinding.HotelItemBinding
import app.recrutamento.android.challengealpha.model.hotel.Hotel
import com.squareup.picasso.Picasso

class HotelAdapter(
    var items: List<Hotel>) :
    RecyclerView.Adapter<HotelAdapter.ViewHolder>(),
    AdapterItensInterface {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: HotelItemBinding =
            HotelItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun replaceItems(item: List<*>) {
        this.items = item as List<Hotel>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel = items[position]

        holder.bind(hotel)
    }

    class ViewHolder(val binding: HotelItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hotel: Hotel) {
            binding.hotel = hotel
            binding.executePendingBindings()
        }
    }

    object DataBindingAdapter {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(view: ImageView?, url: String?) {
            Picasso.get().load(url)?.into(view)
        }
    }
}