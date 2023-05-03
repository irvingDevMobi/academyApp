package com.wizeline.myacademy.app.domain.users

enum class UserType {
    UNKNOWN,
    ADMIN,
    LECTURER,
    MENTEE;

    companion object {
        fun fromString(label: String?): UserType =
            when (label?.uppercase()) {
                ADMIN.name -> ADMIN
                LECTURER.name -> LECTURER
                MENTEE.name -> MENTEE
                else -> UNKNOWN
            }
    }
}
