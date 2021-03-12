package com.dean.apihomemarketplace.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dean.apihomemarketplace.R
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_welcome.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        signin_pembeli.onClick {
            startActivity<SignInActivity>()
        }

        signin_penjual.onClick {
            startActivity<SignInActivity>()
        }


    }
}