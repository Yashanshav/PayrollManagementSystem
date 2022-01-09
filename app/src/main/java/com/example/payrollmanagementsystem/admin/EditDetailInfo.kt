package com.example.payrollmanagementsystem.admin

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
import com.example.payrollmanagementsystem.databases.EmployeeRepository
import com.example.payrollmanagementsystem.databases.LoginDatabase

class EditDetailInfo : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_edit_detail_info, container, false)

        val id = arguments?.getInt("id")

        var name: EditText = view.findViewById(R.id.Editname)
        var phoneNo: EditText = view.findViewById(R.id.EditphoneNo)
        var address: EditText = view.findViewById(R.id.Editaddress)
        var achievements: EditText = view.findViewById(R.id.Editachievements)
        var basicPay: EditText = view.findViewById(R.id.EditbaiscPay)
        var pf: EditText = view.findViewById(R.id.Editpf)
        var otherAllowances: EditText = view.findViewById(R.id.EditotherAllowances)
        var addPassword: EditText = view.findViewById(R.id.EditaddPassword)
        val editBatten: Button = view.findViewById(R.id.EditaddEmployee)

        val application = requireNotNull(this.activity).application
        val dao = LoginDatabase.getInstance(application).employeeDAO()
        val repository = EmployeeRepository(dao)

        var employee = repository.search(id!!)
        var salary = repository.searchSal(id)
        var loginDetails = repository.searchLogin(id)
        var leaves = repository.searchLeave(id)

        name.setText(employee.name)
        phoneNo.setText(employee.phoneNo.toString())
        address.setText(employee.address)
        achievements.setText(employee.achievements)
        basicPay.setText(salary.basicPay.toString())
        pf.setText(salary.pf.toString())
        otherAllowances.setText(salary.otherAllowances.toString())
        addPassword.setText(loginDetails.password)

        editBatten.setOnClickListener {
            employee.name = name.text.toString()
            employee.phoneNo = phoneNo.text.toString().trim().toInt()
            employee.address = address.text.toString()
            employee.achievements = achievements.text.toString()
            salary.basicPay = basicPay.text.toString().trim().toDouble()
            salary.pf = pf.text.toString().trim().toInt()
            salary.otherAllowances = otherAllowances.text.toString().trim().toInt()
            loginDetails.password = addPassword.text.toString()

            repository.updateAll(employee,loginDetails,salary,leaves)
            Toast.makeText(context, "record updated successfully for $id", Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(R.id.action_editDetailInfo_to_adminFragment)
        }



        return view
    }


}



