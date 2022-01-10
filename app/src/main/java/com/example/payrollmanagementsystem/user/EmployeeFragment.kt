package com.example.payrollmanagementsystem.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.payrollmanagementsystem.R


class EmployeeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_employee,container,false)

        val id = arguments?.getInt("empId")
        val bundle = bundleOf("eId" to id)

        val generate: Button = view.findViewById(R.id.GenerateSalaryReport)
        val leave: Button = view.findViewById(R.id.TakeLeave)

        generate.setOnClickListener {
            this.findNavController().navigate(R.id.action_employeeFragment_to_salarySlipFragment,bundle)
        }

        leave.setOnClickListener {
            this.findNavController().navigate(R.id.action_employeeFragment_to_leaveFragment,bundle)
        }


        return view
    }

}