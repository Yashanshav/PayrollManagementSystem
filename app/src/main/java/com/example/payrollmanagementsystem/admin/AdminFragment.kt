package com.example.payrollmanagementsystem.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.payrollmanagementsystem.R

class AdminFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, containerAdminFragment: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_admin,containerAdminFragment, false)

        val editDetails: Button = view.findViewById(R.id.editDetails)
        val addEmployee: Button = view.findViewById(R.id.AddEmp)
        val deleteEmployee: Button = view.findViewById(R.id.deleteEmp)

        editDetails.setOnClickListener {
            view.findNavController().navigate(R.id.action_adminFragment_to_editDetailFragment)
        }

        addEmployee.setOnClickListener {
            view.findNavController().navigate(R.id.action_adminFragment_to_addEmployeeFragment)
        }

        deleteEmployee.setOnClickListener {
            view.findNavController().navigate(R.id.action_adminFragment_to_deleteFragment)
        }




        return view
    }
}