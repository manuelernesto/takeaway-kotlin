package io.github.manuelernesto.takeaway.Controller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import io.github.manuelernesto.takeaway.Model.Product
import io.github.manuelernesto.takeaway.R
import io.github.manuelernesto.takeaway.Utils.PRODUCT_EXTRA
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var productId: String
    lateinit var database: FirebaseDatabase
    lateinit var product: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        //firebase init
        database = FirebaseDatabase.getInstance()
        product = database.getReference(PRODUCT_EXTRA)

        productId = intent.getStringExtra(PRODUCT_EXTRA)

        loadDetailProduct(productId)
    }

    private fun loadDetailProduct(productId: String) {
        val valueEventListener = object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val product: Product? = dataSnapshot.getValue(Product::class.java)

                Picasso.get()
                        .load(product?.image)
                        .placeholder(R.mipmap.bg_home)
                        .error(R.mipmap.bg_home)
                        .into(product_img)

                collapsing.title = product?.name

                product_name.text = product?.name
                product_price.text = product?.price
                product_description.text = product?.description

            }

        }
        product.child(productId).addValueEventListener(valueEventListener)
    }
}
