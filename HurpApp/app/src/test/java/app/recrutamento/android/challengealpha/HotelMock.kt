package app.recrutamento.android.challengealpha

import app.recrutamento.android.challengealpha.model.hotel.Hotel

object HotelMock {
    val hotelList = mutableListOf(
        Hotel(name = "Hotel 1",stars = 3),
        Hotel(name = "Hotel 2",stars = 1),
        Hotel(name = "Hotel 3",stars = 4),
        Hotel(name = "Hotel 4",stars = 2),
        Hotel(name = "Hotel 5",stars = 3),
        Hotel(name = "Hotel 6",stars = 5),
        Hotel(name = "Hotel 7",stars = 5),
        Hotel(name = "Hotel 8",stars = 3)
    )

    val HotelListFilteredThreeStars = mutableListOf(
        Hotel(name = "Hotel 1",stars = 3),
        Hotel(name = "Hotel 5",stars = 3),
        Hotel(name = "Hotel 8",stars = 3)
    )
}