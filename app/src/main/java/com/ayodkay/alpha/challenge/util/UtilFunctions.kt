package com.ayodkay.alpha.challenge.util

import com.ayodkay.alpha.challenge.database.Hotels
import com.ayodkay.alpha.challenge.model.Address
import com.ayodkay.alpha.challenge.model.Amenities
import com.ayodkay.alpha.challenge.model.Descriptions
import com.ayodkay.alpha.challenge.model.HotelModel
import org.json.JSONObject

class UtilFunctions{

    //gets all the hotels and loop through the response
    fun handleJson(response: JSONObject): ArrayList<HotelModel>
    {

        val hotels: ArrayList<HotelModel> = arrayListOf()

        var name: String
        val image: ArrayList<ArrayList<String>> = arrayListOf(  )
        var price:String? = null
        var descriptions: Descriptions
        var stars: String
        var huFreeCancellation: Boolean
        var address :Address? = null


        val result = response.getJSONArray("results")

        for (results_loop in 0 until result.length())
        {
            val res = result.getJSONObject(results_loop)
            name = res.getString("name")

            val description = res.getString("description")
            val smallDescription = res.getString("smallDescription")
            descriptions = Descriptions(smallDescription,description)
            stars = res.getInt("stars").toString()
            huFreeCancellation = res.getBoolean("hu_free_cancellation")

            val gallery = res.getJSONArray("gallery")
            val imageList = arrayListOf<String>()
            for (gallery_loop in 0 until gallery.length())
            {

                val galleryLoopResult = gallery.getJSONObject(gallery_loop)
                val url  = galleryLoopResult.getString("url")

                imageList.add(url)


            }

            image.add(imageList)

            val pr = res.getJSONObject("price")
            for (price_loop in 0 until pr.length())
            {
                price = pr.getString("amount")

            }

            val ad = res.getJSONObject("address")

            for (address_loop in 0 until ad.length())
            {
                val zipcode = ad.getString("zipcode")
                val addresss = ad.getString("full_address")
                val city = ad.getString("city")
                val state = ad.getString("state")
                val country = ad.getString("country")

                val geoLocation = ad.getJSONObject("geoLocation")

                for (geoLocation_loop in 0 until geoLocation.length())
                {
                    val lon = geoLocation.getString("lon").toDouble()
                    val lat = geoLocation.getString("lat").toDouble()

                    address = Address(zipcode,addresss,city, state, country,lon,lat)
                }



            }

            hotels.add(
                HotelModel(
                    results_loop,name,price!!,image, null,descriptions,address!!,stars,
                    huFreeCancellation)
            )
        }

        return hotels
    }

    //gets all the hotels,loop through the response and get hotel stars needed
    fun handleStarJson(response: JSONObject,star:String): ArrayList<HotelModel>
    {
        val hotels: ArrayList<HotelModel> = arrayListOf()

        var name: String
        val image: ArrayList<ArrayList<String>> = arrayListOf(  )
        var price:String? = null
        var descriptions: Descriptions
        var stars: String
        var huFreeCancellation: Boolean
        var address :Address? = null

        val result = response.getJSONArray("results")

        for (results_loop in 0 until result.length())
        {
            val res = result.getJSONObject(results_loop)
            stars = res.getInt("stars").toString()

            if (star == stars)
            {
                name = res.getString("name")

                val description = res.getString("description")
                val smallDescription = res.getString("smallDescription")
                descriptions = Descriptions(smallDescription,description)

                huFreeCancellation = res.getBoolean("hu_free_cancellation")

                val gallery = res.getJSONArray("gallery")
                val imageList = arrayListOf<String>()
                for (gallery_loop in 0 until gallery.length())
                {

                    val galleryLoopResult = gallery.getJSONObject(gallery_loop)
                    val url  = galleryLoopResult.getString("url")

                    imageList.add(url)


                }

                image.add(imageList)

                val pr = res.getJSONObject("price")
                for (price_loop in 0 until pr.length())
                {
                    price = pr.getString("amount")

                }

                val ad = res.getJSONObject("address")

                for (address_loop in 0 until ad.length())
                {
                    val zipcode = ad.getString("zipcode")
                    val addresss = ad.getString("full_address")
                    val city = ad.getString("city")
                    val state = ad.getString("state")
                    val country = ad.getString("country")


                    val geoLocation = ad.getJSONObject("geoLocation")

                    for (geoLocation_loop in 0 until geoLocation.length())
                    {
                        val lon = geoLocation.getString("lon").toDouble()
                        val lat = geoLocation.getString("lat").toDouble()

                        address = Address(zipcode,addresss,city, state, country,lon,lat)
                    }

                }

                hotels.add(
                    HotelModel(
                        results_loop,name,price!!,image, null,descriptions,address!!,
                        stars,huFreeCancellation)
                )
            }
            else
            {
                continue
            }
        }


        return hotels
    }

    //gets all the hotels in the room database and loop through the response
    fun handleJsonToData(response: JSONObject):ArrayList<Hotels>
    {

        val hotelList:ArrayList<Hotels> = arrayListOf()

        var name: String
        val amenities: ArrayList<ArrayList<Amenities>> = arrayListOf(  )
        val image: ArrayList<ArrayList<String>> = arrayListOf(  )
        var price:String? = null
        var descriptions: Descriptions
        var huFreeCancellation: Boolean
        var address :Address? = null

        val result = response.getJSONArray("results")

        for (results_loop in 0 until result.length())
        {
            val res = result.getJSONObject(results_loop)
            name = res.getString("name")

            val description = res.getString("description")
            val smallDescription = res.getString("smallDescription")
            descriptions = Descriptions(smallDescription,description)

            huFreeCancellation = res.getBoolean("hu_free_cancellation")

            val am = res.getJSONArray("amenities")
            val amenitiesList : ArrayList<Amenities> = arrayListOf()
            for (amenities_loop in 0 until am.length())
            {
                val amRes = am.getJSONObject(amenities_loop)
                val name = amRes.getString("name")
                val category = amRes.getString("category")

                amenitiesList.add(Amenities(name,category))

            }
            amenities.add(amenitiesList)

            val gallery = res.getJSONArray("gallery")
            val imageList = arrayListOf<String>()
            for (gallery_loop in 0 until gallery.length())
            {

                val galleryLoopResult = gallery.getJSONObject(gallery_loop)
                val url  = galleryLoopResult.getString("url")

                imageList.add(url)


            }
            image.add(imageList)
            val pr = res.getJSONObject("price")
            for (price_loop in 0 until pr.length())
            {
                price = pr.getString("amount")

            }
            val ad = res.getJSONObject("address")

            for (address_loop in 0 until ad.length())
            {
                val zipcode = ad.getString("zipcode")
                val addresss = ad.getString("full_address")
                val city = ad.getString("city")
                val state = ad.getString("state")
                val country = ad.getString("country")


                val geoLocation = ad.getJSONObject("geoLocation")

                for (geoLocation_loop in 0 until geoLocation.length())
                {
                    val lon = geoLocation.getString("lon").toDouble()
                    val lat = geoLocation.getString("lat").toDouble()

                    address = Address(zipcode,addresss,city, state, country,lon,lat)
                }

            }

            hotelList.add(
                Hotels(results_loop,name,price!!,huFreeCancellation, descriptions,address!!,
                    image,amenities))
        }

        return hotelList
    }

}