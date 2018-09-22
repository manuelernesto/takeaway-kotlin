package io.github.manuelernesto.takeaway

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import io.github.manuelernesto.takeaway.Model.User
import io.github.manuelernesto.takeaway.Utils.Common
import kotlinx.android.synthetic.main.activity_login.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val userTable: DatabaseReference = database.getReference("User")
        progressBar.visibility = View.INVISIBLE

        btn_signIn.setOnClickListener {

            setLayoutVisibility(View.VISIBLE, View.INVISIBLE)

            val valueEventListener = object : ValueEventListener {
                override fun onCancelled(databaseError: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    //Get User Information

                    setLayoutVisibility(View.INVISIBLE, View.VISIBLE)

                    if (dataSnapshot.child(et_phone_number.text.toString()).exists()) {


                        val user: User? = dataSnapshot.child(et_phone_number.text.toString()).getValue(User::class.java)

                        if (user != null) {

                            if (user.password == et_password.text.toString()) {
                                "SignIn Successful.".toast(this@SignInActivity)
                                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                                Common.currentUser = user
                                startActivity(intent)
                                finish()

                            } else {
                                "Wrong Password.".toast(this@SignInActivity)
                            }
                        }
                    } else {
                        "User not exists.".toast(this@SignInActivity)
                    }


                }

            }

            userTable.addValueEventListener(valueEventListener)
        }
    }


    fun setLayoutVisibility(progressBarVisibility: Int, otherVisibility: Int) {
        progressBar.visibility = progressBarVisibility
        et_password.visibility = otherVisibility
        et_phone_number.visibility = otherVisibility
        btn_signIn.visibility = otherVisibility
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    fun intentSignUp(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}
