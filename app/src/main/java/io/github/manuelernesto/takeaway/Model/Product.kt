package io.github.manuelernesto.takeaway.Model

class Product() {

    var name: String? = null
    var image: String? = null
    var description: String? = null
    var price: String? = null
    var discount: String? = null
    var menuId: String? = null

    constructor(name: String, image: String, description: String,
                price: String, discount: String, menuId: String) : this() {
        this.name = name
        this.image = image
        this.description = description
        this.price = price
        this.discount = discount
        this.menuId = menuId
    }

}