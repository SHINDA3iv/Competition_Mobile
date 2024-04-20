package com.example.parkingapp.domain.entity

data class UserItem(
    val userId: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val department: String
)
