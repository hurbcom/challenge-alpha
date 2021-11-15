import UIKit

protocol HomeRouterContract {
    func openHotelDetails(_ hotel: Hotel)
    func openPackageDetails(_ package: Hotel)
}

class HomeRouter: HomeRouterContract {
    weak var viewController: UIViewController!
    
    func openHotelDetails(_ hotel: Hotel) {
        // Open Details view controller for Hotel
    }
    
    func openPackageDetails(_ package: Hotel) {
        // Open Details view controller for package
    }
}
