package com.example.test.mocks

import com.example.test.domain.models.Person


object UseCaseMocks {
    val people = listOf(
        Person(
            name = "Eduardo",
            image = "http://imageurl.com",
            height = "177",
            mass = "88",
            gender = "male",
            eyeColor = "black"
        )
    )
}