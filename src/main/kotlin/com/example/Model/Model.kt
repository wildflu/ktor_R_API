package com.example.Model

import com.example.Entty.ModelEntity.primaryKey
import kotlinx.serialization.Serializable
import org.ktorm.schema.int
import org.ktorm.schema.varchar

@Serializable
data class Model(
    val id:Int,
    val name:String,
    val serial:String,
    val nbhoures:Int,
    val isrigionel:String,
)
