package io.github.manuelernesto.takeaway.Controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.github.manuelernesto.takeaway.Adapter.CartAdapter
import io.github.manuelernesto.takeaway.DB.Database
import io.github.manuelernesto.takeaway.Model.Order
import io.github.manuelernesto.takeaway.R
import kotlinx.android.synthetic.main.activity_cart.*
import java.text.NumberFormat
import java.util.*

class CartActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var request: DatabaseReference
    lateinit var adapter: CartAdapter
    lateinit var carts: List<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //firebase init
        database = FirebaseDatabase.getInstance()
        request = database.getReference("Request")

        loadListProduct()
    }

    @SuppressLint("SetTextI18n")
    private fun loadListProduct() {
        carts = Database(this).getCarts()
        adapter = CartAdapter(this, carts)
        val manager = LinearLayoutManager(this)
        recyclerview_cart.layoutManager = manager
        recyclerview_cart.setHasFixedSize(true)
        recyclerview_cart.adapter = adapter

        //total of price
        var total = 0
        for (order in carts) {
            total += (Integer.parseInt(order.price)) * (Integer.parseInt(order.quantity))
        }

        val locale = Locale("en", "US")
        val nf = NumberFormat.getCurrencyInstance(locale)
        total_cart_price.text = nf.format(total)
    }
}
