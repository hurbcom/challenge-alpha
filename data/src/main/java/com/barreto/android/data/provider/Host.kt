package com.barreto.android.data.provider

enum class Host(val baseUrl: String) {
    RELEASE("https://www.hurb.com/search/api/"),
    DEBUG("https://www.hurb.com/search/api/");

    companion object {
        fun fromBuildType(flavor: String, buildType: String): Host {
            return valueOf("${flavor}_$buildType".toUpperCase())
        }
        fun fromBuildType(buildType: String): Host {
            return valueOf(buildType.toUpperCase())
        }
    }
}
