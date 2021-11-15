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
    
    func fetchProducts() {
        // let hotels = repository.getHotels()
        output.presentError("Não implementado")
    }
}
