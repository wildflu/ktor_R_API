package com.example.Entty

import com.example.Entty.StudentEntity.primaryKey
import org.ktorm.schema.BooleanSqlType
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object ModelEntity: Table<Nothing>("models") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val serial = varchar("serial")
    val nbhoures = int("nbhoures")
    val isrigionel = varchar("isrigionel")
}
