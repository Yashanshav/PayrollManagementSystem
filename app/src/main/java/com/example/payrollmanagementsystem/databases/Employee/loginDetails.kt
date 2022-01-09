package com.example.payrollmanagementsystem.databases.Employee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Employee::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("lid"),
        onDelete = ForeignKey.CASCADE)))
data class loginDetails (
    @PrimaryKey(autoGenerate = true) val loginId: Int,
    val lid: Int,
    var password: String
)
