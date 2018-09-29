package br.com.hu.allyson.desafiohu.domain

import android.os.Parcel
import android.os.Parcelable

data class Result(
    val meta: Meta,
    val results: List<Hotels>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Meta::class.java.classLoader),
        listOf<Hotels>().apply{
            parcel.readArrayList(Hotels::class.java.classLoader)
    })

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(meta, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}