package com.example.realnews

import java.util.*
import kotlin.collections.ArrayList

data class Newsmodel(
    var totalResults: Int,
    var status: String,
    var articles: ArrayList<model>,
)