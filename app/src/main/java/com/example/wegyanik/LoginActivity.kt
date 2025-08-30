package com.example.wegyanik

import LoginRequest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        val registerPrompt = findViewById<TextView>(R.id.registerprompt)

        loginButton.setOnClickListener {
            android.util.Log.d("LOGIN", "Login button clicked")
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            Toast.makeText(this@LoginActivity, "Login button clicked!", Toast.LENGTH_SHORT).show()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this@LoginActivity, "Enter email & password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val response = RetrofitInstance.authApi.login(email, password)

                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            if (loginResponse.message.contains("successfully", ignoreCase = true)) {
                                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@LoginActivity, HomeScreen::class.java)
                                intent.putExtra("USER_NAME", loginResponse.name)
                                intent.putExtra("USER_ID", loginResponse.id)
                                intent.putExtra("USER_EMAIL", loginResponse.email)
                                intent.putExtra("USER_ROLE", loginResponse.role)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, loginResponse.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

        }

        // "Register" prompt
        registerPrompt.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
