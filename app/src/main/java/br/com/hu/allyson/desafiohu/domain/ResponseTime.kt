package br.com.hu.allyson.desafiohu.domain

import android.os.Parcel
import android.os.Parcelable

data class ResponseTime(
    val searchEngine: Int,
    val total: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(searchEngine)
        parcel.writeInt(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResponseTime> {
        override fun createFromParcel(parcel: Parcel): ResponseTime {
            return ResponseTime(parcel)
        }

        override fun newArray(size: Int): Array<ResponseTime?> {
            return arrayOfNulls(size)
        }
    }
}