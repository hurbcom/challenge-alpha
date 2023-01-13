struct HomeViewModel {
    private let service = Service.shared
    
    func performSearch(for text: String) {
        service.perform(search: text) { results in
            if let abc = results {
                print(abc.map(\.name))
            }
        }
    }
}
