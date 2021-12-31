package com.example.payrollmanagementsystem.ui.login

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

class LoginFragment : Fragment() {


     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_login, container, false)


        val login: Button = view.findViewById(R.id.login)
        val usertype: EditText = view.findViewById(R.id.usertype)
        val password: EditText = view.findViewById(R.id.password)
        val username: EditText = view.findViewById(R.id.username)

        login.setOnClickListener {
            if(usertype.text.toString() == "admin" && checkUsernameAndPasswordForAdmin(username,password)) {

                view.findNavController().navigate(R.id.action_loginFragment_to_adminFragment)


            }
            else if(usertype.text.toString() == "employee"){
                checkUsernameAndPasswordForEmployee(username,password)
            }
            else {
                Toast.makeText(context, "Invalid usertype", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun checkUsernameAndPasswordForAdmin(username: EditText, password: EditText) : Boolean{
        if(username.text.toString() == "1000" && password.text.toString() == "admin123") {
            // admin activity
            Toast.makeText(context, "Welcome admin", Toast.LENGTH_SHORT).show()
            return true

        }
        else {
            Toast.makeText(context, "username or password don't match for admin", Toast.LENGTH_SHORT).show()
            return false
        }

    }

    private fun checkUsernameAndPasswordForEmployee(username: EditText, password: EditText) {
        if(username.text.toString() == "1000" && password.text.toString() == "admin123") {
            // admin activity
            Toast.makeText(context, "Welcome admin", Toast.LENGTH_SHORT).show()


        }
        else {
            Toast.makeText(context, "username or password don't match for admin", Toast.LENGTH_SHORT).show()
        }

    }

}