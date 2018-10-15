package io.github.manuelernesto.takeaway.Adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.amulyakhare.textdrawable.TextDrawable
import io.github.manuelernesto.takeaway.Model.Order
import io.github.manuelernesto.takeaway.R
import java.text.NumberFormat
import java.util.*


class CartAdapter(private val context: Context, private val orders: List<Order>) : RecyclerView.Adapter<CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.cart_item, parent, false)
        val name = view.findViewById<TextView>(R.id.txtNameProductCart)
        val price = view.findViewById<TextView>(R.id.txtPriceProductCart)
        val btn_img = view.findViewById<ImageView>(R.id.btn_cart_count)
        return CartViewHolder(view, name, price, btn_img)
    }

    override fun getItemCount(): Int {
        return orders.count()
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val textDrawable = TextDrawable.builder()
                .buildRound("" + orders[position].quantity, Color.RED)
        holder.cartBtnCount.setImageDrawable(textDrawable)

        val price = (Integer.parseInt(orders[position].price)) *
                (Integer.parseInt(orders[position].quantity))

        val locale = Locale("en", "US")
        val nf = NumberFormat.getCurrencyInstance(locale)

        holder.cartProductPrice.text = nf.format(price)
        holder.cartProductName.text = orders[position].productName
    }


}