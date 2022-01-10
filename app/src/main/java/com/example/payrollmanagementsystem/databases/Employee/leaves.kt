package com.example.payrollmanagementsystem.databases.Employee

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Employee::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("empLId"),
        onDelete = ForeignKey.CASCADE)))
data class leaves (
    @PrimaryKey(autoGenerate = true) val leaveId: Int,
    val empLId: Int,
    var leavesTaken: Int,
    @ColumnInfo(defaultValue = "15") val availableLeaves: Int
)
