package com.example.payrollmanagementsystem.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.payrollmanagementsystem.databases.Employee.Employee
import com.example.payrollmanagementsystem.databases.Employee.leaves
import com.example.payrollmanagementsystem.databases.Employee.loginDetails
import com.example.payrollmanagementsystem.databases.Employee.salary


@Database(entities = [Employee::class, loginDetails::class, salary::class, leaves::class], version = 3, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {
    abstract fun employeeDAO() : EmployeeDAO

    companion object {

        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getInstance(context: Context): LoginDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDatabase::class.java,
                        "LoginDatabase"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}