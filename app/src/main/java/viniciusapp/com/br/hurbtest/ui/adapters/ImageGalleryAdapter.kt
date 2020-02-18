package viniciusapp.com.br.hurbtest.ui.adapters

import viniciusapp.com.br.hurbtest.models.GalleryModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import viniciusapp.com.br.hurbtest.R

class ImageGalleryAdapter(private val items: List<GalleryModel>, private val listener: Listener) : RecyclerView.Adapter<ImageGalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_image_hotels, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    class ViewHolder(itemView: View, private val listener: Listener) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: GalleryModel) {
            val sdvImageGalleryHotel = itemView.findViewById<SimpleDraweeView>(R.id.sdvImageGalleryHotel)

            sdvImageGalleryHotel.setOnClickListener {
                listener.onClick(item.url!!)
            }
            val uriPath = item.url
            sdvImageGalleryHotel.setImageURI(uriPath)
        }
    }

    interface Listener {
        fun onClick(url: String)
    }
}