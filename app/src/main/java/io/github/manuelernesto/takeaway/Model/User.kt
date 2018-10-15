package io.github.manuelernesto.takeaway.Model

class User() {

    lateinit var name: String
    lateinit var password: String
    lateinit var phone: String

    constructor(name: String, password: String, phone: String) : this() {
        this.name = name
        this.password = password
        this.phone = phone
    }
}