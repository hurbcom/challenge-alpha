protocol ProductListViewModelDelegate: AnyObject {
    func didUpdate()
}

final class ProductListViewModel {
    // MARK: Properties
    
    var products = [Product]()
    private let service = Service.shared
    
    // MARK: Delegate
    
    weak var delegate: ProductListViewModelDelegate?

    // MARK: Public
    
    func performSearch(for text: String) {
        service.perform(search: text) { [weak self] in
            guard let self else { return }
            
            switch $0 {
            case .success(let searchedItems):
                if let items = searchedItems {
                    let productsArray = items.map { item in
                        return Product(
                            name: item.name,
                            category: item.category,
                            price: Double(item.price.amount),
                            gallery: item.gallery.compactMap { galleryItem in
                                return galleryItem.url
                            },
                            smallDescription: item.smallDescription,
                            isAvalible: item.isAvailable,
                            _description: item.description,
                            url: item.url,
                            address: Address(
                                state: item.address?.state ?? "",
                                country: item.address?.country ?? "",
                                city: item.address?.city ?? "",
                                geoLocation: Location(
                                    lat: item.address?.geoLocation?.lat ?? 0.0,
                                    lon: item.address?.geoLocation?.lon ?? 0.0
                                )
                            )
                        )
                    }
                    self.products = productsArray.filter({ product in
                        return product.isAvalible
                    })
                    self.delegate?.didUpdate()
                }

            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
