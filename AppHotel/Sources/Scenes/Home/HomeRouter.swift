import UIKit

protocol HomeRouterContract {
    func openHotelDetails(_ hotel: SearchResult)
    func openPackageDetails(_ package: SearchResult)
}

class HomeRouter: HomeRouterContract {
    weak var viewController: UIViewController!
    
    func openHotelDetails(_ hotel: SearchResult) {
        // Open Details view controller for Hotel
    }
    
    func openPackageDetails(_ package: SearchResult) {
        // Open Details view controller for package
    }
}
