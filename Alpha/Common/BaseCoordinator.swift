//
//  BaseCoordinator.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

/// The coordinator that holds all coordinators
class BaseCoordinator: Coordinator {
    var childCoordinators: [Coordinator] = []
    /// Is called when the coordinator is completed
    var isCompleted: (() -> ())?

    func start() {
        fatalError("Children should implement `start`.")
    }
}
