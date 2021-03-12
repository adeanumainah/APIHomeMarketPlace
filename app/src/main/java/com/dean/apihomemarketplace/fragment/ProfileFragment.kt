package com.dean.apihomemarketplace.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.SignInActivity
import com.dean.apihomemarketplace.model.Users
import com.dean.apihomemarketplace.utils.Constan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity

class ProfileFragment : Fragment() {

    var auth: FirebaseAuth? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(Constan.tb_user)
        val query = myRef.orderByChild("uid")
                .equalTo(auth?.uid)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (issue in snapshot?.children){
                    val data = issue?.getValue(Users::class.java)
                    showProfile(data)
                }
            }

        })
    }


    private fun showProfile(data: Users?) {
        tv_username_profile.text = data?.name
        tv_email_profile.text = data?.email
        tv_phone_profile.text = data?.phone

        btn_signout.onClick {
            auth?.signOut()
            startActivity<SignInActivity>()
        }
    }
}