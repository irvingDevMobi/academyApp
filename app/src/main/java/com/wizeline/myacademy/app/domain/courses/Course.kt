package com.wizeline.myacademy.app.domain.courses

data class Course(
    val id: String,
    val name: String,
    val description: String,
    val lecturer: String,
    val mentees: List<String> = listOf()
)
