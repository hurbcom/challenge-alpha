//
//  AppCoordinator.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import Foundation
import UIKit

class AppCoordinator: BaseCoordinator {
    private var window: UIWindow
    
    init(_ window: UIWindow) {
        self.window = window
    }
    
    override func start() {
        let homeView = MovieListCoordinator()
        self.start(coordinator: homeView)
        
        window.rootViewController = homeView.navigationController
        window.makeKeyAndVisible()
    }
}
