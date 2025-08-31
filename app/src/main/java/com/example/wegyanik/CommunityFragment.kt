package com.example.wegyanik

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class CommunityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.community, container, false)

        val joinButton = view.findViewById<Button>(R.id.joinButton)
        val exploreButton = view.findViewById<Button>(R.id.exploreButton)

        val whatsappUrl = "https://www.whatsapp.com/channel/0029Vb5ueAB2ER6nYLUhUb1T"

        val openWhatsApp: (View) -> Unit = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))
            startActivity(intent)
        }

        joinButton.setOnClickListener(openWhatsApp)
        exploreButton.setOnClickListener(openWhatsApp)

        return view
    }
}
