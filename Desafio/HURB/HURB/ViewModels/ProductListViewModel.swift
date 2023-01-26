import Foundation

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
    // Procurar hotel a partir de uma cidade/estado/país
    func searchHotels(in place: String) {
        service.performHotel(search: place) { [weak self] in
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
                    self.products = productsArray.filter { product in
                        return product.isAvalible
                    }
                    self.delegate?.didUpdate()
                }
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
    
    // procurar um pacote de viagem a partir de uma cidade/estado/país
    func searchPackages(in place: String) {
        service.performPackage(search: place) { [weak self] in
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
                    self.products = productsArray.filter { product in
                        return product.isAvalible
                    }
                    self.delegate?.didUpdate()
                }
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
    // salvar um produto na lista de historico ao clicar na celula
    func didTapCell(product: Product) {
        do {
            let objectData = try JSONEncoder().encode(product)

            if var favorites = UserDefaults.standard.array(forKey: "myHistory") as? [Data] {
                let existingItem = try favorites.first { data in
                    let object = try JSONDecoder().decode(Product.self, from: data)
                    return object.url == product.url
                }

                if existingItem == nil {
                    favorites.append(objectData)
                }

                UserDefaults.standard.setValue(favorites, forKey: "myHistory")
            } else {
                UserDefaults.standard.setValue([Data](), forKey: "myHistory")
                didTapCell(product: product)
            }
        } catch {
            print(error.localizedDescription)
        }
    }
}
