package com.example.DB

import org.ktorm.database.Database

object DBconection {
    var databaseconecter = Database.connect (
        url = "jdbc:mysql://localhost:3306/classroom",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = ""
    )
}