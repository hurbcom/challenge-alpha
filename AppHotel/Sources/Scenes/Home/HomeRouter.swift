import UIKit

protocol HomeRouterType {
    func openDetails(_ hotel: SearchResult)
}

class HomeRouter: HomeRouterType {
    weak var viewController: UIViewController!
    
    func openDetails(_ result: SearchResult) {
        let detailsViewController = DetailsVC(result: result)
        viewController.navigationController?.pushViewController(detailsViewController, animated: true)
    }
}
