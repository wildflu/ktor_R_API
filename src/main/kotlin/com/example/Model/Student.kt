package com.example.Model

import kotlinx.serialization.Serializable

@Serializable
data class Student(val id:Int, val firstname:String, val lastname:String, val ppf:String)
