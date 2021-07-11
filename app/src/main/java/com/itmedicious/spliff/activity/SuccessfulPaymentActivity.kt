package com.itmedicious.spliff.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.itmedicious.spliff.R
import com.itmedicious.spliff.databinding.ActivityYourBagBinding

class SuccessfulPaymentActivity : AppCompatActivity() {
    private lateinit var binding: SuccessfulPaymentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_successful_payment)

        findViewById<MaterialButton>(R.id.continue_shopping_button).setOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}