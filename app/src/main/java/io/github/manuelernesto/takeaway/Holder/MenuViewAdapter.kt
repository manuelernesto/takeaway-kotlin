package io.github.manuelernesto.takeaway.Holder

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.github.manuelernesto.takeaway.Model.Category
import io.github.manuelernesto.takeaway.R

class MenuViewAdapter(private val context: Context, private val categories: List<Category>, val itemClick: (Category) -> Unit) : RecyclerView.Adapter<MenuViewAdapter.Holder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.menu_item, parent, false)

        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindCategory(categories[position], context)
    }

    inner class Holder(itemView: View?, val itemClick: (Category) -> Unit) : RecyclerView.ViewHolder(itemView!!) {
        var categoryImg = itemView?.findViewById<ImageView>(R.id.categoryImage)
        var categoryName = itemView?.findViewById<TextView>(R.id.categoryName)

        fun bindCategory(category: Category, context: Context) {

            Picasso.get()
                    .load(category.image)
                    .placeholder(R.mipmap.bg_home)
                    .error(R.mipmap.bg_home)
                    .into(categoryImg)

            categoryName?.text = category.name

            itemView.setOnClickListener { itemClick(category) }
        }
    }
}
