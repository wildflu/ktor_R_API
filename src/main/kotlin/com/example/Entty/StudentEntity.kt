package com.example.Entty

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object StudentEntity: Table<Nothing>("student") {
    val id = int("id").primaryKey()
    val firstname = varchar("firstname")
    val lastname = varchar("lastname")
    val ppf = varchar("ppf")
}