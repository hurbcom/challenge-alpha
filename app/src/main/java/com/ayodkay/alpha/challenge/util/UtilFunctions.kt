package com.ayodkay.alpha.challenge.util

import android.content.Context
import com.ayodkay.alpha.challenge.database.Hotels
import com.ayodkay.alpha.challenge.model.Address
import com.ayodkay.alpha.challenge.model.Amenities
import com.ayodkay.alpha.challenge.model.Descriptions
import com.ayodkay.alpha.challenge.model.HotelModel
import org.json.JSONObject

class UtilFunctions internal constructor(private val context: Context){

    fun handleJson(response: JSONObject): ArrayList<HotelModel> {

        val hotels: ArrayList<HotelModel> = arrayListOf()

        var name: String
        var amenities: ArrayList<ArrayList<Amenities>> = arrayListOf(  )
        val image: ArrayList<ArrayList<String>> = arrayListOf(  )
        var price:String? = null
        var descriptions: Descriptions
        var stars: String
        var huFreeCancellation: Boolean
        var address :Address? = null


        val result = response.getJSONArray("results")

        for (results_loop in 0 until result.length()) {
            val res = result.getJSONObject(results_loop)
            name = res.getString("name")

            val description = res.getString("description")
            val smallDescription = res.getString("smallDescription")
            descriptions = Descriptions(smallDescription,description)
            stars = res.getInt("stars").toString()
            huFreeCancellation = res.getBoolean("hu_free_cancellation")

            val am = res.getJSONArray("amenities")
            for (amenities_loop in 0 until am.length()) {
                val am_res = am.getJSONObject(amenities_loop)
                val name = am_res.getString("name")
                val category = am_res.getString("category")

                //amenities.add(Amenities(name, category))

            }

            val gallery = res.getJSONArray("gallery")
            val imageList = arrayListOf<String>()
            for (gallery_loop in 0 until gallery.length()) {

                val galleryLoopResult = gallery.getJSONObject(gallery_loop)
                val url  = galleryLoopResult.getString("url")

                imageList.add(url)


            }
            image.add(imageList)
            val pr = res.getJSONObject("price")
            for (price_loop in 0 until pr.length()) {
                price = pr.getString("amount")

            }
            val ad = res.getJSONObject("address")

            for (address_loop in 0 until ad.length()) {
                val zipcode = ad.getString("zipcode")
                val addresss = ad.getString("full_address")
                val city = ad.getString("city")
                val state = ad.getString("state")
                val country = ad.getString("country")


                address = Address(zipcode,addresss,city, state, country)

            }

            hotels.add(
                HotelModel(
                    name,price!!,image, null,descriptions,address!!,stars,huFreeCancellation)
            )
        }

        return hotels
    }

    fun handleStarJson(response: JSONObject,star:String): ArrayList<HotelModel>{
        val hotels: ArrayList<HotelModel> = arrayListOf()

        var name: String
        var amenities: ArrayList<ArrayList<Amenities>> = arrayListOf(  )
        val image: ArrayList<ArrayList<String>> = arrayListOf(  )
        var price:String? = null
        var descriptions: Descriptions
        var stars: String
        var huFreeCancellation: Boolean
        var address :Address? = null


        val result = response.getJSONArray("results")

        for (results_loop in 0 until result.length()) {
            val res = result.getJSONObject(results_loop)
            stars = res.getInt("stars").toString()

            if (star == stars){
                name = res.getString("name")
                val description = res.getString("description")
                val smallDescription = res.getString("smallDescription")
                descriptions = Descriptions(smallDescription,description)

                huFreeCancellation = res.getBoolean("hu_free_cancellation")

                val am = res.getJSONArray("amenities")
                for (amenities_loop in 0 until am.length()) {
                    val am_res = am.getJSONObject(amenities_loop)
                    val name = am_res.getString("name")
                    val category = am_res.getString("category")

                    //amenities.add(Amenities(name, category))

                }

                val gallery = res.getJSONArray("gallery")
                val imageList = arrayListOf<String>()
                for (gallery_loop in 0 until gallery.length()) {

                    val galleryLoopResult = gallery.getJSONObject(gallery_loop)
                    val url  = galleryLoopResult.getString("url")

                    imageList.add(url)


                }
                image.add(imageList)
                val pr = res.getJSONObject("price")
                for (price_loop in 0 until pr.length()) {
                    price = pr.getString("amount")

                }
                val ad = res.getJSONObject("address")

                for (address_loop in 0 until ad.length()) {
                    val zipcode = ad.getString("zipcode")
                    val addresss = ad.getString("full_address")
                    val city = ad.getString("city")
                    val state = ad.getString("state")
                    val country = ad.getString("country")


                    address = Address(zipcode,addresss,city, state, country)

                }

                hotels.add(
                    HotelModel(
                        name,price!!,image, null,descriptions,address!!,stars,huFreeCancellation)
                )
            }else{
                continue
            }
        }


        return hotels
    }

    fun handleJsonToData(response: JSONObject):ArrayList<Hotels>{

        val aaa :ArrayList<Hotels> = arrayListOf()

        var name: String
        val amenities: ArrayList<ArrayList<Amenities>> = arrayListOf(  )
        val image: ArrayList<ArrayList<String>> = arrayListOf(  )
        var price:String? = null
        var descriptions: Descriptions
        var huFreeCancellation: Boolean
        var address :Address? = null

        val result = response.getJSONArray("results")

        for (results_loop in 0 until result.length()) {
            val res = result.getJSONObject(results_loop)
            name = res.getString("name")

            val description = res.getString("description")
            val smallDescription = res.getString("smallDescription")
            descriptions = Descriptions(smallDescription,description)

            huFreeCancellation = res.getBoolean("hu_free_cancellation")

            val am = res.getJSONArray("amenities")
            val amenities_list : ArrayList<Amenities> = arrayListOf()
            for (amenities_loop in 0 until am.length()) {
                val am_res = am.getJSONObject(amenities_loop)
                val name = am_res.getString("name")
                val category = am_res.getString("category")

                amenities_list.add(Amenities(name,category))

            }
            amenities.add(amenities_list)

            val gallery = res.getJSONArray("gallery")
            val imageList = arrayListOf<String>()
            for (gallery_loop in 0 until gallery.length()) {

                val galleryLoopResult = gallery.getJSONObject(gallery_loop)
                val url  = galleryLoopResult.getString("url")

                imageList.add(url)


            }
            image.add(imageList)
            val pr = res.getJSONObject("price")
            for (price_loop in 0 until pr.length()) {
                price = pr.getString("amount")

            }
            val ad = res.getJSONObject("address")

            for (address_loop in 0 until ad.length()) {
                val zipcode = ad.getString("zipcode")
                val addresss = ad.getString("full_address")
                val city = ad.getString("city")
                val state = ad.getString("state")
                val country = ad.getString("country")


                address = Address(zipcode,addresss,city, state, country)

            }

            aaa.add(Hotels(name,price!!,huFreeCancellation,descriptions,address!!,image,amenities))
        }

        return aaa
    }

}