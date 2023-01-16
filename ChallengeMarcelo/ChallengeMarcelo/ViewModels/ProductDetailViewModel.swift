final class ProductDetailViewModel {
    // MARK: Properties

    let model: Product
    var suggestions = [Suggestion]()
    private let service = Service.shared
    
    // MARK: Init
    
    init(model: Product) {
        self.model = model
    }
    
    func performSuggestions() {
        service.performSuggestion(queryString: model.address.city) { [weak self] result in
            guard let self else { return }
            
            switch result {
            case .success(let suggestionsItems):
                if let items = suggestionsItems {
                    let suggestionsArray = items.map { item in
                        return Suggestion(
                            suggestionType:
                                item.asProductSuggestion?.suggestionType ?? "",
                            text: item.asProductSuggestion?.text ?? "")
                    }
                    self.suggestions = suggestionsArray.filter { item in
                        return !item.text.isEmpty
                    }
                    print(suggestionsArray)
                }
            case .failure(let error):
                print(error)
            }
        }
    }
}
