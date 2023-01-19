//
//  PackageDetailsRouter.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import UIKit
import SwiftUI

protocol PackageDetailsRouterProtocol {
    func presentShareSheet(urlString: String)
    @available(iOS 15, *) func presentDescription(_ description: String, amenities: [String])
}

final class PackageDetailsRouter: PackageDetailsRouterProtocol {
    
    weak var viewController: UIViewController?
    
    func presentShareSheet(urlString: String) {
        guard let url = URL(string: urlString) else { return }
        let activityVC = UIActivityViewController(activityItems: [url], applicationActivities: nil)
        viewController?.present(activityVC, animated: true, completion: nil)
    }
    
    @available(iOS 15, *)
    func presentDescription(_ description: String, amenities: [String]) {
        let vc = UIHostingController<PackageDescriptionView>.init(rootView: PackageDescriptionView(description: description, amenities: amenities, closeAction: { [weak self] in
            self?.viewController?.dismiss(animated: true)
        }))
        vc.modalPresentationStyle = .overFullScreen
        vc.view.backgroundColor = .black.withAlphaComponent(0.5)
        
        viewController?.present(vc, animated: true)
    }
}
