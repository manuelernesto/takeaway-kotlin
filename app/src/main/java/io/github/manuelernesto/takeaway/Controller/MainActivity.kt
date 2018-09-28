package io.github.manuelernesto.takeaway.Controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.manuelernesto.takeaway.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start_order.setOnClickListener {
            val loginIntent = Intent(this, SignInActivity::class.java)
            startActivity(loginIntent)
        }

    }

}
