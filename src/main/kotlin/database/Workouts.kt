package com.example.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Workouts : Table("workouts") {

    val id = integer("id").autoIncrement()
    val name = varchar("name", 100)
    val description = varchar("description", 255)
    val durationMinutes = integer("duration_minutes")
    val date = datetime("date")

    override val primaryKey = PrimaryKey(id)
}