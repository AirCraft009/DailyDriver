package com.mxsxll.dailydriver.data

import androidx.room3.PrimaryKey

data class Settings (
    @PrimaryKey val key: String,
    val value: String
)