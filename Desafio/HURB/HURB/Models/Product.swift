struct Product: Codable {
    var name: String
    var category: String
    var price: Double
    var gallery: [String]
    var smallDescription: String
    var isAvalible: Bool
    var _description: String
    var url: String
    var address: Address
}
