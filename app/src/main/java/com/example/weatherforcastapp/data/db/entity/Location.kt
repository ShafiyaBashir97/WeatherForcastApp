package com.example.weatherforcastapp.data.db.entity


import com.google.gson.annotations.SerializedName

data class Location(
    val name: String, // New York
    val country: String, // United States of America
    val region: String, // New York
    val lat: String, // 40.714
    val lon: String, // -74.006
    @SerializedName("timezone_id")
    val timezoneId: String, // America/New_York
    val localtime: String, // 2020-09-21 07:14
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Int, // 1600672440
    @SerializedName("utc_offset")
    val utcOffset: String // -4.0
)