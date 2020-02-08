package br.com.rvvaranda.hu.Helper

import com.google.gson.annotations.SerializedName

open class FilterBase {

    @SerializedName("count")
    val count: Int = 0
    @SerializedName("filter")
    val filter: String = ""
    @SerializedName("term")
    val term: String = ""
}