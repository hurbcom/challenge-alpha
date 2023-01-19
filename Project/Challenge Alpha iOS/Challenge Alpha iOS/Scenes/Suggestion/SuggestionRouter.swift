//
//  SuggestionRouter.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import UIKit

protocol SuggestionRouterProtocol {
    func dismiss()
}

final class SuggestionRouter: SuggestionRouterProtocol {
    weak var viewController: UIViewController?
    
    func dismiss() {
        viewController?.dismiss(animated: true)
    }
}
