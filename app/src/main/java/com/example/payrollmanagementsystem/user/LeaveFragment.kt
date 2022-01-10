package com.example.payrollmanagementsystem.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.payrollmanagementsystem.R
import com.example.payrollmanagementsystem.databases.Employee.leaves
import com.example.payrollmanagementsystem.databases.EmployeeRepository
import com.example.payrollmanagementsystem.databases.LoginDatabase


class LeaveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_leave, container, false)

        val id = arguments?.getInt("eId")
        val leaves: EditText = view.findViewById(R.id.LeaveId)
        val addLeave: Button = view.findViewById(R.id.leave)

        if(leaves.toString() == null) {
            Toast.makeText(context, "Please enter an integer",Toast.LENGTH_SHORT).show()
        }
        else {
            addLeave.setOnClickListener {
                val application = requireNotNull(this.activity).application
                val dao = LoginDatabase.getInstance(application).employeeDAO()
                val repository = EmployeeRepository(dao)

                val lf: leaves = repository.searchLeave(id!!)
                lf.leavesTaken += leaves.text.toString().trim().toInt()
                repository.updateLeave(lf)
                Toast.makeText(context, "Added Leaves", Toast.LENGTH_SHORT).show()
                this.findNavController().navigate(R.id.action_leaveFragment_to_employeeFragment)
            }
        }


        return view
    }

}