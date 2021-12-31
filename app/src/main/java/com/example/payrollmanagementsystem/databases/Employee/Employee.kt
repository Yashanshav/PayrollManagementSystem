package com.example.payrollmanagementsystem.databases.Employee

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Employee")
data class Employee (

        @PrimaryKey(autoGenerate = false) val id: Int,
        val name: String?,
        val address: String?,
        val phoneNo: Int?,
        val achievements: String

)

data class empAll (
        val name: String?,
        val address: String?,
        val phoneNo: Int?,
        val achievements: String,
        val basicPay: Double,
        val pf: Int,
        val otherAllowances: Int,
        val password: String
        )



