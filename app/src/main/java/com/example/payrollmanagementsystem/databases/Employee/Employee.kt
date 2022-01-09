package com.example.payrollmanagementsystem.databases.Employee

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Employee")
data class Employee (

    @PrimaryKey(autoGenerate = false) val id: Int,
    var name: String,
    var address: String,
    var phoneNo: Int,
    var achievements: String

)




