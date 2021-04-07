package com.dean.apihomemarketplace.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.utils.Constan
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_authentication_hp.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AuthenticationHpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication_hp)

        val key = intent.getStringExtra(Constan.key)
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(Constan.tb_user)

        tv_submit_number.onClick {

            val intent = Intent(this@AuthenticationHpActivity, SignInActivity::class.java)
            startActivity(intent)
//            if (et_auth_hp.text.toString().isNotEmpty()){
//                myRef.child(key!!).child("phone")
//                        .setValue(et_auth_hp.text.toString())
//                startActivity<MainActivity>()
            }

        }


}



//val key = intent.getStringExtra(Constan.key)
//val database = FirebaseDatabase.getInstance()
//val myRef = database.getReference(Constan.tb_user)
//
////update realtime database
//tv_submit_number.onClick {
//    if (et_auth_hp.text.toString().isNotEmpty()){
//        myRef.child(key!!).child("phone")
//                .setValue(et_auth_hp.text.toString())
//        startActivity<MainActivity>()
//    }
//
//    else toast("tidak boleh kosong")
//}