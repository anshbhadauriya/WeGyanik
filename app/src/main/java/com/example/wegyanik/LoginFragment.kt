package com.example.wegyanik

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.activity_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginPromptTextView = view.findViewById<TextView>(R.id.registerprompt)
        loginPromptTextView.setOnClickListener {
            val intent = Intent(requireContext(), SignupActivity::class.java)
            startActivity(intent)
        }
    }
}
