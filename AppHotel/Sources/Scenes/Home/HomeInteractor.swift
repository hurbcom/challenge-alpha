protocol HomeInteractorInput {
    func fetchProducts()
}

protocol HomeInteractorOutput {
    func presentProducts(_ list: [Hotel])
    func presentError(_ error: String)
}

class HomeInteractor: HomeInteractorInput {
//    var repository: HotelRepository!
    var output: HomeInteractorOutput!
    var repository: HotelRepositoryType!
    
    func fetchProducts() {
        repository.searchHotels(query: "Teste") { [weak self] hotels in
            self?.output.presentProducts(hotels)
//            output.presentError("Não implementado")
        }
    }
}
