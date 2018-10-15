package io.github.manuelernesto.takeaway.Model

class Request() {

    lateinit var phone: String
    lateinit var name: String
    lateinit var address: String
    lateinit var total: String
    lateinit var foods: List<Order>

    constructor(
            phone: String,
            name: String,
            address: String,
            total: String,
            foods: List<Order>) : this() {
        this.phone = phone
        this.name = name
        this.address = address
        this.total = total
        this.foods = foods
    }
}