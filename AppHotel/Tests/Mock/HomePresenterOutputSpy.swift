import UIKit
@testable import AppHotel

class HomePresenterOutputSpy: HomePresenterOutput {
    @Spy var invokedShowResults: [SearchResult]?
    @Spy var invokedShowAlert: UIAlertController?

    func showResults(_ list: [SearchResult]) {
        invokedShowResults = list
    }
    
    func showAlert(_ alert: UIAlertController) {
        invokedShowAlert = alert
    }
}
