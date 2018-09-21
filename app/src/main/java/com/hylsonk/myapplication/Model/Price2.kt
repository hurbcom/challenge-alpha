package com.hylsonk.myapplication.Model

class Price2 {
    private var cost: Double = 0.toDouble()

    fun getCost(): Double {
        return this.cost
    }

    fun setCost(cost: Double) {
        this.cost = cost
    }

    private var cost_fee: Int = 0

    fun getCostFee(): Int {
        return this.cost_fee
    }

    fun setCostFee(cost_fee: Int) {
        this.cost_fee = cost_fee
    }

    private var cost_price: Double = 0.toDouble()

    fun getCostPrice(): Double {
        return this.cost_price
    }

    fun setCostPrice(cost_price: Double) {
        this.cost_price = cost_price
    }

    private var current_price: Double = 0.toDouble()

    fun getCurrentPriceDouble(): Double {
        return this.current_price
    }

    fun setCurrentPrice(current_price: Double) {
        this.current_price = current_price
    }

    private var old_price: Double = 0.toDouble()

    fun getOldPriceDouble(): Double {
        return this.old_price
    }

    fun setOldPrice(old_price: Double) {
        this.old_price = old_price
    }

    private var sku: String? = null

    fun getSku(): String? {
        return this.sku
    }

    fun setSku(sku: String) {
        this.sku = sku
    }

    private var originalAmountPerDay: Double = 0.toDouble()

    fun getOriginalAmountPerDay(): Double {
        return this.originalAmountPerDay
    }

    fun setOriginalAmountPerDay(originalAmountPerDay: Double) {
        this.originalAmountPerDay = originalAmountPerDay
    }

    private var amountPerDay: Double = 0.toDouble()

    fun getAmountPerDay(): Double {
        return this.amountPerDay
    }

    fun setAmountPerDay(amountPerDay: Double) {
        this.amountPerDay = amountPerDay
    }

    private var amount: Double = 0.toDouble()

    fun getAmount(): Double {
        return this.amount
    }

    fun setAmount(amount: Double) {
        this.amount = amount
    }

    private var oldPrice: Int? = null

    fun getOldPrice(): Int? {
        return this.oldPrice
    }

    fun setOldPrice(oldPrice: Int?) {
        this.oldPrice = oldPrice
    }

    private var currentPrice: Int? = null

    fun getCurrentPrice(): Int? {
        return this.currentPrice
    }

    fun setCurrentPrice(currentPrice: Int?) {
        this.currentPrice = currentPrice
    }

    private var originalAmount: Int? = null

    fun getOriginalAmount(): Int? {
        return this.originalAmount
    }

    fun setOriginalAmount(originalAmount: Int?) {
        this.originalAmount = originalAmount
    }
}