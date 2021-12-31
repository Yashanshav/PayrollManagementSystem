package com.example.payrollmanagementsystem.databases

import androidx.room.*
import com.example.payrollmanagementsystem.databases.Employee.*

@Dao
interface EmployeeDAO {

    //Add employee
    @Insert
    fun insertAll(employee: Employee, loginDetails: loginDetails, leaves: leaves, salary: salary)

   @Query("Select name, phoneNo, address, achievements, basicPay, pf, otherAllowances, password from Employee,loginDetails,leaves,salary where id = :Id")
   fun searchAll(Id: Int) : empAll


    @Query("Select  *  from Employee where id = :Id")
    fun search(Id: Int) : Employee

    @Delete
    fun delete(employee: Employee)

    @Update
    fun updateAll(employee: Employee,loginDetails: loginDetails,leaves: leaves,salary: salary)
}
