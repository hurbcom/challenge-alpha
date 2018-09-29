package br.com.hu.allyson.desafiohu.domain

import android.os.Parcel
import android.os.Parcelable

data class Meta(
    val count: Int,
    val offset: Int,
    val query: String,
    val warning: String,
    val countWithAvailabilityInPage: Int,
    val responseTime: ResponseTime,
    val countHotel: Int,
    val countPackage: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(ResponseTime::class.java.classLoader),
        parcel.readInt(),
        parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeInt(offset)
        parcel.writeString(query)
        parcel.writeString(warning)
        parcel.writeInt(countWithAvailabilityInPage)
        parcel.writeParcelable(responseTime, flags)
        parcel.writeInt(countHotel)
        parcel.writeInt(countPackage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Meta> {
        override fun createFromParcel(parcel: Parcel): Meta {
            return Meta(parcel)
        }

        override fun newArray(size: Int): Array<Meta?> {
            return arrayOfNulls(size)
        }
    }
}