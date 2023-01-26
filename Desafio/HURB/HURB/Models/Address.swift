struct Address: Codable {
    var state: String
    var country: String
    var city: String
    var geoLocation: Location
}
