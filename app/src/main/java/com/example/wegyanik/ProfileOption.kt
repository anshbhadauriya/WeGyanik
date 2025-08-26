package com.example.wegyanik

data class ProfileOption(
    val title: String,
    val isToggle: Boolean = false,
    var toggleState: Boolean = false
)
