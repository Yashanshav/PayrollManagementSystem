package com.example.payrollmanagementsystem.databases.Employee

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Employee::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("empId"),
        onDelete = ForeignKey.CASCADE)))
data class salary (
        @PrimaryKey(autoGenerate = true) val salId: Int,
        val empId: Int,
        val basicPay: Double,
        val ta: Double,
        val da: Double,
        val hra: Double,
        val otherAllowances: Int,
        val pf: Int
)
