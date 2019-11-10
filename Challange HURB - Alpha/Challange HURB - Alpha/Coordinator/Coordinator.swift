//
//  Coordinator.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 05/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

/// Defines the coordinator behaviour in the app
protocol Coordinator {
    
    // MARK: - Properties
    var childCoordinators: [Coordinator] { get set }
    var navigationController: UINavigationController { get set }
    
    // MARK: - Methods
    func start()
}
