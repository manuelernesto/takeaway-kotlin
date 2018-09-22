package io.github.manuelernesto.takeaway

import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.database.*
import io.github.manuelernesto.takeaway.Holder.MenuViewAdapter
import io.github.manuelernesto.takeaway.Model.Category
import io.github.manuelernesto.takeaway.Utils.Common
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var database: FirebaseDatabase
    lateinit var category: DatabaseReference
    lateinit var adapter: MenuViewAdapter
    var categories: MutableList<Category> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar.title = "Menu"
        setSupportActionBar(toolbar)


        //firebase init
        database = FirebaseDatabase.getInstance()
        category = database.getReference("category")




        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        //user name
        val view: View = nav_view.getHeaderView(0)
        view.userLogged.text = Common.currentUser!!.name


        val manager = LinearLayoutManager(this)
        recyclerview.layoutManager = manager
        recyclerview.setHasFixedSize(true)
        loadMenu()

    }

    fun loadMenu() {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (i in 1..5) {
                    val category: Category? = dataSnapshot.child("0$i").getValue(Category::class.java)
                    if (category != null) {
                        categories.add(category)
                    }
                }

                adapter = MenuViewAdapter(this@HomeActivity, categories) {category ->
                    "${category.name}".toast(this@HomeActivity)
                }

                recyclerview.adapter = adapter

            }

        }

        category.addValueEventListener(valueEventListener)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_menu -> {
                // Handle the camera action
            }
            R.id.nav_cart -> {
                // Handle the camera action
            }
            R.id.nav_orders -> {
                // Handle the camera action
            }
            R.id.nav_log_out -> {
                // Handle the camera action
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }



}
