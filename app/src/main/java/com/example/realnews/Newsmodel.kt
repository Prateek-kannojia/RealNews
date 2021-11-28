package com.example.realnews

import java.util.*

data class Newsmodel(
    var totalResults: Int,
    var status: String,
    var articles: ArrayList<model>
)