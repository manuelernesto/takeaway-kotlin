package io.github.manuelernesto.takeaway.Model

class Order() {

    lateinit var productId: String
    lateinit var productName: String
    lateinit var quantity: String
    lateinit var price: String
    lateinit var discount: String

    constructor(productId: String, productName: String, quantity: String
                , price: String, discount: String) : this() {

        this.productId = productId
        this.productName = productName
        this.quantity = quantity
        this.price = price
        this.discount = discount
    }
}