package com.example.payrollmanagementsystem.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.payrollmanagementsystem.R
import com.example.payrollmanagementsystem.databases.EmployeeRepository
import com.example.payrollmanagementsystem.databases.LoginDatabase

class DeleteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_delete, container, false)

       val id: EditText = view.findViewById(R.id.DempId)
        val del: Button = view.findViewById(R.id.deleteEmp)

        del.setOnClickListener {
            val application = requireNotNull(this.activity).application
            val dao = LoginDatabase.getInstance(application).employeeDAO()
            val repository = EmployeeRepository(dao)

            repository.delete(id.text.toString().toInt())
              val toast = Toast.makeText(context, "Employee deleted successfully with id ${id.text.toString()} ", Toast.LENGTH_SHORT).show()
        }

        return view
    }


}