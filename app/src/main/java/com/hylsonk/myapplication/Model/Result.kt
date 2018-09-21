package com.hylsonk.myapplication.Model

class Result {
    private var sku: String? = null

    fun getSku(): String? {
        return this.sku
    }

    fun setSku(sku: String) {
        this.sku = sku
    }

    private var isHotel: Boolean = false

    fun getIsHotel(): Boolean {
        return this.isHotel
    }

    fun setIsHotel(isHotel: Boolean) {
        this.isHotel = isHotel
    }

    private var category: String? = null

    fun getCategory(): String? {
        return this.category
    }

    fun setCategory(category: String) {
        this.category = category
    }

    private var smallDescription: String? = null

    fun getSmallDescription(): String? {
        return this.smallDescription
    }

    fun setSmallDescription(smallDescription: String) {
        this.smallDescription = smallDescription
    }

    private var amenities: ArrayList<Amenity>? = null

    fun getAmenities(): ArrayList<Amenity>? {
        return this.amenities
    }

    fun setAmenities(amenities: ArrayList<Amenity>) {
        this.amenities = amenities
    }

    private var id: String? = null

    fun getId(): String? {
        return this.id
    }

    fun setId(id: String) {
        this.id = id
    }

    private var price: Price2? = null

    fun getPrice(): Price2? {
        return this.price
    }

    fun setPrice(price: Price2) {
        this.price = price
    }

    private var hu_free_cancellation: Boolean = false

    fun getHuFreeCancellation(): Boolean {
        return this.hu_free_cancellation
    }

    fun setHuFreeCancellation(hu_free_cancellation: Boolean) {
        this.hu_free_cancellation = hu_free_cancellation
    }

    private var image: String? = null

    fun getImage(): String? {
        return this.image
    }

    fun setImage(image: String) {
        this.image = image
    }

    private var name: String? = null

    fun getName(): String? {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

    private var url: String? = null

    fun getUrl(): String? {
        return this.url
    }

    fun setUrl(url: String) {
        this.url = url
    }

    private var description: String? = null

    fun getDescription(): String? {
        return this.description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    private var stars: Int = 0

    fun getStars(): Int {
        return this.stars
    }

    fun setStars(stars: Int) {
        this.stars = stars
    }

    private var gallery: ArrayList<Gallery>? = null

    fun getGallery(): ArrayList<Gallery>? {
        return this.gallery
    }

    fun setGallery(gallery: ArrayList<Gallery>) {
        this.gallery = gallery
    }

    private var address: Address? = null

    fun getAddress(): Address? {
        return this.address
    }

    fun setAddress(address: Address) {
        this.address = address
    }

    private var tags: ArrayList<String>? = null

    fun getTags(): ArrayList<String>? {
        return this.tags
    }

    fun setTags(tags: ArrayList<String>) {
        this.tags = tags
    }

    private var quantityDescriptors: QuantityDescriptors? = null

    fun getQuantityDescriptors(): QuantityDescriptors? {
        return this.quantityDescriptors
    }

    fun setQuantityDescriptors(quantityDescriptors: QuantityDescriptors) {
        this.quantityDescriptors = quantityDescriptors
    }

    private var featuredItem: FeaturedItem? = null

    fun getFeaturedItem(): FeaturedItem? {
        return this.featuredItem
    }

    fun setFeaturedItem(featuredItem: FeaturedItem) {
        this.featuredItem = featuredItem
    }

    private var isPackage: Boolean? = null

    fun getIsPackage(): Boolean? {
        return this.isPackage
    }

    fun setIsPackage(isPackage: Boolean?) {
        this.isPackage = isPackage
    }
}