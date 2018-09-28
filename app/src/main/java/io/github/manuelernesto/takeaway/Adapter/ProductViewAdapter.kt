package io.github.manuelernesto.takeaway.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.manuelernesto.takeaway.Model.Product
import io.github.manuelernesto.takeaway.R

class ProductViewAdapter(private val context: Context, private val products: List<Product>, val itemClick: (Product) -> Unit) : RecyclerView.Adapter<ProductViewAdapter.Holder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.product_item, parent, false)

        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindCategory(products[position], context)
    }

    inner class Holder(itemView: View?, val itemClick: (Product) -> Unit) : RecyclerView.ViewHolder(itemView!!) {
//        var categoryImg = itemView?.findViewById<ImageView>(R.id.imgFood)
//        var categoryName = itemView?.findViewById<TextView>(R.id.txtNameFood)
//        var categoryPrice = itemView?.findViewById<TextView>(R.id.txtFoodPrice)
//        var categoryDiscount = itemView?.findViewById<TextView>(R.id.txtFoodDiscount)

        @SuppressLint("SetTextI18n")
        fun bindCategory(product: Product, context: Context) {

//            Picasso.get()
//                    .load(product.image)
//                    .placeholder(R.mipmap.bg_home)
//                    .error(R.mipmap.bg_home)
//                    .into(categoryImg)
//
//            categoryName?.text = product.name
//            categoryPrice?.text = "Price: $${product.price}"
//            categoryDiscount?.text = "Discount: $${product.discount}"

            itemView.setOnClickListener { itemClick(product) }
        }
    }
}
