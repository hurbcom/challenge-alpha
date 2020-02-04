package br.com.flyingdutchman.challenge_alpha.data.model

import android.os.Parcel
import android.os.Parcelable

data class ImageUrl(var url: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageUrl> {
        override fun createFromParcel(parcel: Parcel): ImageUrl {
            return ImageUrl(parcel)
        }

        override fun newArray(size: Int): Array<ImageUrl?> {
            return arrayOfNulls(size)
        }
    }

}
