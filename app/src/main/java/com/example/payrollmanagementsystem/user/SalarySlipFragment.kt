package com.example.payrollmanagementsystem.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.payrollmanagementsystem.R
import com.example.payrollmanagementsystem.databases.Employee.Employee
import com.example.payrollmanagementsystem.databases.Employee.leaves
import com.example.payrollmanagementsystem.databases.Employee.salary
import com.example.payrollmanagementsystem.databases.EmployeeRepository
import com.example.payrollmanagementsystem.databases.LoginDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


class SalarySlipFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_salary_slip, container, false)

        val id = arguments?.getInt("eId")

        val application = requireNotNull(this.activity).application
        GlobalScope.launch(Dispatchers.IO) {
            val dao = LoginDatabase.getInstance(application).employeeDAO()
            val repository = EmployeeRepository(dao)

            val employee: Employee = repository.search(id!!)
            val salary: salary = repository.searchSal(id)
            val leaves: leaves = repository.searchLeave(id)

            val name: TextView = view.findViewById(R.id.SSname)
            val phoneNo: TextView = view.findViewById(R.id.SSphoneNo)
            val address: TextView = view.findViewById(R.id.SSaddress)
            val achievements: TextView = view.findViewById(R.id.SSachievements)
            val basicPay: TextView = view.findViewById(R.id.SSbaiscPay)
            val hra: TextView = view.findViewById(R.id.SShra)
            val da: TextView = view.findViewById(R.id.SSda)
            val ta: TextView = view.findViewById(R.id.SSta)
            val pf: TextView = view.findViewById(R.id.SSpf)
            val otherAllowances: TextView = view.findViewById(R.id.SSotherAllowances)
            val takenLeave: TextView = view.findViewById(R.id.SSleaveTaken)
            val availableLeave: TextView = view.findViewById(R.id.SSavailableLeave)
            val netSalary: TextView = view.findViewById(R.id.SSnetSalary)

            name.text = "Name- " + employee.name
            phoneNo.text = "Phone No- " + employee.phoneNo.toString()
            address.text = "Address- " + employee.address
            achievements.text = "Achievements- " + employee.achievements
            basicPay.text = "Basic Pay- " + (salary.basicPay / 12).toString()
            hra.text = "HRA -" + (salary.hra / 12).toString()
            da.text = "DA- " + (salary.da / 12).toString()
            ta.text = "TA- " + (salary.ta / 12).toString()
            pf.text = "PF- " + salary.pf.toString()
            otherAllowances.text = "Other Allowances- " + salary.otherAllowances.toString()
            takenLeave.text = "Leaves Taken- " + leaves.leavesTaken.toString()
            availableLeave.text = "Available Leaves- " + leaves.availableLeaves.toString()

            var lf = 0;
            if (leaves.leavesTaken > leaves.availableLeaves) lf =
                leaves.leavesTaken - leaves.availableLeaves
            val tax = calculateTax(salary.basicPay)
            var netTax = 0
            netTax = if (tax >= salary.pf) (tax - salary.pf).roundToInt()
            else (salary.pf - tax).roundToInt()
            val amount =
                (salary.basicPay + salary.hra + salary.ta + salary.da + salary.otherAllowances) / 12 - netTax - ((salary.basicPay / 30) * lf)

            netSalary.text = "Net Salary- " + amount.toString()
        }


        return view
    }

    private fun calculateTax(pay: Double): Double {
        return if(pay in 500000.0..1000000.0) (pay-500000)*0.2
        else if(pay > 1000000) (pay-1000000)*0.3 + 100000
        else 0.0



    }

}