//
//  PackageDetailsRouter.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import UIKit

protocol PackageDetailsRouterProtocol {
    func presentShareSheet(urlString: String)
}

final class PackageDetailsRouter: PackageDetailsRouterProtocol {
    
    weak var viewController: UIViewController?
    
    func presentShareSheet(urlString: String) {
        guard let url = URL(string: urlString) else { return }
        let activityVC = UIActivityViewController(activityItems: [url], applicationActivities: nil)
        viewController?.present(activityVC, animated: true, completion: nil)
    }
}
