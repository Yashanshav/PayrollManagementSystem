package com.example.payrollmanagementsystem.databases

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.payrollmanagementsystem.databases.Employee.Employee
import com.example.payrollmanagementsystem.databases.Employee.leaves
import com.example.payrollmanagementsystem.databases.Employee.loginDetails
import com.example.payrollmanagementsystem.databases.Employee.salary

class EmployeeRepository(private val employeeDAO: EmployeeDAO) {

    @Insert
    fun insertAll(employee: Employee, loginDetails: loginDetails, leaves: leaves, salary: salary) {
        employeeDAO.insertAll(employee,loginDetails,leaves,salary)
    }

    @Query("Select * from Employee where id = :Id")
    fun search(Id: Int) : Employee {
       return employeeDAO.search(Id)
    }

    @Query("Select * from loginDetails where lid = :Id")
    fun searchLogin(Id: Int) : loginDetails {
        return employeeDAO.searchLogin(Id)
    }
    @Query("Select * from salary where empId = :Id")
    fun searchSal(Id: Int) : salary {
        return employeeDAO.searchSal(Id)
    }
    @Query("Select * from leaves where empLId = :Id")
    fun searchLeave(Id: Int) : leaves {
        return employeeDAO.searchLeave(Id)
    }

    @Delete
    fun delete(Id: Int) {
        val employee: Employee = search(Id)
        employeeDAO.delete(employee)
    }


}
