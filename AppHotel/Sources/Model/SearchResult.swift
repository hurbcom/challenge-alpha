struct SearchResult {
    let name: String
    let description: String
    let cover: String
    let price: String
    let address: String
    let category: ResultCategory
    let gallery: [ResultImage]
}

struct ResultImage {
    let url: String
    let name: String
}

enum ResultCategory: String {
    case hotel, package, activity, none
}
