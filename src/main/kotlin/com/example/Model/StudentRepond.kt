package com.example.Model

import kotlinx.serialization.Serializable
@Serializable

data class StudentRepond<T>(
    val stat:Boolean,
    val data:T
)