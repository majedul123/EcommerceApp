package com.itmedicious.spliff.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.itmedicious.spliff.api.UtilsApi
import com.itmedicious.spliff.databinding.ActivityLoginBinding
import com.itmedicious.spliff.model.LogIn
import com.itmedicious.spliff.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    val PREFS_NAME = "LoginPrefs"

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val settings = getSharedPreferences(PREFS_NAME, 0)
        if (settings.getString("logged", "").toString() == "logged") {
            val i = Intent(this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
            finish()
        }

        binding?.createAccountButton?.setOnClickListener {
            checkLoginStatus()
        }

        binding?.registrationText?.setOnClickListener {
            val i = Intent(this, RegistrationActivity::class.java)
            startActivity(i)
        }

    }

    private fun checkLoginStatus() {

        var emailAddress: String = binding?.emailAddressLabel?.text.toString()
        var password: String = binding?.passwordLabel?.text.toString()

        if (emailAddress.isEmpty()) {
            binding?.emailAddressLabel?.setError("need to provide email")
        }
        if (password.isEmpty()) {
            binding?.passwordLabel?.setError("need to provide password")
        }


        UtilsApi.apiService.logIn(LogIn(email = emailAddress, password = password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {

                        val accessToken: String? = response.body()?.success?.token
                        val settings = getSharedPreferences(PREFS_NAME, 0)
                        val editor = settings.edit()
                        editor.putString("logged", "logged")
                        editor.putString("token", accessToken)
                        editor.apply()
                      //  Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Please provide correct email and password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                    Log.d("debug", "onFailure: ERROR > $t")
                    Toast.makeText(
                        this@LoginActivity,
                        "error "+t.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}