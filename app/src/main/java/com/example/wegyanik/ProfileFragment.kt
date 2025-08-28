package com.example.wegyanik

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

        val community = view.findViewById<TextView>(R.id.community1)
        val domain = view.findViewById<TextView>(R.id.notifications)
        val offer = view.findViewById<TextView>(R.id.dom)
        val myOrders = view.findViewById<TextView>(R.id.myOrders)
        val needHelp = view.findViewById<TextView>(R.id.needHelp)
        val loginLogout = view.findViewById<TextView>(R.id.logout)

        community.setOnClickListener { openFragment(CommunityFragment()) }
        domain.setOnClickListener { openFragment(DomainFragment()) }
        offer.setOnClickListener { openFragment(WhatWeOfferFragment()) }
        myOrders.setOnClickListener { openFragment(MyOrdersFragment()) }
        needHelp.setOnClickListener { openFragment(NeedHelpFragment()) }

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
            textView.setTextColor(requireContext().getColor(R.color.colorError)) // Use your error color
        } else {
            textView.text = "Login"
            textView.setTextColor(requireContext().getColor(R.color.primary_accent)) // Use your accent color
        }
    }

    private fun performLogout(textView: TextView) {
        // Clear your user session, tokens, preferences here
        isUserLoggedIn = false
        updateLoginLogoutText(textView)
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
        // Optionally navigate away or refresh UI
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
