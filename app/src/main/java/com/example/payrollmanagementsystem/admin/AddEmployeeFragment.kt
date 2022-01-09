package com.example.payrollmanagementsystem.admin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.payrollmanagementsystem.R
import com.example.payrollmanagementsystem.databases.Employee.Employee
import com.example.payrollmanagementsystem.databases.Employee.leaves
import com.example.payrollmanagementsystem.databases.Employee.loginDetails
import com.example.payrollmanagementsystem.databases.Employee.salary
import com.example.payrollmanagementsystem.databases.EmployeeRepository
import com.example.payrollmanagementsystem.databases.LoginDatabase


class AddEmployeeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var id = loadId()


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_employee, container, false)

        val name: EditText = view.findViewById(R.id.name)
        val phoneNo: EditText = view.findViewById(R.id.phoneNo)
        val address: EditText = view.findViewById(R.id.address)
        val achievements: EditText = view.findViewById(R.id.achievements)
        val basicPay: EditText = view.findViewById(R.id.baiscPay)
        val pf: EditText = view.findViewById(R.id.pf)
        val otherAllowances: EditText = view.findViewById(R.id.otherAllowances)
        val addPassword: EditText = view.findViewById(R.id.addPassword)
        val add: Button = view.findViewById(R.id.addEmployee)



        add.setOnClickListener {

            val application = requireNotNull(this.activity).application
            val dao = LoginDatabase.getInstance(application).employeeDAO()
            val repository = EmployeeRepository(dao)

            val leaves = leaves(0, id!!, 0, 15)
            val employee = Employee(
                id,
                name.text.toString(),
                address.text.toString(),
                phoneNo.text.toString().trim().toInt(),
                achievements.text.toString()
            )
            val login = loginDetails(0, id, addPassword.text.toString())
            val salary = salary(
                0,
                id,
                basicPay.text.toString().trim().toDouble(),
                basicPay.text.toString().trim().toDouble() * 0.05,
                basicPay.text.toString().trim().toDouble() * 0.1,
                basicPay.text.toString().trim().toDouble() * 0.2,
                otherAllowances.text.toString().trim().toInt(),
                pf.text.toString().trim().toInt()
            )

            repository.insertAll(employee, login, leaves, salary)

            Toast.makeText(context, "Your id number is $id", Toast.LENGTH_SHORT).show()
            saveId(++id)
            view.findNavController().navigate(R.id.action_addEmployeeFragment_to_adminFragment)
        }


        return view
    }

    private fun saveId(id: Int) {
        val sharedPreferences = this.activity?.getSharedPreferences("id", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putInt("id", id)
        }?.apply()

    }

    private fun loadId(): Int? {
        val sharedPreferences = this.activity?.getSharedPreferences("id", Context.MODE_PRIVATE)
        return sharedPreferences?.getInt("id", 1001)
    }
}