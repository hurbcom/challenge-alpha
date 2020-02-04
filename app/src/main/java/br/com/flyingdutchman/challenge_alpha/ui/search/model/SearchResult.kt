package br.com.flyingdutchman.challenge_alpha.ui.search.model

import android.os.Parcel
import android.os.Parcelable
import br.com.flyingdutchman.challenge_alpha.data.model.ImageUrl

data class SearchResult(
    val id: String,
    var name: String,
    var url: String,
    var description: String,
    var shortDescription: String,
    var gallery: List<ImageUrl>,
    var freeCancelation: Boolean,
    var currentPrice: String,
    var oldPrice: String,
    var rating: Int,
    var city: String,
    var amenities: String,
    var isHotel: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(ImageUrl),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(description)
        parcel.writeString(shortDescription)
        parcel.writeTypedList(gallery)
        parcel.writeByte(if (freeCancelation) 1 else 0)
        parcel.writeString(currentPrice)
        parcel.writeString(oldPrice)
        parcel.writeInt(rating)
        parcel.writeString(city)
        parcel.writeString(amenities)
        parcel.writeByte(if (isHotel) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchResult> {
        override fun createFromParcel(parcel: Parcel): SearchResult {
            return SearchResult(parcel)
        }

        override fun newArray(size: Int): Array<SearchResult?> {
            return arrayOfNulls(size)
        }
    }
}