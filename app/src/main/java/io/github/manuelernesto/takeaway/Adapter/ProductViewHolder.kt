package io.github.manuelernesto.takeaway.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.manuelernesto.takeaway.Interface.ItemClickListener

class ProductViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

    var productImg: ImageView
    var productName: TextView
    var productPrice: TextView
    var productDiscount: TextView

    lateinit var itemClickListener: ItemClickListener

    constructor(itemView: View,
                productImg: ImageView,
                productName: TextView,
                productPrice: TextView,
                productDiscount: TextView) : super(itemView) {
        this.productImg = productImg
        this.productName = productName
        this.productPrice = productPrice
        this.productDiscount = productDiscount
        itemView.setOnClickListener(this)
    }

    fun setitemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }


    override fun onClick(v: View?) {
        itemClickListener.onClick(v!!, adapterPosition, false)
    }


}