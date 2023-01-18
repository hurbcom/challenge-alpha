import Foundation

protocol ProductDetailViewModelDelegate: AnyObject {
    func removeItemFromList()
    func addItemFromList()
}

final class ProductDetailViewModel {
    // MARK: Properties

    let model: Product
    var suggestions = [Suggestion]()
    private let service = Service.shared
    
    weak var delegate: ProductDetailViewModelDelegate?
    
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
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func didTapFavorite() {
        do {
            let objectData = try JSONEncoder().encode(model)

            if var favorites = UserDefaults.standard.array(forKey: "favorites") as? [Data] {
                let existingItem = try favorites.first { data in
                    let object = try JSONDecoder().decode(Product.self, from: data)
                    return object.url == model.url
                }

                if let existingItem, let index = favorites.firstIndex(of: existingItem) {
                    favorites.remove(at: index)
                    delegate?.removeItemFromList()

                } else {
                    favorites.append(objectData)
                    delegate?.addItemFromList()
                }

                UserDefaults.standard.setValue(favorites, forKey: "favorites")
            } else {
                UserDefaults.standard.setValue([Data](), forKey: "favorites")
                didTapFavorite()
            }
        } catch {
            print(error.localizedDescription)
        }
    }
    
    func verifyIfItemIsFavorite() {
        if let favorites = UserDefaults.standard.array(forKey: "favorites") as? [Data] {
            let existingItem = favorites.first { data in
                if let object = try? JSONDecoder().decode(Product.self, from: data) {
                    return object.url == model.url
                } else {
                    return false
                }
            }
            
            if existingItem != nil {
                delegate?.addItemFromList()
            } else {
                delegate?.removeItemFromList()
            }
        }
    }
}
