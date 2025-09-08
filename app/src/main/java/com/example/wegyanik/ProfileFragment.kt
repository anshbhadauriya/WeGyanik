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

    private var isUserLoggedIn = false  // Replace with real login state check

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val community = view.findViewById<View>(R.id.community1)
        val domain = view.findViewById<View>(R.id.notifications)
        val offer = view.findViewById<View>(R.id.dom)
//        val myOrders = view.findViewById<View>(R.id.myOrders)
        val needHelp = view.findViewById<View>(R.id.needHelp)
        val loginLogout = view.findViewById<TextView>(R.id.logout)

        needHelp.setOnClickListener {
            val url="https://wegyanik.in/contact"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        community.setOnClickListener { openFragment(CommunityFragment()) }
        domain.setOnClickListener { openFragment(DomainFragment()) }
        offer.setOnClickListener { openFragment(WhatWeOfferFragment()) }
//        myOrders.setOnClickListener { openFragment(MyOrdersFragment()) }
//        needHelp.setOnClickListener { openFragment(NeedHelpFragment()) }
        val userName = arguments?.getString("USER_NAME")

// Example: set to TextView
        val nameTextView = view.findViewById<TextView>(R.id.profileName)
        nameTextView.text = userName ?: "Guest"


        updateLoginLogoutText(loginLogout)
        loginLogout.setOnClickListener {
            if (isUserLoggedIn) {
                performLogout(loginLogout)
            } else {
                openFragment(LoginFragment())
            }
        }
    }

    private fun updateLoginLogoutText(textView: TextView) {
        if (isUserLoggedIn) {
            textView.text = "Logout"
            textView.setTextColor(requireContext().getColor(R.color.colorError))
        } else {
            textView.text = "Login"
            textView.setTextColor(requireContext().getColor(R.color.primary_accent))
        }
    }

    private fun performLogout(textView: TextView) {

        isUserLoggedIn = false
        updateLoginLogoutText(textView)
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
