package io.github.manuelernesto.takeaway.DB

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import io.github.manuelernesto.takeaway.Model.Order

class Database(context: Context) : SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val DB_NAME = "takeawayDB.db"
        private val DB_VERSION = 1
    }

    fun getCarts(): List<Order> {
        val db: SQLiteDatabase = readableDatabase
        val q = SQLiteQueryBuilder()
        val select_column = arrayOf<String>(
                "ProductId", "ProductName", "Quantity", "Price", "Discount")
        val table = "OrderDetail"
        q.tables = table
        val cursor: Cursor = q.query(db, select_column, null, null, null, null, null)

        val result = ArrayList<Order>()

        if (cursor.moveToFirst()) {
            do {
                result.add(Order(
                        cursor.getString(cursor.getColumnIndex("ProductId")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Discount"))
                ))
            } while (cursor.moveToNext())
        }

        return result
    }

    fun addToCart(order: Order) {
        val db: SQLiteDatabase = writableDatabase
        val query = String.format(
                "INSERT INTO OrderDetail(ProductId, ProductName,Quantity,Price,Discount)VALUES('%S','%S','%S','%S','%S');",
                order.productId, order.productName, order.quantity, order.price, order.discount)

        db.execSQL(query)
    }

    fun cleanCart() {
        val db: SQLiteDatabase = writableDatabase
        val query =
                "DELETE FROM OrderDetail"

        db.execSQL(query)
    }

}
