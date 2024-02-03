package com.example.jetpackcomposecatalog.model

import androidx.annotation.DrawableRes
import java.util.concurrent.SubmissionPublisher

data class SuperHero(
    var superheroName: String,
    var realName: String,
    var publisher: String,
    @DrawableRes var photo: Int
)
