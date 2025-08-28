package com.example.wegyanik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.nio.file.Files.find

class CommunityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val button5 = view.findViewById<Button>(R.id.button5)
//        button5.setOnClickListener {
//
//        }
        // Inflate the community page layout
        return inflater.inflate(R.layout.community, container, false)
    }
}
