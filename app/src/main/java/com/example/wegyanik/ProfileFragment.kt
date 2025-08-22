package com.example.wegyanik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val community = view.findViewById<TextView>(R.id.community1)
        val domain = view.findViewById<TextView>(R.id.notifications)
        val offer = view.findViewById<TextView>(R.id.dom)

        community.setOnClickListener {
            openFragment(CommunityFragment())
        }

        domain.setOnClickListener {
            openFragment(DomainFragment())
        }

        offer.setOnClickListener {
            openFragment(WhatWeOfferFragment())
        }

        return view
    }

    private fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)              // so back goes back to Profile
            .commit()
    }
}
