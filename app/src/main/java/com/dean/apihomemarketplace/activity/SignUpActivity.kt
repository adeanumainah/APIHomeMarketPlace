package com.dean.apihomemarketplace.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.model.Users
import com.dean.apihomemarketplace.utils.Constan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SignUpActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        btn_register_sign_up.onClick {
            if (name_sign_up.text!!.isNotEmpty() &&
                    email_sign_up.text!!.isNotEmpty() &&
                    pass_sign_up.text!!.isNotEmpty() &&
                    confirm_sign_up.text!!.isNotEmpty() &&
                    phone_sign_up.text!!.isNotEmpty()

            ){
                authUserSignUp(
                        email_sign_up.text.toString(),
                        pass_sign_up.text.toString()
                )

            }

        }
    }

    //proses authentication
    private fun authUserSignUp(email: String, pass: String): Boolean? {
        auth = FirebaseAuth.getInstance()

        var status: Boolean? = null
        val TAG = "tag"

        auth?.createUserWithEmailAndPassword(email, pass)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        if (insertUser(
                                        name_sign_up.text.toString(),
                                        email_sign_up.text.toString(),
                                        phone_sign_up.text.toString(),
                                        task.result?.user!!
                                )){
                            startActivity<SignInActivity>()
                        }
                    } else {
                        Toast.makeText(this, getString(R.string.text_error_message) + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        status = false
                    }
                }
        return status

    }

    //proses menambahkan data user ke realtime database
    private fun insertUser(
            name: String,
            email: String,
            phone: String,
            users: FirebaseUser
    ): Boolean {
        var user = Users()
        user.uid = users.uid
        user.name = name
        user.email = email
        user.phone = phone

        val database = FirebaseDatabase.getInstance()

        //id yg masuk ke database
        var key = database.reference.push().key

        //nama table
        val myRef = database.getReference(Constan.tb_user)

        //menyimpan ke database
        myRef.child(key!!).setValue(user)

        return true
    }
}