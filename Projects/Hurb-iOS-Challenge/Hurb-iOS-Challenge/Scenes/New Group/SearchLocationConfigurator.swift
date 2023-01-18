//
//  SearchLocationConfigurator.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 17/01/23.
//  Copyright (c) 2023 ___ORGANIZATIONNAME___. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

@objc class SearchLocationConfigurator: NSObject {
    
    @objc static func setupArch(viewController: SearchLocationViewController) {
        
        let presenter: SearchLocationPresenter = SearchLocationPresenter(viewController: viewController)
        let interactor: SearchLocationInteractor = SearchLocationInteractor(presenter: presenter, worker: SearchLocationWorker())
        let router: SearchLocationRouter = SearchLocationRouter(viewController: viewController, dataStore: interactor)
        
        viewController.interactor = interactor
        viewController.router = router
    }
}
