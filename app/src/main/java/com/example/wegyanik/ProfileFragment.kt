package com.example.wegyanik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private var isUserLoggedIn = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val community = view.findViewById<View>(R.id.community1)
        val domain = view.findViewById<View>(R.id.notifications)
        val offer = view.findViewById<View>(R.id.dom)
        val myOrders = view.findViewById<View>(R.id.myOrders)
        val needHelp = view.findViewById<View>(R.id.needHelp)
        val loginLogout = view.findViewById<TextView>(R.id.logout)

        needHelp.setOnClickListener {
            val url = "https://wegyanik.in/contact"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        community.setOnClickListener { openFragment(CommunityFragment()) }
        domain.setOnClickListener { openFragment(DomainFragment()) }
        offer.setOnClickListener { openFragment(WhatWeOfferFragment()) }
        myOrders.setOnClickListener { openFragment(OrdersFragment()) }

        // READ login state and user info from FRAGMENT ARGUMENTS
        val userName = arguments?.getString("USER_NAME") ?: "User"
        val email = arguments?.getString("USER_EMAIL") ?: ""
        isUserLoggedIn = arguments?.getBoolean("IS_LOGGED_IN") ?: false

        val nameTextView = view.findViewById<TextView>(R.id.profileName)
        val emailTextView = view.findViewById<TextView>(R.id.profileEmail)
        val loginLogoutTextView = loginLogout

        nameTextView.text = userName
        emailTextView.text = email

        updateLoginLogoutText(loginLogoutTextView)

        loginLogoutTextView.setOnClickListener {
            if (isUserLoggedIn) {
                performLogout()
            } else {
                // OPEN LoginActivity, and after login success,
                // refresh ProfileFragment with NEW arguments (see below)
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun updateLoginLogoutText(textView: TextView) {
        if (isUserLoggedIn) {
            textView.text = "Sign Out"
            textView.setTextColor(requireContext().getColor(R.color.colorError))
        } else {
            textView.text = "Login"
            textView.setTextColor(requireContext().getColor(R.color.primary_accent))
        }
    }

    private fun performLogout() {
        val profileFragment = ProfileFragment().apply {
            arguments = Bundle().apply {
                putBoolean("IS_LOGGED_IN", false)        // Set logged out state
                putString("USER_NAME", "User")           // Reset name to default
                putString("USER_EMAIL", "")               // Clear email
            }
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, profileFragment)
            .commit()

        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
