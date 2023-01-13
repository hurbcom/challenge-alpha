protocol HomeViewModelDelegate: AnyObject {
    func didUpdate(products: [Product])
}

final class HomeViewModel {
    // MARK: Properties
    
    private let service = Service.shared
    
    // MARK: Delegate
    
    weak var delegate: HomeViewModelDelegate?
    
    // MARK: Public
    
    func performSearch(for text: String) {
        service.perform(search: text) { [weak self] in
            guard let self else { return }

            switch $0 {
            case .success(let searchedItems):
                if let items = searchedItems {
                    let productsArray = items.map { item in
                        return Product(name: item.name, category: item.category)
                    }

                    self.delegate?.didUpdate(products: productsArray)
                }
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
