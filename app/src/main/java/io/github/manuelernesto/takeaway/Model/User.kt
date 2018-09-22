package io.github.manuelernesto.takeaway.Model

class User() {

    var name: String? = null
    var password: String? = null

    constructor(name: String, password: String) : this() {
        this.name = name
        this.password = password
    }


}