package br.com.flyingdutchman.challenge_alpha.ui.search.model

import android.os.Parcel
import android.os.Parcelable

data class RailsSearchResults(val name: String, val searchResults: List<SearchResult>) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(SearchResult)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeTypedList(searchResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RailsSearchResults> {
        override fun createFromParcel(parcel: Parcel): RailsSearchResults {
            return RailsSearchResults(parcel)
        }

        override fun newArray(size: Int): Array<RailsSearchResults?> {
            return arrayOfNulls(size)
        }
    }
}