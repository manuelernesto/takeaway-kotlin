package io.github.manuelernesto.takeaway.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.manuelernesto.takeaway.Interface.ItemClickListener

class CartViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

    var cartProductName: TextView
    var cartProductPrice: TextView
    var cartBtnCount: ImageView

    lateinit var itemClickListener: ItemClickListener

    constructor(itemView: View,
                cartProductName: TextView,
                cartProductPrice: TextView,
                cartBtnCount: ImageView) : super(itemView) {

        this.cartProductName = cartProductName
        this.cartProductPrice = cartProductPrice
        this.cartBtnCount = cartBtnCount

        itemView.setOnClickListener(this)

    }

    fun setitemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!, adapterPosition, false)
    }


}