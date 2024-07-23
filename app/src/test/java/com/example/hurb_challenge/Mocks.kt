package com.example.hurb_challenge

import com.example.hurb_challenge.app.data.dto.ApiResponse
import com.example.hurb_challenge.app.data.dto.CharacterDto
import com.example.hurb_challenge.app.data.dto.FilmDto
import com.example.hurb_challenge.app.data.dto.StarshipDto
import com.example.hurb_challenge.app.data.dto.VehicleDto
import com.example.hurb_challenge.app.domain.model.CardItem
import com.example.hurb_challenge.app.domain.model.Character
import com.example.hurb_challenge.app.domain.model.Starship
import com.example.hurb_challenge.app.domain.model.Vehicle

object Mocks {

    val apiResponseFilmDto = ApiResponse(
        count = 10,
        next = "http://mockurl.com",
        previous = null,
        results = listOf(
            FilmDto(
                title = "Fake Title",
                episode = 1,
                openingCrawl = "Fake Opening Crawl",
                director = "Fake Director",
                url = "http://fakeurl.com",
                releaseDate = "1988-05-22",
                charactersUrl = listOf(
                    "http://fakecharacterurl.com/1",
                    "http://fakecharacterurl.com/2",
                    "http://fakecharacterurl1.com/3"
                )
            )
        )
    )

    val listOfVehicleDto = listOf(
        VehicleDto(
            name = "Fake Vehicle Name 01",
            model = "Fake Vehicle Model 01",
            url = "http://fakevehicleurl.com/01.jpg"
        ),
        VehicleDto(
            name = "Fake Vehicle Name 02",
            model = "Fake Vehicle Model 02",
            url = "http://fakevehicleurl.com/02.jpg"
        ),
        VehicleDto(
            name = "Fake Vehicle Name 03",
            model = "Fake Vehicle Model 03",
            url = "http://fakevehicleurl.com/03.jpg"
        )
    )

    val listOfStarshipsDto = listOf(
        StarshipDto(
            name = "Fake Starship Name 01",
            model = "Fake Starship Model 01",
            url = "http://fakestarshipurl.com/01.jpg"
        ),
        StarshipDto(
            name = "Fake Starship Name 02",
            model = "Fake Starship Model 02",
            url = "http://fakestarshipurl.com/02.jpg"
        ),
        StarshipDto(
            name = "Fake Starship Name 03",
            model = "Fake Starship Model 03",
            url = "http://fakestarshipurl.com/03.jpg"
        )
    )

    val listOfCharactersDto = listOf(
        CharacterDto(
            name = "Fake Name 01",
            height = "200",
            gender = "male",
            url = "http://fakecharacterurl.com/01",
            birthYear = "DY695",
            vehiclesUrl = listOf(
                "http://fakevehicleurl.com/01",
                "http://fakevehicleurl.com/02",
                "http://fakevehicleurl.com/03"
            ),
            starshipsUrl = listOf(
                "http://fakestarshipurl.com/01",
                "http://fakestarshipurl.com/02",
                "http://fakestarshipurl.com/03"
             )
        ),
        CharacterDto(
            name = "Fake Name 02",
            height = "162",
            gender = "female",
            url = "http://fakecharacterurl.com/02",
            birthYear = "BN6943",
            vehiclesUrl = listOf(
                "http://fakevehicleurl.com/05",
                "http://fakevehicleurl.com/07",
                "http://fakevehicleurl.com/10"
            ),
            starshipsUrl = listOf(
                "http://fakestarshipurl.com/11",
                "http://fakestarshipurl.com/12",
                "http://fakestarshipurl.com/30"
            )
        ),
        CharacterDto(
            name = "Fake Name 03",
            height = "185",
            gender = "n/a",
            url = "http://fakecharacterurl.com/03",
            birthYear = "DY1236",
            vehiclesUrl = listOf(
                "http://fakevehicleurl.com/13",
                "http://fakevehicleurl.com/09",
                "http://fakevehicleurl.com/10"
            ),
            starshipsUrl = listOf(
                "http://fakestarshipurl.com/08",
                "http://fakestarshipurl.com/05",
                "http://fakestarshipurl.com/16"
            )
        )
    )

    val listOfVehicle = listOf(
        Vehicle(
            id = "01",
            name = "Fake Vehicle Name 01",
            model = "Fake Vehicle Model 01"
        ),
        Vehicle(
            id = "02",
            name = "Fake Vehicle Name 02",
            model = "Fake Vehicle Model 02"
        )
    )

    val listOfStarship = listOf(
        Starship(
            id = "01",
            name = "Fake Starship Name 01",
            model = "Fake Starship Model 01"
        ),
        Starship(
            id = "02",
            name = "Fake Starship Name 02",
            model = "Fake Starship Model 02"
        )
    )

    val listOfCharacter = listOf(
       Character(
           id = "01",
           name = "Fake Starship Name 01",
           height = "162",
           gender = "female",
           birthYear = "BN6943",
           vehiclesUrl = listOf(
               "http://fakevehicleurl.com/05",
               "http://fakevehicleurl.com/07",
               "http://fakevehicleurl.com/10"
           ),
           starshipsUrl = listOf(
               "http://fakestarshipurl.com/11",
               "http://fakestarshipurl.com/12",
               "http://fakestarshipurl.com/30"
           )
        ),
        Character(
            id = "02",
            name = "Fake Starship Name 02",
            height = "185",
            gender = "n/a",
            birthYear = "DY1236",
            vehiclesUrl = listOf(
                "http://fakevehicleurl.com/13",
                "http://fakevehicleurl.com/09",
                "http://fakevehicleurl.com/10"
            ),
            starshipsUrl = listOf(
                "http://fakestarshipurl.com/08",
                "http://fakestarshipurl.com/05",
                "http://fakestarshipurl.com/16"
            )
        )
    )
}