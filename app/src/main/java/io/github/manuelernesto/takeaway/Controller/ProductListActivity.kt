package io.github.manuelernesto.takeaway.Controller

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import io.github.manuelernesto.takeaway.Adapter.ProductViewHolder
import io.github.manuelernesto.takeaway.Interface.ItemClickListener
import io.github.manuelernesto.takeaway.Model.Product
import io.github.manuelernesto.takeaway.R
import io.github.manuelernesto.takeaway.Utils.CATEGORY_EXTRA
import io.github.manuelernesto.takeaway.Utils.PRODUCT_EXTRA
import kotlinx.android.synthetic.main.activity_food_list.*

class ProductListActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase
    lateinit var product: DatabaseReference
    lateinit var categoryId: String
    lateinit var viewHolder: FirebaseRecyclerAdapter<Product, ProductViewHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        //firebase init
        database = FirebaseDatabase.getInstance()
        product = database.getReference(PRODUCT_EXTRA)


        categoryId = intent.getStringExtra(CATEGORY_EXTRA)

        val manager = LinearLayoutManager(this)
        recyclerviewFoodList.layoutManager = manager
        recyclerviewFoodList.setHasFixedSize(true)
        loadMenuItems(categoryId)
    }

    private fun loadMenuItems(categoryId: String) {
        val productQuery = product.orderByChild("menuId").equalTo(categoryId)

        val productOption = FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(productQuery, Product::class.java).build()

        viewHolder = object : FirebaseRecyclerAdapter<Product, ProductViewHolder>(productOption) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.product_item, parent, false)

                val name = view.findViewById<TextView>(R.id.txtNameProduct)
                val price = view.findViewById<TextView>(R.id.txtPriceProductCart)
                val discount = view.findViewById<TextView>(R.id.txtDiscountProduct)
                val img = view.findViewById<ImageView>(R.id.imgProduct)

                return ProductViewHolder(view, img, name, price, discount)
            }

            @SuppressLint("SetTextI18n")
            override fun onBindViewHolder(holder: ProductViewHolder, position: Int, model: Product) {
                holder.productName.text = model.name
                holder.productPrice.text = "Price: $${model.price}"
                holder.productDiscount.text = "Discount: $${model.discount}"


                Picasso.get()
                        .load(model.image)
                        .placeholder(R.mipmap.bg_home)
                        .error(R.mipmap.bg_home)
                        .into(holder.productImg)

                val itemClickListener = object : ItemClickListener {
                    override fun onClick(view: View, position: Int, isLongClick: Boolean) {
                        val intent = Intent(this@ProductListActivity, ProductDetailActivity::class.java)
                        intent.putExtra(PRODUCT_EXTRA, viewHolder.getRef(position).key)
                        startActivity(intent)
                    }
                }
                holder.setitemClickListener(itemClickListener)
            }
        }
        recyclerviewFoodList.adapter = viewHolder
    }

    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    override fun onStart() {
        super.onStart()
        viewHolder.startListening()
    }

    override fun onStop() {
        super.onStop()
        viewHolder.stopListening()
    }
}
