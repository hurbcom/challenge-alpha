package com.example.core.base.presentation

import java.io.Serializable

abstract class BaseViewData: Serializable

enum class CategoryType {
    PEOPLE, PLANETS, STARSHIPS
}