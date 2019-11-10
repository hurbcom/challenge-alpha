//
//  CoordinatorComunicationDelegate.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 07/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// Interface used to communication between controllers and the coordinator
protocol CoordinatorComunicationDelegate: AnyObject {
    // MARK: - Properties
    var coordinator: MainCoordinator? { get }
}
