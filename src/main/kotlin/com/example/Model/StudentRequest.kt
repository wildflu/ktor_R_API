package com.example.Model

import kotlinx.serialization.Serializable

@Serializable
data class StudentRequest(
    val firstname:String,
    val lastname:String,
    val ppf:String
)
