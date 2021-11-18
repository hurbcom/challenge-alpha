@testable import AppHotel

class HomeVCOutputSpy: HomeVCOutput {
    @Spy var invokedAskForSearch: String?
    
    func askForSearch(term: String) {
        invokedAskForSearch = term
    }
}
