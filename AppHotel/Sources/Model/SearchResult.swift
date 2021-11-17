struct SearchResult {
    let name: String
    let description: String
    let category: ResultCategory
}

enum ResultCategory: String {
    case hotel, package, activity, none
}
