package com.example.payrollmanagementsystem.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.payrollmanagementsystem.R
import com.example.payrollmanagementsystem.databases.EmployeeRepository
import com.example.payrollmanagementsystem.databases.LoginDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class EditDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_edit_detail, container, false)

        val id: EditText = view.findViewById(R.id.editempId)
        val search: Button = view.findViewById(R.id.searchEditEmpDetails)

        search.setOnClickListener {
            val application = requireNotNull(this.activity).application

            GlobalScope.launch(Dispatchers.IO) {
                val dao = LoginDatabase.getInstance(application).employeeDAO()
                val repository = EmployeeRepository(dao)


                // used this but haven't tested it yet if it works or not
                if (repository.search(id.text.toString().toInt()) == null) {
                    Toast.makeText(context, "record not found", Toast.LENGTH_SHORT).show()
                } else {
                    val bundle = bundleOf("id" to id.text.toString().trim().toInt())
                    view.findNavController()
                        .navigate(R.id.action_editDetailFragment_to_editDetailInfo, bundle)
                }
            }
        }

        return view
    }


}