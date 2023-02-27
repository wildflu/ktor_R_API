package com.example.plugins

import com.example.DB.DBconection
import com.example.Entty.StudentEntity
import com.example.Model.Student
import com.example.Model.StudentRepond
import com.example.Model.StudentRequest
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import org.ktorm.dsl.*

fun Application.configureRouting() {
    routing {
        val db = DBconection.databaseconecter
        get("/") {
            call.respondText("Hello World!")
        }
        post("/login") {
            val request = call.receive<StudentRequest>()
            val result = db.insert(StudentEntity){
                set(it.firstname, request.firstname)
                set(it.lastname, request.lastname)
                set(it.ppf, request.ppf)
            }
            if(result == 1) {
                call.respond(HttpStatusCode.OK, StudentRepond(true, "success"))
            }else {
                call.respond(HttpStatusCode.BadRequest, StudentRepond(false, "Failed"))
            }
        }
        get("/students") {
            val allStudents = db.from(StudentEntity).select().map {
                val id = it[StudentEntity.id]
                val firstname = it[StudentEntity.firstname]
                val lastname = it[StudentEntity.lastname]
                val ppf = it[StudentEntity.ppf]
                Student(id?:-1, firstname?:"", lastname?:"",ppf?:"")
            }
            call.respond(allStudents)
        }
        delete("/student/{id}") {
            val id = call.parameters["id"]?.toInt()?:-1
            val roweffec = db.delete(StudentEntity){
                it.id eq id
            }
            if(roweffec ==1 ) {
                call.respond(HttpStatusCode.OK, StudentRepond(true , "Success"))
            }else {
                call.respond(HttpStatusCode.NotFound, StudentRepond(false, "Failed"))
            }
        }

        put("/student/{id}"){
            val id = call.parameters["id"]?.toInt()?:-1
            val updateStudent = call.receive<StudentRequest>()
            val roweFFec = db.update(StudentEntity){
                set(it.firstname, updateStudent.firstname)
                set(it.lastname, updateStudent.lastname)
                set(it.ppf, updateStudent.ppf)
                where { it.id eq id }
            }
            if(roweFFec == 1) {
                call.respond(HttpStatusCode.OK, StudentRepond(true , "Success"))
            }else {
                call.respond(HttpStatusCode.NotFound, StudentRepond(false , "Failed"))
            }
        }

        get("/student/{id}") {
            var id = call.parameters["id"]?.toInt() ?:-1
            val student = db.from(StudentEntity).select()
                .where(StudentEntity.id eq id)
                .map {
                    val id = it[StudentEntity.id]!!
                    val firstname = it[StudentEntity.firstname]!!
                    val lastname = it[StudentEntity.lastname]!!
                    val ppf = it[StudentEntity.ppf]!!
//                    Notes(id=id, from=from, to=to)
                    Student(id, firstname, lastname, ppf)

                }.firstOrNull()
            if(student == null) {
                call.respond(HttpStatusCode.NotFound, StudentRepond(false , "Failed"))
            }else {
                call.respond(HttpStatusCode.OK, StudentRepond(true, student))
            }
        }
    }
}
